package com.xu.works.constant;

/**
 * @Description:  Works 的常用枚举类
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/7-上午 08:18
 */
public  class WorksEnum{
    public enum  Works_mh_Enum {

        /**
         * 漫画
         */
        works_mh(1, "works_mh"),
        /**
         * 今日漫画观看的用户 每次添加人气等于这个漫画今天已经被这个用户观看了
         */
        works_popularity_today_user(1, "works_popularity_today_user"),
         /**
          * 今日漫画观看的人气
          */
         works_popularity_today_mh(1, "works_popularity_today_mh"),
         /**
          * 三日内人气 一天一更新 第三天删除
          */
         works_popularity_three_days_mh(2, "works_popularity_three_days_mh"),
         /**
          * 本周内人气 一天一更新  每周1 0点删除
          */
         works_popularity_thisWeek_mh(3, "works_popularity_thisWeek_mh"),
         /**
          * 本月内人气 一天一更新 每月最第一天 0点删除
          */
         works_popularity_thisMonth_mh(4, "works_popularity_thisMonth_mh");

         private int code;
         private String msg;

         Works_mh_Enum(int code, String msg) {
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
    public enum  Works_xs_Enum {
        /**
         * 小说
         */
        works_xs(2, "works_xs"),
        /**
         * 今日小说观看的用户 每次添加人气等于这个漫画今天已经被这个用户观看了
         */
        works_popularity_today_user(1, "works_popularity_today_user"),
        /**
         * 今日漫画观看的人气
         */
        works_popularity_today_xs(1, "works_popularity_today_xs"),
        /**
         * 三日内人气 一天一更新
         */
        works_popularity_three_days_xs(2, "works_popularity_three_days_xs"),
        /**
         * 本周内人气 一天一更新
         */
        works_popularity_thisWeek_xs(3, "works_popularity_thisWeek_xs"),
        /**
         * 本月内人气 一天一更新
         */
        works_popularity_thisMonth_xs(4, "works_popularity_thisMonth_xs");

        private int code;
        private String msg;

        Works_xs_Enum(int code, String msg) {
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

}