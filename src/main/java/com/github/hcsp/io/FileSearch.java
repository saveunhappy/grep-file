package com.github.hcsp.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileSearch {
    /*
     * 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
     * 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。 请不要让这个方法抛出checked exception
     */
    public static int grep(File target, String text) {
        int count = 0;
        String str;
        try {
            if (!target.exists() || !target.isFile()) {
                throw new IllegalArgumentException();
            }
            BufferedReader buffer = Files.newBufferedReader(target.toPath());
            while ((str = buffer.readLine()) != null) {
                count++;
                if (str.contains(text)) {
                    return count;
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBB"));
    }
}
