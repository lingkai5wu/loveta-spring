package com.github.lingkai5wu.loveta;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class DataDictionaryTests {
    private static void write(String fileName, StringBuilder stringBuilder) throws IOException {
        File file = new File("target/" + fileName + ".html");
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
        writer.write(stringBuilder.toString());
        writer.write("""
                </body>
                </html>
                """);
        writer.close();
    }

    @Test
    void generateDateItem() throws IOException {
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

        write("data-item", sb);
    }

    @Test
    void generateDataFlow() throws IOException {
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
            String comment = javaClass.getComment();
            int i = comment.lastIndexOf(" ");
            if (i != -1) {
                comment = comment.substring(0, i);
            }
            sb.append("<td>").append(comment).append("</td>");
            if (javaClass.getName().endsWith("VO")) {
                sb.append("<td>系统</td>");
                sb.append("<td>用户</td>");
            } else {
                sb.append("<td>用户</td>");
                sb.append("<td>系统</td>");
            }
            String joined = fields.stream()
                    .map(JavaField::getComment)
                    .collect(Collectors.joining("、"));
            sb.append("<td>").append(joined).append("</td>");
            sb.append("</tr>");
        }
        sb.append("</table>\n");

        write("data-flow", sb);
    }

    @Test
    void generateDataProcess() throws IOException {
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
                generic = generic
                        .replace("<", "&lt;")
                        .replace(">", "&gt;");
                sb.append("<td>").append(generic).append("</td>");
                sb.append("</tr>");
            }
        }
        sb.append("</table>\n");

        write("data-process", sb);
    }
}
