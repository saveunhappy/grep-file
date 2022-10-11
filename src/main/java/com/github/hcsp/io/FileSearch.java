package com.github.hcsp.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(target));
            // 读取每一行内容
            String line = reader.readLine();
            // 标记当前行数
            int i = 1;
            while (line != null) {
                if (line.contains(text)) {
                    return i;
                }
                line = reader.readLine();
                i++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBB"));
    }
}
