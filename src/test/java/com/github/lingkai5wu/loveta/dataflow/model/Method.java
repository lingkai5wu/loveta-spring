package com.github.lingkai5wu.loveta.dataflow.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Method {

    private String name;

    private String comment;

    private List<String> parameterTypeList;

    private String returnType;

    private List<MethodCall> methodCallList;

    private List<MethodCall> serviceMethodCallList;
}
