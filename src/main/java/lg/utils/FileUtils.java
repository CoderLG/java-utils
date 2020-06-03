package lg.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Calendar;

/**
 * author: LG
 * date: 2019-11-07 16:42
 * desc:
 * 对文件的操作
 * 用的的时候在细琢
 *
 * 传参都为文件的绝对路径
 */
@Slf4j
public class FileUtils {
    /**
     * 创建文件夹
     * @param destDirName
     * @return
     */
    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            log.error("创建目录" + destDirName + "失败，目标目录已经存在");
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        //创建目录
        if (dir.mkdirs()) {
            log.error("创建目录" + destDirName + "成功！");
            return true;
        } else {
            log.error("创建目录" + destDirName + "失败！");
            return false;
        }

    }

    /**
     * 创建文件
     * @param destFileName
     * @return
     */
    public static boolean createFile(String destFileName) {
        File file = new File(destFileName);
        if(file.exists()) {
            log.error("创建单个文件" + destFileName + "失败，目标文件已存在！");
            return false;
        }
        if (destFileName.endsWith(File.separator)) {
            System.out.println("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
            return false;
        }
        //判断目标文件所在的目录是否存在
        if(!file.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            log.error("目标文件所在目录不存在，正在创建它！");
            if(!file.getParentFile().mkdirs()) {
                log.error("创建目标文件所在目录失败！");
                return false;
            }
        }
        //创建目标文件
        try {
            if (file.createNewFile()) {
                log.error("创建单个文件" + destFileName + "成功！");
                return true;
            } else {
                log.error("创建单个文件" + destFileName + "失败！");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("创建单个文件" + destFileName + "失败！" + e.getMessage());
            return false;
        }

    }

    /**
     * 删除文件夹（递归） 、文件
     * @param destFileName
     * @return
     */
    public static boolean deleteDir(String destFileName) {
        File dir = new File(destFileName);
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(dir.getPath()+"\\" + children[i]);
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /**
     * 文件的创建时间  修改时间    类型
     * @param destFileName
     */
    public static void detail(String destFileName) {
        File dir = new File(destFileName);
        Calendar cal = Calendar.getInstance();
        if (dir.isDirectory()) {
            System.out.println("Directory " + destFileName);
            System.out.println("------------------------------");
            System.out.println("Name\tTime created\tTime modified\tType");

            //String[] children = dir.list();
            File[] files = dir.listFiles();
            File file = null;
            FileTime time = null;
            for (int i =0; i<files.length;i++) {
                file = files[i];
                if (file.isDirectory()) {
                    System.out.print(file.getName() + "\t");
                    try {
                        time = Files.readAttributes(Paths.get(files[i].getAbsolutePath()), BasicFileAttributes.class).creationTime();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println(TimeUtils.longToStr(time.toInstant()) + "\t" + TimeUtils.longToStr(file.lastModified()) + "\t" + "d");

                } else {
                    System.out.print(file.getName() + "\t");
                    try {
                        time = Files.readAttributes(Paths.get(files[i].getAbsolutePath()), BasicFileAttributes.class).creationTime();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(TimeUtils.longToStr(time.toInstant()) + "\t" + TimeUtils.longToStr(file.lastModified()) + "\t" + "f");
                }
            }
        }

    }

    /**
     * 存在此文件
     * @param path
     * @return
     */
    public static boolean fileExist(String path) {
        File file = new File(path);
        try {
            if (!file.exists()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            e.printStackTrace();
        }

        return false;
    }

}
