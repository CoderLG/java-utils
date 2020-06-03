package lg.utils;

import com.sun.management.OperatingSystemMXBean;
import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

import java.lang.management.ManagementFactory;

/**
 * author: LG
 * date: 2020-01-08 15:17
 * desc:
 */
@Slf4j
public class SystemUtils {

    public static void checkSystem() {
        System.out.println("start");
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();
        System.out.println(os);
        log.info("检查 CPU...");
        CentralProcessor processor = hal.getProcessor();
        double[] loadAverage = processor.getSystemLoadAverage(3);
        System.out.println(loadAverage);
        System.out.println(loadAverage.clone().toString());
        System.out.println(loadAverage.length);
        System.out.println(loadAverage[0]);
        System.out.println(loadAverage[1]);
        System.out.println(loadAverage[2]);
    }

    public static void getBaseSystem(int byteToMb){
        // 操作系统级内存情况查询
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        String os = System.getProperty("os.name");
        long physicalFree = osmxb.getFreePhysicalMemorySize() / byteToMb;
        long physicalTotal = osmxb.getTotalPhysicalMemorySize() / byteToMb;
        long physicalUse = physicalTotal - physicalFree;
        System.out.println("操作系统的版本：" + os);
        System.out.println("操作系统物理内存已用的空间为：" + physicalFree + " MB");
        System.out.println("操作系统物理内存的空闲空间为：" + physicalUse + " MB");
        System.out.println("操作系统总物理内存：" + physicalTotal + " MB");

    }

    public static void getSystemTotalThread(){

        // 获得线程总数
        ThreadGroup parentThread;
        int totalThread = 0;
        for (parentThread = Thread.currentThread().getThreadGroup(); parentThread
                .getParent() != null; parentThread = parentThread.getParent()) {
            totalThread = parentThread.activeCount();
        }
        System.out.println("获得线程总数:" + totalThread);

    }

}
