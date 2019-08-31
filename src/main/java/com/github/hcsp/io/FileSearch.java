package com.github.hcsp.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    private static final int TEXT_NOT_FOUND = -1;

    public static int grep(File target, String text) {
        if (!target.exists() || !target.canRead()) {
            throw new IllegalArgumentException();
        }
        try (Scanner sc = new Scanner(target)) {
            int countLines = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                ++countLines;
                if (line.contains(text)) {
                    return countLines;
                }
            }
            return TEXT_NOT_FOUND;
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBB"));
    }
}
