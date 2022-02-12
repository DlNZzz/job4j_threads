package ru.job4j.io;

import java.io.*;

public class Del {
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public String getContent() {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader
                     = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            while (bufferedReader.ready()) {
                stringBuilder.append(bufferedReader.readLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public String getContentWithoutUnicode() {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader
                     = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            while (bufferedReader.ready()) {
                String data = bufferedReader.readLine();
                for (int i = 0; i < data.length(); i += 1) {
                    char c = data.charAt(i);
                    if (c < 0x80) {
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

    public void saveContent(String content) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(content);
            bufferedWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}