package com.github.hcsp.io;

import java.io.*;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) throws IllegalArgumentException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(target))) {
            int lineNum = 1;
            while (bufferedReader.ready()) {
                if (bufferedReader.readLine().contains(text)) {
                    return lineNum;
                }
                lineNum++;
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("文件不存在或者无法读取", e);
        } catch (OutOfMemoryError error) {
            throw new IllegalArgumentException("文件太大了", error);
        }

        return -1;
    }

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBB"));
    }
}
