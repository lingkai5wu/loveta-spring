package com.github.lingkai5wu.loveta;

import cn.dev33.satoken.annotation.SaCheckPermission;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.reflections.scanners.Scanners.MethodsAnnotated;

class ReflectionTest {

    @Test
    void getPermissionCodes() {
        Set<String> permissionCodeSet = new HashSet<>();
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .forPackage("com.github.lingkai5wu.loveta.controller")
                        .setScanners(MethodsAnnotated));
        Set<Method> methodSet =
                reflections.get(MethodsAnnotated.with(SaCheckPermission.class).as(Method.class));
        methodSet.forEach(method -> {
            SaCheckPermission saCheckPermission = method.getAnnotation(SaCheckPermission.class);
            List<String> values = List.of(saCheckPermission.value());
            permissionCodeSet.addAll(values);
        });
        permissionCodeSet.forEach(System.out::println);
    }
}
