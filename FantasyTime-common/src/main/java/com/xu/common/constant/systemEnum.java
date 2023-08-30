package com.xu.common.constant;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * @Description:
 * @author: 徐國紀
 * @author: Tistben
 * @createTime: 2023-08-2023/8/27-18:34
 */
public enum systemEnum {
    /**
     * 漫画
     */
    USERNAME(1,null),
    USERIP(2,null);
    private int code;
    private String msg;

    systemEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg=msg;
    }
    static {
        Map<String, String> map = System.getenv();
        String userName = map.get("USERNAME");// 获取// 用户名
        InetAddress localHost = null;
        try {
            localHost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        String hostAddress = localHost.getHostAddress();
        systemEnum.USERIP.setMsg(hostAddress);
        systemEnum.USERNAME.setMsg(userName);

    }
}
