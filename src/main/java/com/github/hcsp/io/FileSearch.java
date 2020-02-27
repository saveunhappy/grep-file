package com.github.hcsp.io;

import java.io.*;


public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) {
//用Files.readAllLines方法过不了单元测试的第三题，会抛outOfMemory?
 /*       List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(String.valueOf(target)), StandardCharsets.UTF_8);
            System.out.println("lines*****:" + lines);
            int lineNum = 0;
            for (String line : lines) {
                lineNum++;
                if (line.contains(text)) {
                    return lineNum ;
                }
            }
            return -1;
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }*/

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(target));
            int num = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                num++;
                if (line.contains(text)) {
                    return num;
                }
            }
            return -1;
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
         /*不需要关闭文件吗？
         finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/

    }


    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBB"));
    }
}
