package com.yzk.sys.controller;


import com.yzk.sys.dao.pojo.SysUser;
import com.yzk.sys.service.SysUserService;
import com.yzk.sys.vo.Result;
import com.yzk.sys.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
       //一页放10条数据
    public static final Integer PAGE_SIZE =5;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    @GetMapping("/currentUser")
    public Result currentUser(@RequestHeader("Authorization") String token){
        return sysUserService.findUserByToken(token);
    }
    /**
     * 分页查询员工
     * 获取员工的数量
     *
     * @param page  页数
     * @return 搜索员工页面
     */
    @RequestMapping(value = {"/searchsysuser","/searchsysuser/{nickname}","/searchsysuser/{account}","/searchsysuser/{nickname}/{account}"})
    public Result getAllSysUser(SysUser sysUser, @RequestParam(defaultValue = "1") Integer page) {
        return sysUserService.getAllEmps(sysUser, page, PAGE_SIZE);
    }
    @RequestMapping(value = {"/pageparams","/pageparams/{nickname}","/pageparams/{account}","/pageparams/{nickname}/{account}"})
    public Result getAllPageParams(SysUser sysUser, @RequestParam(defaultValue = "1") Integer page) {
        PageParams pageParams = new PageParams();
        Long total = sysUserService.getTotal(sysUser);
        pageParams.setTotal(total);
        pageParams.setPage(page);
        long l = total % PAGE_SIZE == 0 ? total / PAGE_SIZE : total / PAGE_SIZE + 1;
        pageParams.setPagenum(l);
        return  Result.success(pageParams);

    }
}

