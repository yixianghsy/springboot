package hsy.com.aop.uitl;

import lombok.Data;

/**
 * 统一的 REST响应对象
 * @author wj
 * @create 2022-07-01 14:28
 */
@Data
public class RetResult {

    /**
     * 成功
     */
    public static int SUCCESS_CODE = 200;
    /**
     * 失败
     */
    public static int FAIL_CODE = 500;
    /**
     * 业务警告
     */
    public static int WARN_CODE = 400;

    public static int ret_Code = 0;

    public static String ret_Info = "";

    public static String result ;

    public static Boolean success ;

    int code;
    String message;
    Object data;

    private RetResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 操作成功-无数据返回
     * @return
     */
    public static RetResult success() {
        return new RetResult(SUCCESS_CODE,"操作成功！",true);
    }

    /**
     * 操作成功-带数据
     * @param data
     * @return
     */
    public static RetResult success(Object data) {
        return new RetResult(SUCCESS_CODE,"操作成功！",data);
    }

    /**
     * 500异常
     * @param message
     * @return
     */
    public static RetResult fail(String message) {
        return new RetResult(FAIL_CODE,message,"");
    }

    /**
     * 业务异常
     * @param message
     * @return
     */
    public static RetResult warn(String message) {
        return new RetResult(WARN_CODE,message,"");
    }
}