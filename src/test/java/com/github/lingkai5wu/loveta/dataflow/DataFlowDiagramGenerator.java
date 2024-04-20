package com.github.lingkai5wu.loveta.dataflow;

import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithJavadoc;
import com.github.javaparser.ast.nodeTypes.NodeWithType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.lingkai5wu.loveta.dataflow.model.Controller;
import com.github.lingkai5wu.loveta.dataflow.model.Method;
import com.github.lingkai5wu.loveta.dataflow.model.MethodCall;
import com.github.lingkai5wu.loveta.dataflow.model.Service;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaType;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.function.Function;

@Component
public class DataFlowDiagramGenerator {

    private final String ROOT = "src/main/java/com/github/lingkai5wu/loveta";
    private final ApplicationContext applicationContext;

    public DataFlowDiagramGenerator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @SneakyThrows
    public void generate() {
        List<Controller> controllerList = processFiles(ROOT + "/controller", this::getController);
        List<Service> serviceList = listServices();

        for (Controller controller : controllerList) {
            for (Method method : controller.getMethodList()) {
                for (MethodCall methodCall : method.getServiceMethodCallList()) {
                    Service service = serviceList.stream()
                            .filter(s -> s.getClassType()
                                    .equals(methodCall.getServiceClassType()))
                            .findFirst()
                            .orElse(null);
                    if (service == null) {
                        throw new RuntimeException("Service not found: " + methodCall);
                    }
                    Method serviceMethod = service.getMethodList()
                            .stream()
                            .filter(m -> m.getName()
                                    .equals(methodCall.getName()))
                            .findFirst()
                            .orElse(null);
                    if (serviceMethod == null) {
                        throw new RuntimeException("Service method not found: " + methodCall);
                    }
                    BeanUtil.copyProperties(serviceMethod, methodCall);
                }
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.writeValue(new File("target/dataflow.json"),
                controllerList);
    }

    private <T> List<T> processFiles(String filePath, Function<String, T> fileProcessor) {
        List<T> resultList = new ArrayList<>();
        File file = new File(filePath);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    resultList.addAll(processFiles(f.getAbsolutePath(), fileProcessor));
                }
            }
        } else if (file.getName()
                .endsWith(".java")) {
            resultList.add(fileProcessor.apply(file.getAbsolutePath()));
        }
        return resultList;
    }

    @SneakyThrows
    private Controller getController(String filePath) {
        CompilationUnit cu = StaticJavaParser.parse(new FileInputStream(filePath));

        Controller controller = new Controller();
        cu.accept(new ControllerVisitor(), controller);

        List<Method> methodList = new ArrayList<>();
        cu.accept(new MethodVisitor(), methodList);
        for (Method method : methodList) {
            List<MethodCall> methodCallList = new ArrayList<>(method.getMethodCallList());
            Iterator<MethodCall> iterator = methodCallList.iterator();
            while (iterator.hasNext()) {
                MethodCall methodCall = iterator.next();
                String serviceFieldType = controller.getServiceFieldMap()
                        .get(methodCall.getScope());
                if (serviceFieldType == null) {
                    iterator.remove();
                    continue;
                }
                methodCall.setScope(null);
                methodCall.setServiceClassType(serviceFieldType);
            }
            method.setMethodCallList(null);
            method.setServiceMethodCallList(methodCallList);
        }
        controller.setServiceFieldMap(null);
        controller.setMethodList(methodList);
        return controller;
    }

    @SneakyThrows
    private List<Service> listServices() {
        JavaProjectBuilder builder = new JavaProjectBuilder();
        builder.addSourceTree(new File(ROOT + "/service/impl"));

        Collection<JavaClass> javaClassCollection = builder.getClasses();
        List<Service> serviceList = new ArrayList<>();
        for (JavaClass javaClass : javaClassCollection) {
            Service service = new Service();
            service.setName(javaClass.getName());
            service.setComment(javaClass.getComment());
            service.setPackageName(javaClass.getPackageName());
            service.setClassName(javaClass.getName());
            service.setClassType(javaClass.getImplements()
                    .stream()
                    .findFirst()
                    .map(JavaType::getValue)
                    .orElse(null)
            );

            String entityName = javaClass.getName()
                    .replace("ServiceImpl", "");
            List<Method> methodList = new ArrayList<>();
            Class<?> serviceClass = Class.forName(service.getPackageName() + "." + service.getClassName());
            serviceClass = applicationContext.getBean(serviceClass)
                    .getClass();
            java.lang.reflect.Method[] serviceClassMethods = serviceClass.getMethods();
            for (java.lang.reflect.Method serviceClassMethod : serviceClassMethods) {
                Method method = new Method();
                method.setName(serviceClassMethod.getName());
                List<String> parameterTypesList = new ArrayList<>(Arrays.stream(serviceClassMethod.getParameterTypes())
                        .map(Class::getSimpleName)
                        .toList());
                for (int i = 0; i < parameterTypesList.size(); i++) {
                    String parameterType = parameterTypesList.get(i);
                    if ("Object".equals(parameterType)) {
                        parameterTypesList.set(i, entityName);
                    }
                }
                method.setParameterTypeList(parameterTypesList);
                String returnType = serviceClassMethod.getReturnType()
                        .getSimpleName();
                if ("Object".equals(returnType)) {
                    returnType = entityName;
                }
                method.setReturnType(returnType);
                methodList.add(method);
            }
            service.setMethodList(methodList);
            serviceList.add(service);
        }
        return serviceList;
    }

    private String getJavadocCommentContent(NodeWithJavadoc<?> node) {
        return node.getComment()
                .map(comment -> comment.getContent()
                        .split("\n")[1].replaceFirst("\\*", "")
                        .strip())
                .orElse(null);
    }

    private class ControllerVisitor extends VoidVisitorAdapter<Controller> {

        @Override
        public void visit(ClassOrInterfaceDeclaration n, Controller arg) {
            super.visit(n, arg);

            arg.setName(n.getNameAsString())
                    .setName(n.getNameAsString())
                    .setComment(getJavadocCommentContent(n));
        }

        @Override
        public void visit(FieldDeclaration n, Controller arg) {
            super.visit(n, arg);

            Map<String, String> serviceFieldMap = arg.getServiceFieldMap();
            if (serviceFieldMap == null) {
                serviceFieldMap = new HashMap<>();
                arg.setServiceFieldMap(serviceFieldMap);
            }
            Map<String, String> finalServiceFieldMap = serviceFieldMap;
            n.getVariables()
                    .stream()
                    .filter(var -> var.getTypeAsString()
                            .endsWith("Service"))
                    .forEach(var -> finalServiceFieldMap.put(var.getNameAsString(), var.getTypeAsString()));
        }
    }

    private class MethodVisitor extends VoidVisitorAdapter<List<Method>> {

        @Override
        public void visit(MethodDeclaration n, List<Method> arg) {
            super.visit(n, arg);

            List<MethodCall> methodCallList = n.findAll(MethodCallExpr.class)
                    .stream()
                    .map(m -> new MethodCall().setName(m.getNameAsString())
                            .setScope(m.getScope()
                                    .map(Node::toString)
                                    .orElse(null)))
                    .toList();

            List<String> parameterTypeList = n.getParameters()
                    .stream()
                    .map(NodeWithType::getTypeAsString)
                    .toList();

            arg.add(new Method().setName(n.getNameAsString())
                    .setComment(getJavadocCommentContent(n))
                    .setParameterTypeList(parameterTypeList)
                    .setReturnType(n.getTypeAsString())
                    .setMethodCallList(methodCallList));
        }
    }

}
