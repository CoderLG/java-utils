package lg.utils;

import com.vividsolutions.jts.geom.Geometry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * author: LG
 * date: 2020-06-24 12:32
 * desc:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ShpUtilsTest {



    @Test
    public void test(){
        List<String> list = new ArrayList<>();
        list.add("POLYGON ((116.21278950384274 39.90557982319698, 116.21177234433465 39.90610963061354, 116.21106912279264 39.90264172209895, 116.21399502548638 39.902612822554126, 116.21629305278306 39.905011479365406, 116.21278950384274 39.90557982319698))");
        list.add("POLYGON((113.38185597038 34.54828048706,113.38224220848 34.548355588913,113.38249970055 34.548108825684,113.38237095451 34.54787279129,113.38208127594 34.547786960602,113.38185597038 34.54828048706))");

        List<Geometry> geometryList = new ArrayList<>();
        for (String str : list) {
            Geometry geom = WKTUtil.wktToGeom(str);
            geometryList.add(geom);
        }

        String url = "D:\\geovis\\icenter-output\\geo2shp\\test3.shp";
        ShapeUtil.write2Shape(url, "utf-8", "Polygon", geometryList);
//        ShapeUtil.zipShapeFile(url);


    }

}