package com.qman.web.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import org.springframework.core.io.ClassPathResource;

public class FileUtils {

    public static String readFile(String filePath) {
        try {
            InputStream resource = new ClassPathResource(filePath).getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource));
            String result = reader.lines().collect(Collectors.joining("\n"));
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
