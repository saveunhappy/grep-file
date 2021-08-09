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
        String line;
        int lineNumber = 0;
        try (LineIterator it = FileUtils.lineIterator(target, "UTF-8")) {
            while (it.hasNext()) {
                lineNumber++;
                line = it.nextLine();
                if (line.replaceFirst("^0+(?!$)", "").equals(text)) {
                    return lineNumber;
                }
            }
            return -1;
        } catch (IOException e) {
            throw new IllegalArgumentException("file does not exist or reading error");
        }
    }

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBB"));
    }
}
