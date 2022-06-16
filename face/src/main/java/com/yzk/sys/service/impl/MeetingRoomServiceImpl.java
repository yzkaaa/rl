package com.yzk.sys.service.impl;

import com.yzk.sys.dao.mapper.MeetingRoomMapper;
import com.yzk.sys.dao.pojo.MeetingRoom;
import com.yzk.sys.service.MeetingRoomService;
import com.yzk.sys.vo.MeetingRoomVo;
import com.yzk.sys.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeetingRoomServiceImpl implements MeetingRoomService {
    @Autowired
    private MeetingRoomMapper meetingRoomMapper;


    private List<MeetingRoomVo> copyList(List<MeetingRoom> records) {
        List<MeetingRoomVo> meetingRoomList = new ArrayList<>();
        for (MeetingRoom record : records) {
            meetingRoomList.add(copy(record));
        }
        return meetingRoomList;
    }

    private MeetingRoomVo copy(MeetingRoom meetingRoom){
        MeetingRoomVo meetingRoomVo = new MeetingRoomVo();
        BeanUtils.copyProperties(meetingRoom,meetingRoomVo);
        return meetingRoomVo;
    }

    @Override
    public Result getAllMr() {
        List<MeetingRoom> meetingRooms = meetingRoomMapper.selectList(null);
        return Result.success(copyList(meetingRooms));
    }

    public Result getMrById(Integer roomid) {
        MeetingRoom meetingRoom = meetingRoomMapper.selectById(roomid);
        return Result.success(meetingRoom);
    }

    public Integer updateroom(MeetingRoom meetingRoom) {
        int update = meetingRoomMapper.updateById(meetingRoom);
        return  update;
    }
    public Integer addMr(MeetingRoom meetingRoom) {
        int insert = meetingRoomMapper.insert(meetingRoom);
        return  insert;
    }
}
