package com.github.hcsp.io;

//import javax.sound.sampled.Line;

import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.HashMap;
//import java.util.Map;

//import static java.nio.charset.StandardCharsets.UTF_8;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception

    public static int grep(File target, String text) {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(target));
            String read;
            int line = 0;
            while ((read = br.readLine()) != null) {
                line++;
                if (read.contains(text)) {
                    return line;
                }
            }
            return -1;
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

//    public static int grep2(File target, String text) {
//        if (!target.exists()) {
//            throw new IllegalArgumentException();
//        }
//        Map<String, Integer> data = new HashMap<>();
//        int i = 0;
//        try {
//            for (String readAllLine : Files.readAllLines(Paths.get(target.getCanonicalPath()), UTF_8)) {
//                i++;
//                data.put(readAllLine, i);
//            }
//            if (data.get(text) != null) {
//                return data.get(text);
//            } else {
//                return -1;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return data.get(text);
//    }

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBB"));
    }
}
