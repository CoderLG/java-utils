package lg.utils;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.geotools.geometry.jts.JTSFactoryFinder;

import java.math.BigDecimal;

/**
 * author: LG
 * date: 2020-04-07 17:35
 * desc:
 *   多边形关系
 *   https://www.cnblogs.com/duanxingxing/p/5150487.html
 *
 *
 */
public class GeoUtils {

    private static GeometryFactory geometryFactory = new GeometryFactory();
    private static WKTReader reader = new WKTReader(JTSFactoryFinder.getGeometryFactory());

    /**
     * 创建一个点
     */
    public static Geometry createPoint(String wktPoint) {
        Geometry res = null;
        try {
            res = reader.read(wktPoint);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }


    /**
     * 创建一条线
     */
    public static Geometry createLine(String line) {
        Geometry res = null;
        try {
            res = reader.read(line);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }

    //创建一个圆
    private static Coordinate[] createCoords(double x, double y, final double RADIUS ){
        final int SIDES = 32; // 确定边数
        Coordinate coords[] = new Coordinate[SIDES + 1];
        for (int i = 0; i < SIDES; i++) {
            double angle = ((double) i / (double) SIDES) * Math.PI * 2.0;
            double dx = Math.cos(angle) * RADIUS;
            double dy = Math.sin(angle) * RADIUS;
            coords[i] = new Coordinate((double) x + dx, (double) y + dy);
        }
        coords[SIDES] = coords[0];
        return coords;
    }

    /**
     * 创建一个多边形面
     */
    private static  Geometry createPolygon(String poly){
        Geometry res = null;
        try {
            res = reader.read(poly);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 注意：
     * 这个圆并不是地图上的圆
     * 没有经纬度的换算
     * 他们的单位都是米
     * 将半径加大，此圆会超出地图
     *
     * 想要得到正确的地图上的圆，可用空间地理数据库，得到此polygon
     * https://blog.csdn.net/weixin_30823227/article/details/97665744
     *
     * 如果非要自己画，这里提供一个思路
     * https://blog.csdn.net/QIJINGBO123/article/details/93782397
     *
     * 创建一个圆环
     *
     */
    public static Geometry createCircleRing(double x, double y, final double RADIUS) {
        Coordinate[] coords = createCoords(x, y, RADIUS);
        Geometry res = geometryFactory.createLinearRing(coords); // 画一个环线
        return  res;
    }

    /**
     * 创建一个圆面
     * @param x
     * @param y
     * @param RADIUS
     * @return
     */
    public static Geometry createCircle(double x, double y, final double RADIUS) {
        Coordinate[] coords = createCoords(x, y, RADIUS);
        LinearRing ring = geometryFactory.createLinearRing(coords); // 画一个环线
        Geometry circle = geometryFactory.createPolygon(ring, null); // 生成一个面
        return circle;
    }


    /**
     * 根据两个 经纬度计算航向
     * 航向为 0 到 360
     *  0、360为向上 90为向右  180为向下
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     *
     * x1 y1 为老点
     * x2 y2 为新点
     */
    public static double azimuthAngle(double x1, double y1, double x2, double y2) {
        double dx, dy, angle = 0;
        dx = x2 - x1;
        dy = y2 - y1;
        if (x2 == x1) {
            angle = Math.PI / 2.0;
            if (y2 == y1) {
                angle = 0.0;
            } else if (y2 < y1) {
//                冗余修改
//                angle = 2.0 * Math.PI / 2.0;
                angle = Math.PI;
            }else{
                angle = 0.0;
            }
        } else if ((x2 > x1) && (y2 > y1)) {
            angle = Math.atan(dx / dy);
        } else if ((x2 > x1) && (y2 <= y1)) {
            angle = Math.PI / 2 + Math.atan(-dy / dx);
        } else if ((x2 < x1) && (y2 < y1)) {
            angle = Math.PI + Math.atan(dx / dy);
        } else if ((x2 < x1) && (y2 >= y1)) {
            angle = 3.0 * Math.PI / 2.0 + Math.atan(dy / -dx);
        }
        BigDecimal re = new BigDecimal(Double.toString((angle * 180 / Math.PI)));
        re = re.setScale(6, BigDecimal.ROUND_HALF_UP);
        return re.doubleValue();
    }


    public static void main(String[] args) throws ParseException {
        String wktPoint = "POINT (75 75)";
        String wktLine = "LINESTRING (50 50,50 100, 100 100,100 50 )";
        String wktCloseLine = "LINESTRING (50 50,50 100, 100 100,100 50,50 50 )";
        String wktPoly = "POLYGON ((50 50,50 100 , 100 100,100 50 ,50 50))";



        Geometry point = (Geometry) createPoint(wktPoint);

        Geometry line = createLine(wktLine);

        Geometry ring = createLine(wktCloseLine);

        Geometry polygon = createPolygon(wktPoly);

        Geometry circleRing = createCircleRing(0, 0, 107);

        Geometry circle = createCircle(0, 0, 107);

        System.out.println(    line.contains(point));
        System.out.println(    ring.contains(point));
        System.out.println(    polygon.contains(point));
        System.out.println(    circleRing.contains(point));
        System.out.println(    circle.contains(point));
    }


}
