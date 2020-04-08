package com.github.hcsp.io;

import java.io.*;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(target));
            int lineNumber = 1;
            while (reader.ready()) {
                if (reader.readLine().contains(text)) {
                    return lineNumber;
                }
                lineNumber++;
            }
            reader.close();
        } catch (IOException e) {
            throw new IllegalArgumentException("文件不存在或无法读取", e);
        } catch (OutOfMemoryError error) {
            throw new IllegalArgumentException("文件太大了", error);
        }
        return -1;
    }


    public static void main(String[] args) throws IOException {
//        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
//        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBBB"));
        File projectRoot = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        // Create a huge file: ~ 1024 bytes * 1024 * 64 lines = 64 MiB
        File hugeTextFile = new File(projectRoot, "target/hcsp-huge-tmp.txt");
        BufferedWriter writer =
                new BufferedWriter(new FileWriter(hugeTextFile, false), 1024 * 1024);
        for (int i = 1; i <= 1024 * 64; ++i) {
            writer.write(String.format("%01023d\n", i));
        }

        int grep = FileSearch.grep(hugeTextFile, "1");
        System.out.println(grep);


    }
}
