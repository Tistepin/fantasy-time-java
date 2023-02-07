package com.xu.works.constant;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/7-下午 04:32
 */
public enum AreaEnum {
    /**
     * 漫画
     */
    Area_Cache_key(1, "Area_Cache_key");
    private int code;
    private String msg;

    AreaEnum(int code, String msg) {
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
