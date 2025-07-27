package com.in4java.io;

import lombok.SneakyThrows;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

public class FilesReadTest {

    @SneakyThrows
    public static void main(String[] args) {
        String path =  FilesReadTest.class.getClassLoader().getResource("file/filesRead.txt").getPath();
        List<String> lines = Files.readAllLines(new  File(path).toPath() );
        lines.forEach(System.out::println);
    }

}
