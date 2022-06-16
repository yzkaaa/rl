package com.yzk.sys.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzk.sys.dao.pojo.Meeting;
import com.yzk.sys.vo.params.MeetingDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MeetingMapper  extends BaseMapper<Meeting> {
    /**
     * 添加参与者
     *
     * @param meetingid 会议Id
     * @param mps       参加会议的员工的员工ID
     */
    void addParticipants(@Param("meetingid") Integer meetingid, @Param("mps") Integer[] mps);
      /**
     * 获取当前用户预订的会议
     *
     * @param employeeid Integer
     * @return List<MeetingDTO>
     */
    List<MeetingDTO> getmeetingofmybook(Integer employeeid);
    /**
     * 撤销会议的操作
     *
     * @param meetingid      会议id
     * @param canceledreason 撤销原因
     */
    Integer cancelmeeting(@Param("meetingid") Integer meetingid, @Param("canceledreason") String canceledreason);
      /**
     * 分页查询获得所有meeting
     *
     * @param meetingDTO MeetingDTO
     * @param page       页数
     * @param pagesize   查几条数据
     * @return
     */
    List<MeetingDTO> listMeetingDTOs(@Param("mdto") MeetingDTO meetingDTO, @Param("page") Integer page,
                                     @Param("pagesize") Integer pagesize);

    /**
     * 获取员工数量
     *
     * @param meetingDTO MeetingDTO
     * @return Long
     */
    Long getTotal(@Param("mdto") MeetingDTO meetingDTO);
     /**
     * 根据employeeid获取会议信息
     *
     * @param employeeid Integer
     * @return List<Meeting>
     */
    List<Meeting> getMeetingById(Integer employeeid);
}

