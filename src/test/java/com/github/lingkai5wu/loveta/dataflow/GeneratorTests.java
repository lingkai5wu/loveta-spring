package com.github.lingkai5wu.loveta.dataflow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class GeneratorTests {

    private final ApplicationContext applicationContext;

    public GeneratorTests(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Test
    void generate() {
        DataFlowDiagramGenerator generator = applicationContext.getBean(DataFlowDiagramGenerator.class);
        generator.generate();
    }
}
