package org.crawler.exception.bizException;


/**
 * @Description : 业务异常的错误代码接口ss
 */

public interface BizExceptionCode {

    /**
     * @Description : 获取错误代码s
     */
    String getCode();
    
    /**
     * @Description : 获取错误信息
     */
    String getMessage();

}
