package hsy.com.aop.uitl;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CodeMsg {

    private Integer code;
    private String msg;

    /**
     * 成功标识
     */
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    /**
     * 失败标识
     */
    public static CodeMsg ERROR = new CodeMsg(500, "服务端异常！");

    //参数校验异常处理
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");

    /*=======================登陆失败状态开始======================*/
    public static CodeMsg SESSION_ERROR = new CodeMsg(500210, "Session不存在或者已经失效");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "登录密码不能为空");
    public static CodeMsg MOBILE_EMPTY = new CodeMsg(500212, "手机号不能为空");
    public static CodeMsg MOBILE_ERROR = new CodeMsg(500213, "手机号格式错误");
    public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500214, "手机号不存在");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500215, "密码错误");
    /*=======================登陆失败状态结束======================*/

    /**
     * 参数校验异常处理
     * @param args
     * @return
     */
    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

    private CodeMsg(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

}