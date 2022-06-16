package com.yzk.sys.vo;

import lombok.Data;

import java.util.Date;
@Data
public class SevenDayMeeting {

    private String meetingname;
    private String roomname;
    private Date starttime;
    private Date endtime;
    private Integer roomid;
    private Integer meetingid;
}
