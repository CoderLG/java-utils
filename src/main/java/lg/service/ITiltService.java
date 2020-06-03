package lg.service;

import org.springframework.data.domain.Page;

import java.util.Set;

/**
 * @author
 * @date
 *
 * 好 service 的样例
 */
public interface ITiltService<T, P> {

	/**
	 * 检查文件夹是否已经发布服务
	 *
	 * @param dir 文件夹路径
	 * @return
	 */
	boolean checkDirPublishStatus(String dir);

	/**
	 * 发布文件夹，发布过程中，会对文件夹进行copy，如果文件夹在数据录下下，不拷贝
	 *
	 * @param tilt
	 * @return
	 */
	T publishDir(T tilt);

	/**
	 * 压缩文件发布，发布过程中，会对文件夹进行解压
	 *
	 * @param filePath
	 * @param tilt
	 * @return
	 */
	T publishFile(String filePath, T tilt);

	/**
	 * 根据id集合，删除发布数据，会将发布文件夹删除
	 *
	 * @param ids
	 * @return
	 */
	boolean deleteServices(Set<Long> ids);

	/**
	 * 根据id集合和userId，删除发布数据，会将发布文件夹删除
	 *
	 * @param ids
	 * @param userId
	 * @return
	 */
	boolean deleteServicesByUserId(Set<Long> ids, String userId);

	/**
	 * 更新倾斜摄影发布的服务，提供名称、描述、缩略图、经纬高
	 *
	 * @param modifingTilt
	 * @return
	 */
	T updateTiltEntity(T modifingTilt);


	/**
	 * 根据id获取倾斜摄影数据发布信息
	 *
	 * @param id
	 * @return
	 */
	T getById(Long id);

	/**
	 * 通过当前页码和每页数量，名称，基于输入的排序信息获取已经发布的倾斜摄影服务列表
	 *
	 * @param currentPage
	 * @param pageSize
	 * @param layerName
	 * @param orderColumn
	 * @param orderType
	 * @return
	 */
	Page<T> queryTiltServiceByPageAndLayerName(int currentPage, int pageSize, String layerName, String orderColumn, String orderType);


	/**
	 * 通过当前页码和每页数量，名称，userId,基于输入的排序信息获取已经发布的倾斜摄影服务列表
	 *
	 * @param currentPage
	 * @param pageSize
	 * @param layerName
	 * @param orderColumn
	 * @param orderType
	 * @param userId
	 * @return
	 */
	Page<T> queryTiltServiceByPageAndLayerNameAndUserId(int currentPage, int pageSize, String layerName, String orderColumn, String orderType, String userId);


	/**
	 * 根据id查到文件夹，加上relativePath地址获取到文件实际路径
	 *
	 * @param id           倾斜摄影图层id
	 * @param relativePath 用户访问的文件相对路径
	 * @return 文件存在返回实际文件路径，失败返回空
	 */
	String queryTiltFilePathById(Long id, String relativePath);

	/**
	 * 通过图层名称查询数据信息
	 *
	 * @param layerName
	 * @return
	 * @author wangda
	 * @ date 20181031
	 */
	T findByLayerName(String layerName);


	/**
	 * 通过图层名称和userid 查询数据信息
	 * @param layerName
	 * @param id
	 * @return
	 */
	T findByLayerNameContainsIgnoreCaseAndUserId(String layerName, String id);

	/**
	 * 判断绝对路径dir下是否存在符合规定文件结尾的
	 *
	 * @param dir
	 * @param format
	 * @return
	 * @author wangda
	 * @date 20181031
	 */
	Boolean fileIsExist(String dir, String format);

	/**
	 * 根据绝对路径（包含文件名）及格式获取经度纬度信息
	 *
	 * @param dir
	 * @param format
	 * @author wangda
	 * @date 20181108
	 */
	P obtainPosition(String dir, String format);

	/**
	 * 根据绝对路径（包含文件名）及格式获取经度纬度信息
	 *
	 * @param dir
	 * @param format
	 * @param selFormat
	 * @param selEle
	 * @param splitstr
	 * @return
	 * @author wangda
	 * @date 20181114
	 */
	P obtainPosition(String dir, String format, String selFormat, String selEle, String splitstr);

	P obtainPositionSec(String dir, String format, String selFormat, String selEle, String splitStr);

}
