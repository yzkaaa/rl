package com.yzk.sys.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.yzk.sys.config.tencent.TrtcUtil;
import com.yzk.sys.dao.pojo.Meeting;
import com.yzk.sys.dao.pojo.SysUser;
import com.yzk.sys.service.MeetingRoomService;
import com.yzk.sys.service.MeetingService;
import com.yzk.sys.service.SysUserService;
import com.yzk.sys.utils.UserThreadLocal;
import com.yzk.sys.vo.Result;
import com.yzk.sys.vo.params.MeetingDTO;
import com.yzk.sys.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class MeetingController {
    @Value("${tencent.trtc.appId}")
    private int appId;

    @Autowired
    private TrtcUtil trtcUtil;


    @Autowired
    private MeetingRoomService meetingRoomService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private MeetingService meetingService;

    /**
     * 查询所有员工
     */
    @RequestMapping("/getempbydepid")
    @ResponseBody
    public Result getEmpsByDepId() {
        return sysUserService.getEmpsByDepId();
    }

    /**
     * 增加会议操作
     *
     * @param meeting Meeting
     * @param mps     参会人数
     */
    @RequestMapping("/doAddMeeting")
    public Result doAddMeeting(Meeting meeting, Integer[] mps) {
        SysUser sysUser = UserThreadLocal.get();
        meeting.setReservationistid(sysUser.getId());
        //给meeting的status设置状态为0 （0代表有的会议，1代表取消的会议）
        meeting.setStatus(0);
        return meetingService.addMeeting(meeting, mps);
    }

    //一页放10条数据
    public static final Integer PAGE_SIZE = 10;

    /**
     * 返回搜索会议页面
     *
     * @return searchmeetings
     */
    @RequestMapping("/searchmeetings")
    public Result getAllMeetings(MeetingDTO meetingDTO, @RequestParam(defaultValue = "1") Integer page) {
        PageParams pageParams = new PageParams();
        Long total = meetingService.getTotal(meetingDTO);
        pageParams.setTotal(total);
        pageParams.setPage(page);
        long l = total % PAGE_SIZE == 0 ? total / PAGE_SIZE : total / PAGE_SIZE + 1;
        pageParams.setPagenum(l);
        return Result.success(pageParams);
    }
    @RequestMapping("/searchmeeting")
    public Result getAllMeeting(MeetingDTO meetingDTO, @RequestParam(defaultValue = "1") Integer page) {
        return meetingService.listMeetingDTOs(meetingDTO, page, PAGE_SIZE);
    }

    /**
     *  加入会议
     * @param meetingid
     * @param random
     * @return
     */
    @PostMapping("/searchMeetId/{meetingid}/{random}")
    public Result searchMeetId(@PathVariable("meetingid") Integer meetingid,
                               @PathVariable("random")    Integer random){
        Boolean meeting = meetingService.JoinMeeting(meetingid, random);
        return Result.success(meeting);
    }
   /* *//**
     * 结束会议
     *//*
     @PostMapping("/meetingDismiss/{meetingid}")
    public Result meetingDismiss(@PathVariable("meetingid") Integer meetingid){

        return Result.success();
    }*/

}
