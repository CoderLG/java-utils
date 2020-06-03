package lg.utils;

/**
 * author: LG
 * date: 2020-01-08 14:38
 * desc:
 * 打印jvm 内存情况
 */
public class JvmUtil {

    /**
     * 打印jvm 内存情况
     */
    public static void printJvmSpace (int byteToMb){

        // 虚拟机级内存情况查询
        long vmFree = 0;
        long vmUse = 0;
        long vmTotal = 0;
        long vmMax = 0;

        Runtime rt = Runtime.getRuntime();
        vmMax = rt.maxMemory() / byteToMb;
        vmFree = rt.freeMemory() / byteToMb;
        vmTotal = rt.totalMemory() / byteToMb;
        vmUse = vmTotal - vmFree;
        System.out.println("JVM最大内存空间为：" + vmMax + " MB");
        System.out.println("JVM堆总内存空间为：" + vmTotal + " MB");
        System.out.println("JVM堆空闲空间为：" + vmFree + " MB");
        System.out.println("JVM堆已用的空间为：" + vmUse + " MB");
    }
}
