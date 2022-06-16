package com.yzk.sys.service;
import com.yzk.sys.dao.pojo.Meeting;
import com.yzk.sys.vo.CancelMeeting;
import com.yzk.sys.vo.Result;
import com.yzk.sys.vo.SevenDayMeeting;
import com.yzk.sys.vo.params.MeetingDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional//出错就回滚防止异常
public interface MeetingService {
     /**
      * 获取当前用户预订的会议
      *
      */
      Result getmeetingofmybookCanCancle(Integer id);

    /**
     * 根据id获取会议信息
     * @param meetingid
     * @return
     */
      Result getmeetingByid(Integer meetingid);
      /**
      * 撤销会议的操作
      *
      * @param meetingid      会议id
      * @param canceledreason 撤销原因
      */
      Result cancelmeeting(Integer meetingid, String canceledreason);
      /**
      * 增加会议
      *
      * @param meeting Meeting
      * @param mps     参加会议的员工的员工ID
      * @return Integer
      */
      Result addMeeting(Meeting meeting, Integer[] mps);

      /**
     * 分页查询获得所有meeting
     *
     * @param meetingDTO MeetingDTO
     * @param page       页数
     * @param pagesize   查几条数据
     * @return
     */
     Result listMeetingDTOs(MeetingDTO meetingDTO, Integer page, Integer pagesize);
    /**
     * 获取员工数量
     *
     * @param meetingDTO MeetingDTO
     * @return Long
     */
     Long getTotal(MeetingDTO meetingDTO);

    /**
     * 一小时后的会议
     * @param employeeid
     * @return
     */
    List<SevenDayMeeting> getHourMeeting(Integer employeeid);

    /**
     * 取消的会议
     * @return
     */
    List<CancelMeeting> getCancelMeeting();
    /**
     * 加入会议
     */
    Boolean JoinMeeting(Integer meetingid,Integer random);
    /**
     * 正在进行的会议
     */
    List<SevenDayMeeting> ongoingMeeting(Integer employeeid);
    /**
     *
     */
    Result meetingDismiss();
}
