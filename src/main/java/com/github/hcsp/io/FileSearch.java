package com.github.hcsp.io;

import java.io.*;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) {
        RandomAccessFile randomAccessFile = null;
        int i = -1, k = 0;
        try {
            randomAccessFile = new RandomAccessFile(target, "r");
            String readline = null;
            while ((readline = randomAccessFile.readLine()) != null) {
                if (readline.contains(text)) {
                    i = ++k;
                    break;
                }
                k++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
        return i;
    }

    public static void main(String[] args) throws IOException {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, ""), "5050"));
    }
}
