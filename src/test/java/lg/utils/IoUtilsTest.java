package lg.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * author: LG
 * date: 2020-03-15 20:19
 * desc:
 */
public class IoUtilsTest {

    @Test
    public void readResourcesFile() throws Exception {
        String tmpPoint = "MULTIPOINT(122.585600805 30.2024739450002)";
        if(tmpPoint.contains("MULTIPOINT"))
            tmpPoint = "POINT"+tmpPoint.substring(tmpPoint.indexOf("("));

        System.out.println(tmpPoint);
    }
}