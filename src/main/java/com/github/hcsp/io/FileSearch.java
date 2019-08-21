package com.github.hcsp.io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) {
        int result = 0;
        int lines = 0;
        try {
            LineIterator it = FileUtils.lineIterator(target, "UTF-8");
            while (it.hasNext()) {
                String line = it.nextLine();
                lines++;
                if (line != null && line.contains(text)) {
                    result = lines;
                }
            }
            if (result == 0) {
                result = -1;
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(target.getName() + "文件不存在或者无法被读取");
        }
    }

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBB"));
    }
}
