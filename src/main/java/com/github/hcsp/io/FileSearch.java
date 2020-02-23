package com.github.hcsp.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) {
        Scanner scan = null;
        try {
            scan = new Scanner(new File(String.valueOf(target)));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        }
        int currentLine = 0;
        while (scan.hasNext()) {
            currentLine++;
            String line = scan.nextLine().toString();
            if (line.contains(text)) {
                return currentLine;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "DDD"));
    }
}
