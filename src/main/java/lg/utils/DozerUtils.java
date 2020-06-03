package lg.utils;

import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * author: LG
 * date: 2019-10-29 11:14
 * desc:
 * 实体间 相同属性的赋值
 *
 * 使用：
 * DozerUtils.mapList(students, Teacher.class);
 */
public class DozerUtils {
      public static DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

      public static <T> List<T> mapList(Collection sourceList,Class<T> destinationClass){
         List destinationList = Lists.newArrayList();

         for(Iterator i = sourceList.iterator(); i.hasNext();){
             Object sourceObj = i.next();
             Object destinationObj = dozerBeanMapper.map(sourceObj, destinationClass);
             destinationList.add(destinationObj);
         }

         return destinationList;
      }
}
