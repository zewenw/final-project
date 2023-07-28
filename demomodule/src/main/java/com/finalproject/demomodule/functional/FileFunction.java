package com.finalproject.demomodule.functional;


import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileFunction {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Files.lines(Paths.get(ResourceUtils.getFile("classpath:file.txt").getAbsolutePath())).forEach(System.out::println);
    }
}
