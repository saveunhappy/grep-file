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
        FileReader istream = null;
        String line = "";
        int lineNum = 1;
        try {
            istream = new FileReader(target);
            BufferedReader in = new BufferedReader(istream);
            for (int i = 1; (line = in.readLine()) != null && !line.contains(text); i++) {
                lineNum = i+1;
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return line == null? -1: lineNum;
    }

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBB"));
    }
}
