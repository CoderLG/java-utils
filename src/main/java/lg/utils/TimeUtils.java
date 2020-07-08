package lg.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * author: LG
 * date: 2019-11-07 17:41
 * desc:
 *
 *
 * long 转 格式化时间
 * 格式化时间 转 long
 *
 * utc（格林威治时间） 转  北京时间
 * 北京时间 转  utc
 *
 *
 * 参考博客：
 *  https://blog.csdn.net/weixin_40861707/article/details/79591562
 */
@Slf4j
public class TimeUtils {

    public static  String getCurrentTime(){
        return longFormatStr(System.currentTimeMillis());
    }

    /**
     * 获取时间差
     * 单位毫秒
     * @param start
     * @return
     */
    public static Long subWithCurrentTime(Long start){
        long end = System.currentTimeMillis();
        return  (end - start) / 1000;
    }

    public static String longFormatStr(Long time){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        cal.setTimeInMillis(time);
        String format = formatter.format(cal.getTime());
        return format;
    }

    public static String longFormatStr(Instant time){
        String format =  DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss.SSS").format(LocalDateTime.ofInstant(time, ZoneId.systemDefault()));
        return format;
    }

    public static Long strFormatLong(String time){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date parse = null;
        try {
            parse = formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse.getTime();
    }

    /**
     * 减 8个小时
     * @param time
     * @return
     */
    public static Long toUtcTime(Long time) {
        /** long时间转换成Calendar */
        Calendar calendar = Calendar.getInstance();

        // time 应为毫秒
        calendar.setTimeInMillis(time);

//        Date date = (Date) calendar.getTime();
//        long localTimeInMillis = date.getTime();
//        calendar.setTimeInMillis(localTimeInMillis);

        /** 取得时间偏移量 */
        int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
        /** 取得夏令时差 */
        int dstOffset = calendar.get(Calendar.DST_OFFSET);
        /** 从本地时间里扣除这些差量，即可以取得UTC时间 */
        calendar.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        /** 取得的时间就是UTC标准时间 */
        Date utcDate = new Date(calendar.getTimeInMillis());
        return utcDate.getTime();
    }

    /**
     * 加8 个小时
     * @param time
     * @return
     */
    public static Long toBjTime(Long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date t = new Date(time);
        String dateStr = formatter.format(t);

        TimeZone utcZone = TimeZone.getTimeZone("UTC");
        formatter.setTimeZone(utcZone);

        try {
            Date date = formatter.parse(dateStr);
            return date.getTime();
        } catch (Exception e) {
            log.error("时间转换出现问题",e);
            return null;
        }


    }

}
