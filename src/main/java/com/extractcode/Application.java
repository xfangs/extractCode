package com.extractcode;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.IOException;

public class Application {
    static File outFile = new File("d://out.txt");
    static String[] suffixs = {".cs", ".js", ".cshtml"};

    public static void main(String[] args) throws IOException {
        String baseDir = "D:\\gitee\\spockp_master";


        File rootFile = new File(baseDir);
        listChild(rootFile);
    }

    private static void listChild(File parentFile) throws IOException {
        for (File file : parentFile.listFiles()) {
            if (file.getName().equals(".git")) {
                continue;
            }
            if (file.isDirectory()) {
                listChild(file);
            } else {
                if (file.getName().lastIndexOf(".") == -1) {
                    return;
                }
                String suffix = file.getName().substring(file.getName().lastIndexOf("."), file.getName().length());
                if (ArrayUtils.contains(suffixs, suffix)) {
                    byte[] bytes = FileUtils.readFileToByteArray(file);
                    FileUtils.write(outFile, "\n=======================" + file.getPath() + "==================================\n", true);
                    FileUtils.writeByteArrayToFile(outFile, bytes, true);
                }
            }
        }

    }

}
