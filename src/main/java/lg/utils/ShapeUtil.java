package lg.utils;


import com.vividsolutions.jts.geom.*;
import org.geotools.data.FeatureWriter;
import org.geotools.data.Transaction;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import java.io.File;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShapeUtil {


    /**
     * 生成shape文件
     *
     * @param shpPath 生成shape文件路径（包含文件名称）
     * @param encode  编码
     * @param geoType 图幅类型，Point和Rolygon
     * @param geoms   图幅集合
     */
    public static void write2Shape(String shpPath, String encode, String geoType, List<Geometry> geoms) {
        try {
            //创建shape文件对象
            File file = new File(shpPath);
            Map<String, Serializable> params = new HashMap<>();
            params.put(ShapefileDataStoreFactory.URLP.key, file.toURI().toURL());
            ShapefileDataStore ds = (ShapefileDataStore) new ShapefileDataStoreFactory().createNewDataStore(params);
            //定义图形信息和属性信息
            SimpleFeatureTypeBuilder tb = new SimpleFeatureTypeBuilder();
            tb.setCRS(DefaultGeographicCRS.WGS84);
            tb.setName("shapefile");

            if ("Polygon".equals(geoType)) {
                tb.add("the_geom", Polygon.class);
            } else if ("MultiPolygon".equals(geoType)) {
                tb.add("the_geom", MultiPolygon.class);
            } else if ("Point".equals(geoType)) {
                tb.add("the_geom", Point.class);
            } else if ("MultiPoint".equals(geoType)) {
                tb.add("the_geom", MultiPoint.class);
            } else if ("LineString".equals(geoType)) {
                tb.add("the_geom", LineString.class);
            } else if ("MultiLineString".equals(geoType)) {
                tb.add("the_geom", MultiLineString.class);
            } else {
                throw new Exception("Geometry中没有该类型：" + geoType);
            }
            tb.add("name", String.class);
            ds.createSchema(tb.buildFeatureType());
            //设置编码
            Charset charset = Charset.forName(encode);
            ds.setCharset(charset);
            //设置Writer
            FeatureWriter<SimpleFeatureType, SimpleFeature> writer = ds.getFeatureWriter(ds.getTypeNames()[0], Transaction.AUTO_COMMIT);
            int tmp = 1;
            for (Geometry geom : geoms) {
                //String type = geom.getGeometryType();
                tmp++;
                //写下一条
                SimpleFeature feature = writer.next();

                feature.setAttribute("the_geom", geom);
                feature.setAttribute("name",String.valueOf(tmp));
            }
            writer.write();
            writer.close();
            ds.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
