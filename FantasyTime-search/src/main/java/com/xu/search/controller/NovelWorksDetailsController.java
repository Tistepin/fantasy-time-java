package com.xu.search.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xu.search.entity.NovelWorksDetailsEntity;
import com.xu.search.service.NovelWorksDetailsService;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.R;



/**
 * 小说作品章节数量
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-05 15:51:40
 */
@RestController
@RequestMapping("search/novelworksdetails")
public class NovelWorksDetailsController {
    @Autowired
    private NovelWorksDetailsService novelWorksDetailsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = novelWorksDetailsService.queryPage(params);

        return R.ok().data("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		NovelWorksDetailsEntity novelWorksDetails = novelWorksDetailsService.getById(id);

        return R.ok().data("novelWorksDetails", novelWorksDetails);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody NovelWorksDetailsEntity novelWorksDetails){
		novelWorksDetailsService.save(novelWorksDetails);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody NovelWorksDetailsEntity novelWorksDetails){
		novelWorksDetailsService.updateById(novelWorksDetails);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		novelWorksDetailsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
