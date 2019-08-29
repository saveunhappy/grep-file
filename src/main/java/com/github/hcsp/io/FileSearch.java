package com.github.hcsp.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(target))) {
            int lineNum = 1;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(text)) {
                    return lineNum;
                }
                lineNum++;
            }
            return -1;
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("文件没有找到");
        } catch (IOException e) {
            throw new UncheckedIOException("文件读取异常", e);
        }
    }

    public static void main(String[] args) throws IOException {
        // File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        // System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBBB"));
        File projectRoot = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        // Create a huge file: ~ 1024 bytes * 1024 * 64 lines = 64 MiB
        File hugeTextFile = new File(projectRoot, "target/hcsp-huge-tmp.txt");
        BufferedWriter writer =
                new BufferedWriter(new FileWriter(hugeTextFile, false), 1024 * 1024);
        for (int i = 1; i <= 1024 * 64; ++i) {
            writer.write(String.format("%01023d\n", i));
        }
        System.out.println(FileSearch.grep(hugeTextFile, "55555"));

    }
}
