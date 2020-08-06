package lg.utils;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WKTUtil {

    public static String geomToWkt(Geometry geometry) {
        String wkt = null;
        WKTWriter writer = new WKTWriter();
        wkt = writer.write(geometry);
        return wkt;
    }

    public static Geometry wktToGeom(String wkt)  {
        Geometry geometry = null;
        WKTReader reader = new WKTReader();
        try {
            geometry = reader.read(wkt);
        } catch (ParseException e) {
            log.error("wktToGeom转换出错",e);
        }
        //geometry.setSRID(4326);
        return geometry;
    }
}