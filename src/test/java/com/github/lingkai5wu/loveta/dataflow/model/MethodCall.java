package com.github.lingkai5wu.loveta.dataflow.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class MethodCall {

    private String name;

    private String scope;

    private String serviceClassType;

    private List<String> parameterTypeList;

    private String returnType;
}
