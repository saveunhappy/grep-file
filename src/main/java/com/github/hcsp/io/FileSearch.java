package com.github.hcsp.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    private static final int NOT_FOUND = -1;

    public static int grep(File target, String text) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(target));
            String line;
            int number = 1;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(text)) {
                    return number;
                }
                number += 1;
            }
            bufferedReader.close();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new IllegalArgumentException();
                }
            }
        }
        return NOT_FOUND;
    }

    public static void main(String[] args) throws IOException {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBB"));
    }
}
