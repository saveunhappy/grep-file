package com.github.hcsp.io;

import java.io.*;
import java.util.List;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) {
        if (!target.exists() || target.isDirectory()) {
            throw new IllegalArgumentException();
        }
        List<String> lines;
        try {
            FileReader fileReader = new FileReader(target);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int i = 1;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(text)) {
                    return i;
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBB"));
    }
}
