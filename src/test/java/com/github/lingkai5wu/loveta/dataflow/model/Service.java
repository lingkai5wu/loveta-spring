package com.github.lingkai5wu.loveta.dataflow.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Service {

    private String name;

    private String comment;

    private String packageName;

    private String className;

    private String classType;

    private List<Method> methodList;

    private String dataTableName;

    private String dataTableComment;
}
