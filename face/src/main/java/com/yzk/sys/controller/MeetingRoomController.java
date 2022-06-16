package com.yzk.sys.controller;



import com.yzk.sys.dao.pojo.MeetingRoom;
import com.yzk.sys.service.MeetingRoomService;
import com.yzk.sys.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MeetingRoomController {


    @Autowired
    private MeetingRoomService meetingRoomService;
    /**
     * 会议室
     *
     */
    @RequestMapping("/meetingrooms")
    public Result meetingrooms() {
        return meetingRoomService.getAllMr();
    }

    /**
     * 会议室详情页面
     *
     * @return roomdetails
     */
    @RequestMapping("/roomdetails/{id}")
    public Result roomdetails(@PathVariable("id")Integer id) {
        return meetingRoomService.getMrById(id);
    }

    /**
     * 更新会议房间信息
     *
     * @param meetingRoom MeetingRoom
     * @return 更新成功返回meetingrooms   失败则继续在修改页面
     */
    @RequestMapping("/updateroom")
    public Result updateroom(@RequestBody MeetingRoom meetingRoom) {
        Integer result = meetingRoomService.updateroom(meetingRoom);
        return Result.success(result);
    }
    /**
     * 添加会议室操作
     *
     * @param meetingRoom MeetingRoom
     * @return 成功返回到meetingrooms页面 失败留在当前页面
     */
    @RequestMapping("/admin/doAddMr")
    public Result doAddMr(@RequestBody MeetingRoom meetingRoom) {
        Integer result = meetingRoomService.addMr(meetingRoom);
        return Result.success(result);
    }
}
