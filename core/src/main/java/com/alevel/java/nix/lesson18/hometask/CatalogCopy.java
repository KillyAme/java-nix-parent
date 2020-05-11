package com.alevel.java.nix.lesson18.hometask;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CatalogCopy {
    public static void main(String[] args) throws IOException {
        Path source = Paths.get("C:\\users\\zv\\dir1");
        Path dest = Paths.get("C:\\users\\zv\\dir2");

        doCopy(source, dest);
    }

    public static void doCopy(Path source, Path dest) throws IOException {
        Files.walk(source).forEach(cp -> copy(cp, dest.resolve(source.relativize(cp))));
    }

    public static void copy(Path from, Path to) {
        try {
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
