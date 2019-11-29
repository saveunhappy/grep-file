package com.github.hcsp.io;

import java.io.*;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) {
        if (!target.isFile() | !target.canRead()) {
            throw new IllegalArgumentException("file does not exit or can not read!");
        }
        int lineNumber = 1;
        try {
            BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(target)));
            String line;
            while (true) {
                line = bfr.readLine();
                if (line == null) {
                    lineNumber = -1;
                    break;
                } else if (line.contains(text)) {
                    break;
                }
                lineNumber += 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineNumber;
    }

    public static void main(String[] args) throws Exception {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBB"));
    }
}
