package com.xu.works.controller;

import java.util.*;

import com.xu.common.utils.PageUtils;
import com.xu.common.utils.R;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xu.works.entity.ContactEntity;
import com.xu.works.service.ContactService;

import javax.servlet.http.HttpServletRequest;


/**
 * 用户关系表 谁是谁的好友 谁关注的谁
 *
 * @author xuguoji
 * @email 2532878663
 * @date 2023-08-24 10:50:16
 */
@RestController
@RequestMapping("works/contact")
@Api(tags = "用户关系表 谁是谁的好友 谁关注的谁")
public class ContactController {
    @Autowired
    private ContactService contactService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = contactService.queryPage(params);

        return R.ok().data("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		ContactEntity contact = contactService.getById(id);

        return R.ok().data("contact", contact);
    }

    /**
     * 保存
     */
    @PutMapping("/save")
    public R save(@RequestBody ContactEntity contact){
		contactService.save(contact);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody ContactEntity contact){
		contactService.updateById(contact);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		contactService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }


    // 查询好友 和好友状态
    @ApiOperation(value = "查询好友 和好友状态", notes = "查询好友 和好友状态")
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @PostMapping("/GetContactState")
    public R GetContactState(HttpServletRequest request){
        ArrayList<HashMap<String, Object>> contactEntityList= contactService.GetContactState(request);
        if (contactEntityList.size()!=0){
            return R.ok().data("ContactStates",contactEntityList);
        }
        return R.ok();
    }
    // 查询是否关注
    @ApiOperation(value = "查询是否关注", notes = "查询是否关注")
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @PostMapping("/GetCheckContact")
    public R GetCheckContact(HttpServletRequest request){
        List<ContactEntity> contactEntityList= contactService.GetCheckContact(request);
        if (contactEntityList.size()!=0){
            return R.ok().data("GetCheckContact",contactEntityList.size()>0);
        }
        return R.ok();
    }
}
