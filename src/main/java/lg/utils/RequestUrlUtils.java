package lg.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求的url
 * 获取url中的各个参数
 */
@Slf4j
public class RequestUrlUtils {

	/**
	 * 从url路径中提取路径，例如api/{id}/folder/a.osg, return folder/a.osg
	 * @param request
	 * @return
	 */
	public static String extractPathFromPattern(final HttpServletRequest request) {
    	log.warn(request.getRequestURI());// /api/v1/services
    	log.warn(request.getRequestURL().toString());//http://192.168.44.69:8230/api/v1/services
    	log.warn(request.getServletPath());// /api/v1/services
    	log.warn(request.getLocalAddr()); //192.168.44.69
    	log.warn("{}", request.getLocalPort()); //8230

		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);
	}

}
