package com.github.hcsp.io;

import java.io.*;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) {
        int result = 0;

        try {
            int line = 0;
            FileReader reader = new FileReader(target);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String str = bufferedReader.readLine();
            while (str != null) {
                line += 1;
                if (str.contains(text)) {
                    result = line;
                    break;
                } else {
                    result = -1;
                }
                str = bufferedReader.readLine();
            }
        } catch (IOException e) {
            if (!target.isFile() || !target.canRead() || !target.exists()) {
                throw new IllegalArgumentException();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBB"));
    }
}
