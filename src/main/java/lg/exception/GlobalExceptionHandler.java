package lg.exception;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 所有的返回 ResponseEntity
 *      都应该是状态码 加返回值
 *
 * catch 到的异常 打log  给自己看的信息
 *      log.error("给自己看的信息",ex);
 *
 * 全局异常得到的异常 打log  给前端看的信息
 *      log.error(RestError.INPUT_ERROR.getMessage(),ex);
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {



    /**
     * 通用异常处理
     *
     * 出现问题的地方
     * 打log   这样问题异常 描述的更清楚
     *
     * 发布倾斜摄影 实例
     *
     */


    /**
     * 用着顺手的异常
     */
    @ResponseBody
    @ExceptionHandler(RestException.class)
    public String handleRestException(HttpServletResponse response, Exception ex) {

        response.setStatus(500);
        RestException e = (RestException) ex;
        RestError restError = e.getRestError();
        JSONObject object = new JSONObject();
        if ( restError != null){
            return restError.toString();
        }else{
            object.put("code","02500022");
            object.put("message",e.getMess());
        }

        return object.toString();
    }




    /**
     * 参数的验证处理
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResponseEntity handleBindException(final BindException ex) {
        log.error(ex.getMessage(),ex);

        String errorMsg = ex.getFieldErrors().stream().map(this::getFieldErrorMessage).collect(Collectors.joining(" "));
        Map<String, Object> returnError = new HashMap();
        returnError.put("code",  RestError.INPUT_ERROR.getCode());
        returnError.put("message",errorMsg);

        return new ResponseEntity(returnError, HttpStatus.BAD_REQUEST);
    }

    private String getFieldErrorMessage(FieldError err) {
        return err.getField() + " " + err.getDefaultMessage() + "!";
    }


    /**
     * 没有匹配到， 此类匹配
     * @param response
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String handleException(HttpServletResponse response, Exception ex) {
        response.setStatus(500);
        log.error(ex.getMessage(),ex);
        return RestError.UNKNOW_ERROR.toString();

    }


}