package com.xu.works.utils;

import java.util.Comparator;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-06-2023/6/20-下午 04:56
 */
public class LengthComparator implements Comparator<String> {
    public int compare(String s1, String s2) {
        return Integer.compare(pageint(s1), pageint(s2));
//        return Integer.compare(s1.length(),s2.length());
    }
    public Integer pageint(String s){
        String[] split = s.split("/");
        String[] split1 = split[split.length - 1].split("\\.");
        return  Integer.parseInt(split1[0]);
    }
}
