package com.yzk.sys.vo.params;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class MeetingDTO {
    //会议ID
    private Integer meetingid;
    //会议名称
    private String meetingname;
    //开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date starttime;
    //结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endtime;
    //预约时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reservationtime;
    //会议说明
    private String description;
    //房间号
    private Integer roomid;
    //预订房间的人的ID
    private Integer reservationistid;
    //预订房间的人的姓名
    private String reservationistname;
    //房间名
    private String roomname;

}
