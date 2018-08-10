package com.auto.common.utils;

import java.io.File;

/**
 * Created by veronica_lapunka on 8/1/18.
 */
public class FileUtils {

    public static void deleteRecursive(File path) {
        File[] c = path.listFiles();
        for (File file : c) {
            if (file.isDirectory()) {
                deleteRecursive(file);
                file.delete();
            } else {
                file.delete();
            }
        }
        path.delete();
    }

}
