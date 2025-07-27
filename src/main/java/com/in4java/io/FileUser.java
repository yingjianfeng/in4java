package com.in4java.io;

import lombok.Data;

@Data
public class FileUser {
    String name;
    Integer age;
    FileUser(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
