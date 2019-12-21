package com.github.hcsp.io;
import java.io.*;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) throws IllegalArgumentException {
        if (text==null || "".equals(text)){
            throw new IllegalArgumentException("text参数错误");
        }
        if (target==null){
            throw new IllegalArgumentException("文件参数不能为空");
        }
        if (!target.exists()){
            throw new IllegalArgumentException("文件不存在");
        }
        if (!target.isFile()){
            throw new IllegalArgumentException("不是一个文件");
        }
        if (!target.canRead()){
            throw new IllegalArgumentException("文件无法读取");
        }

        return grepByJava(target, text);
    }

    public static int grepByJava(File target, String text){
        try (BufferedReader br = new BufferedReader(new FileReader(target))){
            String line;
            int number = 0;
            while ( (line = br.readLine()) != null ){
                number++;
                if (line.contains(text)){
                    return number;
                }
            }

        }catch (Exception exp){
            exp.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBB"));
    }
}
