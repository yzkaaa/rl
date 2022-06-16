package com.yzk.sys.vo;


import lombok.Data;

@Data
public class MeetingRoomVo {
    //会议室ID
    private Integer id;
    //门牌号
    private Integer roomnum;
    //会议室名称
    private String roomname;
    //最多容纳人数
    private Integer capacity;
    //当前状态(0启用  1已占用)
    private Integer status;
    //备注
    private String description;
}

