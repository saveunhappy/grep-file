package com.github.hcsp.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommentCleanerTest {
    @Test
    public void throwExceptionWhenFileNotReadable() throws Exception {
        Assertions.assertThrows(
                IllegalArgumentException.class, () -> FileSearch.grep(new File("inexistent"), ""));
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () ->
                        FileSearch.grep(
                                new File(
                                        System.getProperty(
                                                "basedir", System.getProperty("user.dir"))),
                                ""));
    }

    @Test
    public void returnCorrectValueWhenTargetTextNotFound() {
        File projectRoot = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        Assertions.assertEquals(-1, FileSearch.grep(new File(projectRoot, "log.txt"), "DDD"));
    }

    @Test
    public void canWorkOnHugeFile() throws IOException {
        File projectRoot = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        // Create a huge file: ~ 1024 bytes * 1024 * 64 lines = 64 MiB
        File hugeTextFile = new File(projectRoot, "target/hcsp-huge-tmp.txt");
        BufferedWriter writer =
                new BufferedWriter(new FileWriter(hugeTextFile, false), 1024 * 1024);
        for (int i = 1; i <= 1024 * 64; ++i) {
            writer.write(String.format("%01023d\n", i));
        }

        Assertions.assertEquals(55555, FileSearch.grep(hugeTextFile, "55555"));
    }
}
