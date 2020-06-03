package lg.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * author: LG
 * date: 2020-04-26 15:19
 * desc:
 */
public class TimeUtilsTest {

    @Test
    public void format() {
        Long aLong = TimeUtils.toUtcTime(System.currentTimeMillis());
        System.out.println(aLong);
        System.out.println(TimeUtils.longToStr(aLong));
        System.out.println(TimeUtils.toBjTime(aLong));
        System.out.println(TimeUtils.longToStr(TimeUtils.toBjTime(aLong)));
    }
}