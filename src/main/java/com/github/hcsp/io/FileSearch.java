package com.github.hcsp.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) {
        verifyParams(target, text);

        try {
            BufferedReader in = new BufferedReader(new FileReader(target));
            int index = 0;
            String str;

            while ((str = in.readLine()) != null) {
                index++;
                if (str.contains(text)) {
                    return index;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;
    }

    private static void verifyParams(File target, String text) {
        if (!target.isFile()) {
            throw new IllegalArgumentException("File does not exist.");
        }

        if (text == null) {
            throw new IllegalArgumentException("Text does not exist.");
        }
    }

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBB"));
    }
}
