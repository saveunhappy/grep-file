package com.github.hcsp.io;

import java.io.*;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) {
        BufferedReader br = null;
        int flagline = 0;

        try {
            String lineContent = null;
            br = new BufferedReader(new FileReader(target));
            while ((lineContent = br.readLine()) != null) {
                flagline++;
                if (lineContent.contains(text)) {
                    return flagline;
                }
            }
            return -1;
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("这个文件不存在", e);
        } catch (IOException e) {
            throw new IllegalArgumentException("这个文件无法被读取", e);
        } catch (OutOfMemoryError e){
            throw new OutOfMemoryError("大小超出内存容量");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBB"));
    }
}
