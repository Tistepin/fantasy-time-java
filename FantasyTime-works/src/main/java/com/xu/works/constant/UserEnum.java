package com.xu.works.constant;

/**
 * @Description:
 * @author: 徐國紀
 * @createTime: 2023-01-2023/1/10-上午 10:25
 */
public enum UserEnum {
    /**
     * 用户来源 官方
     */
    USER_SOURCE_TYPE_OFFICIAL(1, "USER_SOURCE_TYPE_OFFICIAL"),
    /**
     * 用户来源 第三方
     */
    USER_SOURCE_TYPE_THIRD_PARTY(2, "USER_SOURCE_TYPE_THIRD_PARTY");
    private int code;
    private String msg;

    UserEnum(int code, String msg) {
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
