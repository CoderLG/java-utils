package lg.exception;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;

@Getter
public enum RestError {

	OTHER_ERROR("00001","保留参数位!"),
	UNKNOW_ERROR("00101","未知异常!"),

	TOKEN_ERROR("00200", "用户token异常!"),
	INPUT_ERROR("00201","输入参数异常!"),

	DATABASE_ERROR("00501","请求数据库异常!");

	private String code;
	private String message;

	private RestError(String code, String message) {
		this.code = String.format("222%s",code) ;
		this.message = message;
	}

	/**
	 * set完后
	 * 第一次调用和第二次调用的结果是一个值
	 *
	 * 所以这么用是有问题的 不应该后续还有set
	 * @return
	 */
//	public RestError setReason(String reason){
//		this.reason = reason;
//		return this;
//	}


	@Override
	public String toString() {
		JSONObject object = new JSONObject();
		object.put("code",code);
		object.put("message",message);
		return object.toString();
	}
}
