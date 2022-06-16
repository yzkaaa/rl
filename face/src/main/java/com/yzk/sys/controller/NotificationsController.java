package com.yzk.sys.controller;


import com.yzk.sys.dao.pojo.SysUser;
import com.yzk.sys.service.MeetingService;
import com.yzk.sys.utils.UserThreadLocal;
import com.yzk.sys.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 通知页面
 */
@Controller
public class NotificationsController {

    @Autowired
    private MeetingService meetingService;

    /**
     * 返回到通知页面并把参加的会议列出来
     *     一小时后的会议
     * @return 返回到通知页面
     */
    @GetMapping("/getHourMeeting")
    public Result getHourMeeting() {
        SysUser sysUser = UserThreadLocal.get();
        Integer id =sysUser.getId();
        return  Result.success(meetingService.getHourMeeting(id));
    }

    /**
     *  取消的会议
     * @return
     */
     @GetMapping("/getCancelMeeting")
    public Result getCancelMeeting() {
        return   Result.success(meetingService.getCancelMeeting());
    }

    /**
     * 正在进行的会议
     * @return
     */
    @GetMapping("/ongoingMeeting")
    public Result ongoingMeeting() {
        SysUser sysUser = UserThreadLocal.get();
        Integer id =sysUser.getId();
        return  Result.success(meetingService.ongoingMeeting(id));
    }
}
