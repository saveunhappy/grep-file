package com.github.hcsp.io;

import java.io.*;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) {
        int line = -1, i = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(target), 5 * 1024 * 1024)) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                i++;
                if (str.contains(text)) {
                    line = i;
                    break;
                }
            }

        } catch (IOException e) {
            throw new IllegalArgumentException("指定的文件 " + target.getAbsolutePath() + " 不存在或者无法被读取", e);
        }

        return line;
    }

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log1.txt"), "BBB"));
    }
}
