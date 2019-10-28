package com.github.hcsp.io;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) {
        try {
            Scanner sc = new Scanner(target);
            //创建一个变量来记录行数
            int lineContainsText = 1;
            while (sc.hasNextLine()) {
                //每次只读取一行，不会累积文件
                String line = sc.nextLine();
                if (line.contains(text)) {
                    return lineContainsText;
                }
                lineContainsText++;
            }
            return -1;
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBB"));
    }
}
