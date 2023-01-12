package com.xu.search.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/9-下午 02:21
 */
@Data
public class WorksInfoVo {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 作品名
     */
    private String worksName;
    /**
     * 作品默认展示图片
     */
    private String defaultImage;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 作品创作者
     */
    private String worksCreator;
    /**
     * 作品创作时间
     */
    private String worksCreateTime;
    /**
     * 作品创作地区
     */
    private String worksArea;
    /**
     * 作品类型 1-漫画 2-小说
     */
    private Integer worksType;
    /**
     * 作品评分 10分满分
     */
    private Float worksScore;
    /**
     * 作品更新至多少
     */
    private String worksRenew;
    /**
     * 作品人气 用户阅读10章以上为1
     */
    private Integer worksPopularity;
    /**
     * 作品描述
     */
    private String worksDescribe;
    /**
     * 作品分类
     */
    private String worksCategory;
    /**
     * 作品状态 1-更新中 2-完结
     */
    private Integer worksStatus;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date editTime;

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD");
        return "WorksInfoVo{" +
                "id=" + id +
                ", worksName='" + worksName + '\'' +
                ", defaultImage='" + defaultImage + '\'' +
                ", creator='" + creator + '\'' +
                ", worksCreator='" + worksCreator + '\'' +
                ", worksCreateTime='" + worksCreateTime + '\'' +
                ", worksArea='" + worksArea + '\'' +
                ", worksType=" + worksType +
                ", worksScore=" + worksScore +
                ", worksRenew='" + worksRenew + '\'' +
                ", worksPopularity=" + worksPopularity +
                ", worksDescribe='" + worksDescribe + '\'' +
                ", worksCategory='" + worksCategory + '\'' +
                ", worksStatus=" + worksStatus +
                ", editTime=" + simpleDateFormat.format(editTime) +
                '}';
    }
}
