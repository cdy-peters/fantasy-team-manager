package com.example.server.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

@Component
public class CsvChangeDetector {

    private static final String CSV_FILE_PATH = "premier-player-23-24.csv";
    private static FileTime lastModifiedTime;

    public boolean hasFileChanged() {
        try {
            ClassPathResource resource = new ClassPathResource(CSV_FILE_PATH);
            BasicFileAttributes attrs = Files.readAttributes(resource.getFile().toPath(), BasicFileAttributes.class);
            FileTime modifiedTime = attrs.lastModifiedTime();

            if (lastModifiedTime == null || !modifiedTime.equals(lastModifiedTime)) {
                lastModifiedTime = modifiedTime;
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}