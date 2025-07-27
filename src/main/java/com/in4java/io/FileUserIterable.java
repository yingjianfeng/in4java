package com.in4java.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class FileUserIterable implements Iterable<FileUser> {

    File file;
    public FileUserIterable(File file) {
        this.file = file;
    }

    @Override
    public Iterator<FileUser> iterator() {
        return new FileUserIterator();
    }

    class FileUserIterator implements Iterator<FileUser> {
        int index = 0;
        List<FileUser> fileUsers = load();

        private List<FileUser> load() {
            try {
                List<String> lines = Files.readAllLines(file.toPath() );
                return lines.stream().map(line -> {
                    line = line.trim();
                    String[] split = line.split(",");
                    return new FileUser(split[0], Integer.parseInt(split[1]));
                }).collect(Collectors.toList());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public boolean hasNext() {
            return index<fileUsers.size();
        }

        @Override
        public FileUser next() {
            return fileUsers.get(index++);
        }
    }
}
