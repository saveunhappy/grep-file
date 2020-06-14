package com.github.hcsp.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) {
        // 不知道为什么用 FileUtils 读文件过不了测试。以后回看的时候，再来解决。
        try {
//            List<String> lines = FileUtils.readLines(target, StandardCharsets.UTF_8);
//            for (int i = 0; i < lines.size(); i++) {
//                if (lines.get(i).contains(text)) {
//                    return i + 1;
//                }
//            }
            BufferedReader reader = Files.newBufferedReader(target.toPath());
            int lineIndex = 1;
            String lineContent = reader.readLine();
            while (lineContent != null) {
                if (lineContent.contains(text)) {
                    return lineIndex;
                }
                lineContent = reader.readLine();
                lineIndex++;
            }
            reader.close();
            return -1;
        } catch (IOException | OutOfMemoryError e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void main(String[] args){
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBB"));

    }
}
