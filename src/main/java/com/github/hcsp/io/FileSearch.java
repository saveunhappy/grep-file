package com.github.hcsp.io;

import java.io.*;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) {
        if (!target.exists() || !target.isFile()) {
            throw new IllegalArgumentException();
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(target))) {
            int lineNumber = 1;
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                } else {
                    if (line.contains(text)) {
                        return lineNumber;
                    }
                }
                lineNumber++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot find file " + target.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Fail to read from " + target.getAbsolutePath());
        }

        return -1;
    }

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBB"));
    }
}
