package com.github.lingkai5wu.loveta;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ERTests {

    @Test
    void generateER() throws IOException {
        JavaProjectBuilder builder = new JavaProjectBuilder();
        builder.addSourceTree(new File("src/main/java/com/github/lingkai5wu/loveta/model/po"));

        Collection<JavaClass> javaClassCollection = builder.getClasses();
        Map<String, List<String>> result = new HashMap<>();
        for (JavaClass javaClass : javaClassCollection) {
            List<JavaField> fields = javaClass.getFields();
            List<String> list = new ArrayList<>();
            for (JavaField field : fields) {
                list.add(field.getComment());
            }
            result.put(javaClass.getComment(), list);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.writeValue(new File("target/er.json"),
                result);
    }
}
