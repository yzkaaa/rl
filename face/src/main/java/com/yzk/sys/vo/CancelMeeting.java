package com.yzk.sys.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class CancelMeeting {
    private String meetingname;
    private String roomname;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date starttime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endtime;
    private String canceledreason;
    private Integer roomid;
    private Integer meetingid;
}