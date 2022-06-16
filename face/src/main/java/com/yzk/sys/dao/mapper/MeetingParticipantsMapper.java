package com.yzk.sys.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MeetingParticipantsMapper {

    /**
     * 通过meetingid获取参加会议的人的employeeid
     *
     * @param meetingid Integer
     * @return List<Integer>
     */
    List<Integer> getAllBymeetingid(Integer meetingid);
}
