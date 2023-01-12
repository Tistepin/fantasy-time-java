package com.xu.common.exception;

/**
 * @author 徐國紀
 * @Name F3863479
 * @create 2022-12-2022/12/9-下午 04:53
 */
public class exception  extends RuntimeException{
    public exception(String ErrorName) {
        super(ErrorName+"存在");
    }
}
