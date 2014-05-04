package me.imen.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User: mengmeng.cheng
 * Date: 5/4/14
 * Time: 2:23 PM
 * Email: chengmengmeng@gmail.com
 */
public class FileUtil {
    public static List<String> getFileList(String path) {
        ArrayList<String> list = new ArrayList<String>();

        folderFileList(path, list);

        return list;
    }

    private static void folderFileList(String path, List<String> list) {
        File dir = new File(path);

        if (dir.exists()) {
            if (!dir.isDirectory()) {
                list.add(dir.getAbsolutePath());
            } else {
                File[] files = dir.listFiles();

                if (files != null && files.length > 0) {
                    for (File file : files) {
                        if (file.isDirectory()) {
                            folderFileList(file.getAbsolutePath(), list);
                        } else {
                            list.add(file.getAbsolutePath());
                        }
                    }
                }
            }
        }
    }
}
