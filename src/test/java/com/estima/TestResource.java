package com.estima;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.util.stream.Collectors.joining;

public class TestResource {

    private final String content;

    public TestResource(String path) throws IOException {
        this.content = new BufferedReader(new InputStreamReader(new ClassPathResource(path).getInputStream())).lines().collect(joining("\n"));
    }

    public String asString() {
        return content;
    }
}