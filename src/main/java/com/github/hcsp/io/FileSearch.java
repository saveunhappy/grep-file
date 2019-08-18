package com.github.hcsp.io;

import java.io.BufferedReader;
import java.io.File;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//import org.apache.commons.io.IOUtils;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(target));
            String line;
            int index = 0;
            while ((line = br.readLine()) != null) {
                ++index;
                if (line.contains(text)) {
                    return index;
                }
            }
            return -1;
//            System.out.println(br.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("文件未找到");
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
//        return 1;
//        int index = 0;
//        boolean isFound = false;
//        InputStream is;
//        try {
//            is = new FileInputStream(target);
//        } catch (FileNotFoundException e) {
//            throw new IllegalArgumentException("文件未找到");
//        }
//
//        List<String> strings;
//        try {
//            strings = IOUtils.readLines(is, "UTF-8");
//        } catch (IOException e) {
//            throw new IllegalArgumentException();
//        }
//        for (String string : strings) {
//            index++;
//            if (string.equals(text)) {
//                isFound = true;
//                break;
//            }
//        }
//        return isFound ? index : -1;

    }

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBB"));
    }
}
