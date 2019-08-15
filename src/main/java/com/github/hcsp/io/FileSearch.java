package com.github.hcsp.io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) {
        verifyParam(target, text);
        LineNumberReader lineNumberReader;
        int index = -1;
        try {
            String s = null;
            lineNumberReader = new LineNumberReader(new FileReader(target));
            while ((s = lineNumberReader.readLine()) != null) {
                if (s.contains(text)) {
                    index = lineNumberReader.getLineNumber();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return index;
    }

    public static void main(String[] args) throws IOException {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBB"));
    }

    public static void verifyParam(File file, String keyword) {
        if (!file.exists()) {
            throw new IllegalArgumentException("文件不存在");
        }
        if (keyword == null || keyword.trim().equals("")) {
            throw new IllegalArgumentException();
        }
    }
}
