package com.github.hcsp.io;


import java.io.*;
import java.nio.file.Files;


public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) {
        int result = -1;
        if (!target.exists() || !target.isFile() || !Files.isReadable(target.toPath())) {
            throw new IllegalArgumentException();
        }
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(target));
            result = 0;
            while (true) {
                String contentLine = bufferedReader.readLine();
                if (contentLine != null) {
                    result++;
                    if (contentLine.contains(text)) {
                        break;
                    }
                } else {
                    result = -1;
                    break;
                }
            }

        } catch (IllegalArgumentException | OutOfMemoryError e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBBB"));
    }
}
