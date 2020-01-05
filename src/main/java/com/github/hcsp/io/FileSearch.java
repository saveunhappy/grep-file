package com.github.hcsp.io;

import java.io.*;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(target));
            int rows = 1;
            String bufferedstring;
            while ((bufferedstring = bufferedReader.readLine()) != null) {
                if (bufferedstring.contains(text)) {
                    return rows;
                }
                rows++;
            }
            return -1;
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File: " + target + " can not be found!");
        } catch (IOException e) {
            throw new IllegalArgumentException("File: " + target + " have a IOException.");
        }
    }

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBB"));
    }
}
