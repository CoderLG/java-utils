package lg.exception;

import lombok.Getter;

/**
 * author: LG
 * date: 2020-04-28 11:21
 * desc:
 */
@Getter
public class RestException extends RuntimeException{
    private static final long serialVersionUID = -5367052880716339652L;

    /**
     * 每次异常都要 写一段话描述这个异常
     *
     * 将这些描述放到一个枚举中，就不用 每次都写一遍了
     */
    private RestError restError ;

    /**
     * 总会有异常没有 封装到restRrror的
     * 写在这里返回
     * 写多了之后可以封装到restRrror中
     */
    private String mess;

    public RestException(RestError restError){
        this.restError = restError;
    }

    public RestException(String mess){
        this.mess = mess;
    }


}
