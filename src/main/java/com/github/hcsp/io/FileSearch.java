package com.github.hcsp.io;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) {
        InputStream is = null;
        try {
            is = new FileInputStream(target);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("手动抛出IllegalArgumentException");
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        String str = null;
        int index = 0;
        while (true) {
            try {
                str = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (str == null) {
                break;
            } else {
                index += 1;
                if (str.contains(text)) {
                    return index;
                }
//                System.out.println(str);
//                System.out.println(index);
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log1.txt"), "DDD"));
    }
}
