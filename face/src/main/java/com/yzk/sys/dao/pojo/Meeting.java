package com.yzk.sys.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Meeting {
    //会议ID
     @TableId(type = IdType.AUTO)
    private Integer meetingid;
    //会议名称
    private String meetingname;
    //房间号
    private Integer roomid;
    //谁预订的
    private Integer reservationistid;
    //参加人数
    private Integer numberofparticipants;
    //开始时间
    private Date starttime;
    //结束时间
    private Date endtime;
    //预约时间
    private Date reservationtime;
    //取消时间
    private Date canceledtime;
    //会议说明
    private String description;
    //状态（0启用  1已占用）
    private Integer status;
    //取消原因
    private String canceledreason;
    //会议号
    private Integer random;
}
