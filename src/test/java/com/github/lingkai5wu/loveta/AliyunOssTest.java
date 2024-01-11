package com.github.lingkai5wu.loveta;

import com.github.lingkai5wu.loveta.service.IOssService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.net.URL;

@SpringBootTest
public class AliyunOssTest {
    @Autowired
    private IOssService ossService;

    @Test
    void getGetObjectUrlTest() {
        String objectName = "Hash校验.exe";
        URL objectUrl = ossService.getGetObjectUrl(objectName);
        System.out.println("objectUrl = " + objectUrl);
    }
}
