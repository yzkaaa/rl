package com.yzk.sys.controller;

import com.yzk.sys.dao.pojo.SysUser;
import com.yzk.sys.service.MeetingService;
import com.yzk.sys.service.SysUserService;
import com.yzk.sys.utils.UserThreadLocal;
import com.yzk.sys.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ChangeBookingController {

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 我的预订页面
     *
     */
    @RequestMapping("/mybookings")
    public Result mybookings() {
        SysUser sysUser = UserThreadLocal.get();
        Integer id = sysUser.getId();
        return meetingService.getmeetingofmybookCanCancle(id);
    }
      /**
      * 预订会议里的详情页面
      *  查询会议详情
      * @return
      */
    @RequestMapping("/mymeetingdetails/{meetingid}")
    public Result meetingdetails(@PathVariable("meetingid") Integer meetingid)  {
        return meetingService.getmeetingByid(meetingid);
    }
     /**
      * 预订会议里的详情页面
      *  查询参会人员信息
      */
    @RequestMapping("/mymeetingdetail/{meetingid}")
    public Result meetingdetail(@PathVariable("meetingid") Integer meetingid) {
        return sysUserService.getEmpsByid(meetingid);
    }
    /**
     * 撤销会议的操作
     *
     * @param meetingid      会议id
     * @param canceledreason 撤销原因
     * @return 请求转发到mybookings页面
     */
    @PostMapping("/dpCancel/{meetingid}/{canceledreason}")
    public Result doCancel(@PathVariable("meetingid") Integer meetingid,
                           @PathVariable("canceledreason")String canceledreason) {
        return meetingService.cancelmeeting(meetingid, canceledreason);
    }
}
