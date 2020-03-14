package com.github.hcsp.io;

import java.io.*;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception

    public static int grep(File target, String text) {
        int lineCount = 1;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(target), "UTF-8"))) {
            String s;
            while ((s = br.readLine()) != null) {
                if (s.contains(text)) {
                    return lineCount;
                }
                lineCount++;
            }
            return -1;
        } catch (OutOfMemoryError e) {
            throw new FileTooBigError("这个文件太大了");
        } catch (IOException e) {
            throw new IllegalArgumentException("名为" + target + "的文件找不到", e);
        }
    }

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBB"));
    }

    public static class FileTooBigError extends OutOfMemoryError {
        public FileTooBigError(String message) {
            super(message);
        }
    }

}
