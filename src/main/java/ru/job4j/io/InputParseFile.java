package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class InputParseFile {
    private final File file;

    public InputParseFile(File file) {
        this.file = file;
    }

    public synchronized String content(Predicate<Character> filter) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader
                     = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            while (bufferedReader.ready()) {
                String data = bufferedReader.readLine();
                for (int i = 0; i < data.length(); i += 1) {
                    char c = data.charAt(i);
                    if (filter.test(c)) {
                        stringBuilder.append(c);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}