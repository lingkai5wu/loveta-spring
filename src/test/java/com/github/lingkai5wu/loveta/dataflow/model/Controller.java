package com.github.lingkai5wu.loveta.dataflow.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class Controller {

    private String name;

    private String comment;

    private Map<String, String> serviceFieldMap;

    private List<Method> methodList;
}

