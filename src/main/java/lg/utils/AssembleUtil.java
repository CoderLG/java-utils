package lg.utils;

import java.io.File;
import java.util.*;

/**
 * author: LG
 * date: 2020-01-06 15:39
 * desc:
 * 对集合的操作
 * 排序 交并补
 */
public class AssembleUtil {

    public  static void fileAction(){
        HashSet<Object> interSectionSet = new HashSet<>();
        HashSet<Object> importFileNameSet = new HashSet<>();
        HashSet<Object> desFileNameSet = new HashSet<>();

        interSectionSet.addAll(importFileNameSet);
        interSectionSet.removeAll(importFileNameSet);

        //interSectionSet 是否包含全部的desFileNameSet
        interSectionSet.containsAll(desFileNameSet);

        //交集 赋值给interSectionSet
        interSectionSet.retainAll(desFileNameSet);
    }

    public static void listSort(){
        File importFileDir = new File("path");
        File[] files = importFileDir.listFiles();
        //按照文件大小进行排序，尽量先导入tif，img等
        List<File> fileList = Arrays.asList(files);
        Collections.sort(fileList, new Comparator<File>() {
            @Override
            public int compare(File f1, File f2) {
                long diff = f1.length() - f2.length();
                if (diff > 0) {
                    return -1;
                } else if (diff == 0) {
                    return 0;
                } else {
                    return 1;//递减排序
                }
            }

            public boolean equals(Object obj) {
                return true;
            }
        });
    }

    public void arrySort(){
        File tempDirFile = new File("");
        File[] partFiles = tempDirFile.listFiles((f, name) -> name.endsWith(".part"));

        Arrays.sort(partFiles, (File f1, File f2) -> {
            String name1 = f1.getName();
            String name2 = f2.getName();
            if (name1.length() < name2.length()) {
                return -1;
            } else if (name1.length() > name2.length()) {
                return 1;
            } else {
                return name1.compareTo(name2);
            }
        });

    }
}
