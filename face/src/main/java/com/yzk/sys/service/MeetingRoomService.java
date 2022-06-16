package com.yzk.sys.service;

import com.yzk.sys.dao.pojo.MeetingRoom;
import com.yzk.sys.vo.Result;
import org.springframework.transaction.annotation.Transactional;

@Transactional//出错就回滚防止异常
public interface MeetingRoomService {

    /**
     * 得到所有的会议室
     *
     * @return
     */
    Result getAllMr();
    /**
     * 根据roomid获取对应的会议室
     *
     * @param roomid roomid
     * @return
     */
    Result getMrById(Integer roomid);

    /**
     * 更新会议房间信息
     *
     * @param meetingRoom MeetingRoom
     * @return Integer
     */
    Integer updateroom(MeetingRoom meetingRoom);
    /**
     * 添加会议室操作
     *
     * @param meetingRoom MeetingRoom
     * @return
     */
    Integer addMr(MeetingRoom meetingRoom);

}
