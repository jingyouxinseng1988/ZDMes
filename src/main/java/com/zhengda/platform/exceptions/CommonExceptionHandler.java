package com.zhengda.platform.exceptions;

import com.fasterxml.jackson.core.JsonParseException;
import com.zhengda.platform.domain.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;


@ControllerAdvice
public class CommonExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);

    /**
     * 用来处理bean validation异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public AjaxResult resolveConstraintViolationException(ConstraintViolationException ex) {
        ex.printStackTrace();
        String msg;
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ConstraintViolation constraintViolation : constraintViolations) {
                msgBuilder.append(constraintViolation.getMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if (errorMessage.length() > 1) {
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
            }
            msg = errorMessage;
        } else {
            msg = ex.getMessage();
        }
        logger.info("bean参数检验不通过:{}", msg);
        return AjaxResult.warn(msg);
    }

    /**
     * 用来处理method validation异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public AjaxResult resolveMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ex.printStackTrace();
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        String errorMessage = getErrorMessage(allErrors);
        if (StringUtils.isEmpty(errorMessage)) {
            errorMessage = ex.getMessage();
        }
        logger.info("BindException参数检验不通过:{}", errorMessage);
        return AjaxResult.warn(errorMessage);
    }


    /**
     * 用来处理BindException异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
   public AjaxResult bindException(BindException ex) {
        ex.printStackTrace();
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        String msg = getErrorMessage(allErrors);
        if (StringUtils.isEmpty(msg)) {
            msg = ex.getMessage();
        }
        logger.info("BindException参数检验不通过:{}", msg);
        return AjaxResult.warn(msg);
    }


    /**
     * SQL异常
     */
    @ExceptionHandler(SQLException.class)
    @ResponseBody
   public AjaxResult handlerSQLException(SQLException ex) {
        ex.printStackTrace();
        logger.error("SQL异常:result={}", ex.getMessage());
        return AjaxResult.warn(ex.getMessage());
    }

    /**
     * 不支持method的异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
   public AjaxResult handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        ex.printStackTrace();
        logger.error("HttpRequestMethodNotSupportedException:result=", ex);
        return AjaxResult.warn(ex.getMessage());
    }

    /**
     * RuntimeException的业务异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
   public AjaxResult handlerRuntimeException(RuntimeException ex) {
        ex.printStackTrace();
        logger.error("RuntimeException:result=", ex);
        return AjaxResult.warn(ex.getMessage());
    }

    /**
     * 未定义的异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
   public AjaxResult handlerException(Exception ex) {
        ex.printStackTrace();
        logger.error("Exception异常:result=", ex);
        return AjaxResult.warn(ex.getMessage());
    }

    /**
     * 未定义的异常
     */
    @ExceptionHandler(JsonParseException.class)
    @ResponseBody
   public AjaxResult handlerException(JsonParseException ex) {
        ex.printStackTrace();
        logger.error("Exception异常:result=", ex);
        return AjaxResult.warn("JSON 解析异常");
    }


    private String getErrorMessage(List<ObjectError> objectErrors) {
        String msg = null;
        if (!CollectionUtils.isEmpty(objectErrors)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ObjectError objectError : objectErrors) {
                msgBuilder.append(objectError.getDefaultMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if (errorMessage.length() > 1) {
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
            }
            msg = errorMessage;
        }
        return msg;
    }

}
