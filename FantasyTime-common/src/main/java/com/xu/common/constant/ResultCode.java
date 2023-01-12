package com.xu.common.constant;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/2-下午 04:37
 */
public enum  ResultCode {
    UNAUTHORIZED(401, "无凭证"), FORBIDDEN(400, "权限不足"),
    AUTHFAILED(402, "认证失败"),SUCCESS(20000,"成功"),
    ERROR(20001,"失败");
    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
