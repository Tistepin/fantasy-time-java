package com.xu.works.service.impl;

import com.xu.works.vo.DownListVo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.Query;

import com.xu.works.dao.CategoryDao;
import com.xu.works.entity.CategoryEntity;
import com.xu.works.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }
    /**
     * @Description
     * @Author F3863479
     * @Date 2023/1/6 下午 01:58
     * @Params [worksType]
     * @return java.util.List<com.xu.search.entity.CategoryEntity>
     *
     */
    @Cacheable(value = "Category",key = "#root.method.name+#root.args[0]")
    @Override
    public List<CategoryEntity> getCategory(Integer worksType) {
        return this.baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq(
                "works_type", worksType
        ).eq("delete_status", 1));
    }

    @Override
    public String getCategorysName(String[] split, Integer worksType) {
        return this.baseMapper.getCategorysName(split,worksType);
    }

    @Override
    public List<DownListVo> GetCategoryDownList(Integer worksTyp) {
        return this.baseMapper.GetCategoryDownList(worksTyp);
    }

}