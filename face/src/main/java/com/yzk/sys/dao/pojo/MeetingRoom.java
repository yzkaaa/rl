package com.yzk.sys.dao.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class MeetingRoom {
    //会议室ID
    @TableId(type = IdType.AUTO)
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
