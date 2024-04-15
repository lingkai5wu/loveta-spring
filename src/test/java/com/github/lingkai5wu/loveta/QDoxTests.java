package com.github.lingkai5wu.loveta;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaType;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QDoxTests {
    private static @NotNull FileWriter initWriter(String fileName) throws IOException {
        File file = new File("target/" + fileName + ".html");
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write("""
                <!DOCTYPE html>
                <html lang="zh">
                <head>
                    <meta charset="UTF-8">
                    <title>%s</title>
                </head>
                <body>
                """.formatted(fileName));
        return writer;
    }

    private static void destroyWriter(FileWriter writer) throws IOException {
        writer.write("""
                </body>
                </html>
                """);
        writer.close();
    }

    @Test
    void generateDateItem() throws IOException {
        FileWriter writer = initWriter("data-item");

        JavaProjectBuilder builder = new JavaProjectBuilder();
        builder.addSourceTree(new File("src/main/java/com/github/lingkai5wu/loveta/model"));

        Collection<JavaClass> javaClassCollection = builder.getClasses();
        Map<List<String>, Set<String>> map = new HashMap<>();
        for (JavaClass javaClass : javaClassCollection) {
            List<JavaField> fields = javaClass.getFields();
            for (JavaField field : fields) {
                List<String> fieldInfo = List.of(field.getName(), field.getType().getName());
                Set<String> commentSet = map.getOrDefault(fieldInfo, new HashSet<>());
                commentSet.add(field.getComment());
                map.put(fieldInfo, commentSet);
            }
        }
        map.forEach((k, v) -> {
            if (v.size() > 1) {
                System.out.println(k + " -> " + v);
            }
        });
        StringBuilder sb = new StringBuilder();
        sb.append("<table>");
        sb.append("<tr><th>数据项</th><th>说明</th><th>注释</th></tr>");

        Map<List<String>, Set<String>> sortedMap = new TreeMap<>(Comparator.comparing(o -> o.get(0)));
        sortedMap.putAll(map);

        for (Map.Entry<List<String>, Set<String>> entry : sortedMap.entrySet()) {
            List<String> key = entry.getKey();
            Set<String> value = entry.getValue();
            sb.append("<tr>");
            sb.append("<td>").append(key.get(0)).append("</td>");
            sb.append("<td>").append(key.get(1)).append("</td>");
            String comments = String.join("、", value);
            sb.append("<td>").append(comments).append("</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");

        writer.write(sb.toString());
        destroyWriter(writer);
    }

    @Test
    void generateDataFlow() throws IOException {
        FileWriter writer = initWriter("data-flow");

        JavaProjectBuilder builder = new JavaProjectBuilder();
        builder.addSourceTree(new File("src/main/java/com/github/lingkai5wu/loveta/model"));

        Collection<JavaClass> javaClassCollection = builder.getClasses();
        StringBuilder sb = new StringBuilder();
        sb.append("<table>");
        sb.append("<tr><th>数据流</th><th>说明</th><th>来源</th><th>去向</th><th>数据项</th></tr>");
        for (JavaClass javaClass : javaClassCollection) {
            List<JavaField> fields = javaClass.getFields();
            sb.append("<tr>");
            sb.append("<td>").append(javaClass.getName()).append("</td>");
            sb.append("<td>").append(javaClass.getComment()).append("</td>");
            if (javaClass.getName().endsWith("VO")) {
                sb.append("<td>系统</td>");
                sb.append("<td>用户</td>");
            } else {
                sb.append("<td>用户</td>");
                sb.append("<td>系统</td>");
            }
            String joined = String.join("、", fields.stream().map(JavaField::getComment).toList());
            sb.append("<td>").append(joined).append("</td>");
            sb.append("</tr>");
        }
        sb.append("</table>\n");

        writer.write(sb.toString());
        destroyWriter(writer);
    }

    @Test
    void generateDataProcess() throws IOException {
        FileWriter writer = initWriter("data-process");

        JavaProjectBuilder builder = new JavaProjectBuilder();
        builder.addSourceTree(new File("src/main/java/com/github/lingkai5wu/loveta/controller"));

        Collection<JavaClass> javaClassCollection = builder.getClasses();
        StringBuilder sb = new StringBuilder();
        sb.append("<table>");
        sb.append("<tr><th>处理名</th><th>说明</th><th>输入数据流</th><th>输出数据流</th></tr>");
        for (JavaClass javaClass : javaClassCollection) {
            List<JavaMethod> methods = javaClass.getMethods();
            for (JavaMethod method : methods) {
                sb.append("<tr>");
                sb.append("<td>").append(method.getName()).append("</td>");
                sb.append("<td>").append(method.getComment()).append("</td>");
                sb.append("<td>").append(method.getParameters()).append("</td>");
                String generic = method.getReturns().getGenericValue();
                generic = generic.replace("<", "&lt;").replace(">", "&gt;");
                sb.append("<td>").append(generic).append("</td>");
                sb.append("</tr>");
            }
        }
        sb.append("</table>\n");

        writer.write(sb.toString());
        destroyWriter(writer);
    }

    @Test
    void generateDataProcessTest() throws IOException {
        JavaProjectBuilder builder = new JavaProjectBuilder();
        builder.addSource(new File("src/main/java/com/github/lingkai5wu/loveta/controller/AggregateController.java"));

        Collection<JavaClass> javaClassCollection = builder.getClasses();
        Pattern pattern = Pattern.compile("\\b([a-zA-Z]+)Service\\b");
        for (JavaClass javaClass : javaClassCollection) {
            List<JavaField> fields = javaClass.getFields();
            List<JavaMethod> methods = javaClass.getMethods();
            Set<String> serviceNameSet = new HashSet<>();
            Set<String> serviceTypeNameSet = new HashSet<>();
            for (JavaMethod method : methods) {
                String sourceCode = method.getSourceCode();
                Matcher matcher = pattern.matcher(sourceCode);
                while (matcher.find()) {
                    serviceNameSet.add(matcher.group(1));
                }
            }
            serviceNameSet.forEach(serviceName -> {
                String serviceFullName = serviceName + "Service";
                fields.stream()
                        .filter(field -> field.getName().equals(serviceFullName))
                        .map(JavaField::getType)
                        .map(JavaType::getValue)
                        .forEach(serviceTypeNameSet::add);
            });
            serviceTypeNameSet.stream()
                    .map(s -> s.replace("Service", ""))
                    .map(s -> s.replaceAll("[A-Z]", "_$0").toLowerCase())
                    .map(s -> s.substring(3))
                    .forEach(System.out::println);
        }
    }

    @Test
    void generateDataProcessJson() throws IOException {
        JavaProjectBuilder builder = new JavaProjectBuilder();
        builder.addSourceTree(new File("src/main/java/com/github/lingkai5wu/loveta/controller"));

        Collection<JavaClass> javaClassCollection = builder.getClasses();
        Map<String, List<List<String>>> result = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        for (JavaClass javaClass : javaClassCollection) {
            List<JavaMethod> methods = javaClass.getMethods();
            List<List<String>> list = new ArrayList<>();
            for (JavaMethod method : methods) {
                List<JavaType> parameterTypeList = method.getParameterTypes();
                List<String> parameterTypeNameList = parameterTypeList.stream()
                        .map(JavaType::getValue)
                        .map(typeName -> typeName.equals("int") ? "id" : typeName)
                        .toList();
                list.add(List.of(method.getName(), method.getComment(), parameterTypeNameList.toString(), method.getReturns().getGenericValue()));
            }
            result.put(javaClass.getComment(), list);
        }
        objectMapper.writeValue(new File("target/data-process.json"), result);
    }
}
