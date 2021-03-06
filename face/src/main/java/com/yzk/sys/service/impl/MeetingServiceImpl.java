package com.yzk.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yzk.sys.dao.mapper.MeetingMapper;
import com.yzk.sys.dao.mapper.MeetingRoomMapper;
import com.yzk.sys.dao.pojo.Meeting;
import com.yzk.sys.dao.pojo.MeetingRoom;
import com.yzk.sys.service.MeetingService;
import com.yzk.sys.vo.CancelMeeting;
import com.yzk.sys.vo.Result;
import com.yzk.sys.vo.RoomDTO;
import com.yzk.sys.vo.SevenDayMeeting;
import com.yzk.sys.vo.params.MeetingDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MeetingServiceImpl implements MeetingService {
     @Autowired
    private RedisTemplate redisTemplate;
     @Autowired
    private MeetingMapper meetingMapper;
     @Autowired
     private MeetingRoomMapper meetingRoomMapper;

    private List<MeetingDTO> copyList(List<Meeting> records) {
       List<MeetingDTO> meetingRoomList = new ArrayList<>();
        for (Meeting record : records) {
            meetingRoomList.add(copy(record));
        }
        return meetingRoomList;
    }
    private MeetingDTO copy(Meeting meeting){
        MeetingDTO meetingDTO = new MeetingDTO();
        BeanUtils.copyProperties(meeting,meetingDTO);
        return meetingDTO;
    }
    public Result getmeetingofmybookCanCancle(Integer id) {
        List<MeetingDTO> getmeetingofmybook = meetingMapper.getmeetingofmybook(id);
        return Result.success(getmeetingofmybook);
    }

    @Override
    public Result getmeetingByid(Integer meetingid) {
        LambdaQueryWrapper<Meeting> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Meeting::getMeetingid,meetingid);
        List<Meeting> meetings = meetingMapper.selectList(queryWrapper);
        return Result.success(copyList(meetings));
    }

    @Override
    public Result cancelmeeting(Integer meetingid, String canceledreason) {
        Integer cancelmeeting = meetingMapper.cancelmeeting(meetingid, canceledreason);
        return Result.success(cancelmeeting);
    }

    /**
     * ????????????
     *
     * @param meeting Meeting
     * @param mps     ??????????????????????????????ID
     * @return Integer
     */
    public Result addMeeting(Meeting meeting, Integer[] mps) {
        //?????????????????????
        meeting.setReservationtime(new Date());
        Random ne=new Random();
        Integer i = ne.nextInt(9999 - 1000 + 1) + 1000;
        meeting.setRandom(i);
        Integer insert = meetingMapper.insert(meeting);
        meetingMapper.addParticipants(meeting.getMeetingid(), mps);
        return Result.success(insert);
    }
    /**
     * ????????????????????????meeting
     *
     * @param meetingDTO MeetingDTO
     * @param page       ??????
     * @param pagesize   ???????????????
     * @return
     */
    public Result listMeetingDTOs(MeetingDTO meetingDTO, Integer page, Integer pagesize) {
        page = (page - 1) * pagesize;
        return  Result.success(meetingMapper.listMeetingDTOs(meetingDTO, page, pagesize));
    }
    /**
     * ??????????????????
     *
     * @param meetingDTO MeetingDTO
     * @return Long
     */
    public Long getTotal(MeetingDTO meetingDTO) {
        return meetingMapper.getTotal(meetingDTO);
    }
    /**
     * ?????????????????????
     *
     * @param employeeid Integer
     * @return List<SevenDayMeeting>
     */
    public List<SevenDayMeeting> getHourMeeting(Integer employeeid) {
        List<Meeting> list = meetingMapper.getMeetingById(employeeid);
        LambdaQueryWrapper<MeetingRoom> Wrapper = new LambdaQueryWrapper();
        Wrapper.select(MeetingRoom::getId,MeetingRoom::getRoomname);
        List<MeetingRoom> roomDTOS= meetingRoomMapper.selectList(Wrapper);
        List<RoomDTO> listroomDTO= copyList1(roomDTOS);

        Calendar c = Calendar.getInstance();
        Date now = new Date();
        c.setTime(now);
        c.add(Calendar.HOUR_OF_DAY, 1);
        Date after7day = c.getTime();

        //?????? ????????????7????????????????????????
        List<Meeting> listNext7Day =
                list.stream().filter(s ->
                        s.getStarttime().getTime() >= now.getTime() &&
                                s.getStarttime().getTime() <= after7day.getTime())
                        .collect(Collectors.toList());

        //??????Map<roomid,roomname>??????????????????,???????????????roomname
        Map<Integer, String> resultMap = listroomDTO.stream().collect(
                HashMap::new,(map, roomDTO) -> map.put(roomDTO.getRoomId(),roomDTO.getRoomName()),
                HashMap::putAll);

        //??????Meeting???SevenDayMeeting,????????????View??????,??????Meeting???????????????
        List<SevenDayMeeting> listDto = listNext7Day.stream().map(meeting -> {

            SevenDayMeeting sevenDayMeeting = new SevenDayMeeting();

            BeanUtils.copyProperties(meeting, sevenDayMeeting);

            sevenDayMeeting.setRoomname(resultMap.get(meeting.getRoomid()));

            return sevenDayMeeting;
        }).collect(Collectors.toList());

        return listDto;
    }
    /**
     * ??????????????????
     *
     * @return List<CancelMeeting>
     */
    public List<CancelMeeting> getCancelMeeting() {
        LambdaQueryWrapper<Meeting> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Meeting::getStatus,1);
        List<Meeting> list  = meetingMapper.selectList(queryWrapper);

        LambdaQueryWrapper<MeetingRoom> Wrapper = new LambdaQueryWrapper();
        Wrapper.select(MeetingRoom::getId,MeetingRoom::getRoomname);
        List<MeetingRoom>roomDTOS= meetingRoomMapper.selectList(Wrapper);
        List<RoomDTO> listroomDTO= copyList1(roomDTOS);

          //??????Map<roomid,roomname>??????????????????,???????????????roomname
        Map<Integer, String> resultMap = listroomDTO.stream().collect(
                HashMap::new,(map, roomDTO) -> map.put(roomDTO.getRoomId(),roomDTO.getRoomName()),
                HashMap::putAll);

        //??????Meeting???CancelMeeting,????????????View??????,??????Meeting???????????????
        List<CancelMeeting> listDto = list.stream().map(meeting -> {

            CancelMeeting cancelMeeting = new CancelMeeting();

            BeanUtils.copyProperties(meeting, cancelMeeting);

            cancelMeeting.setRoomname(resultMap.get(meeting.getRoomid()));

            return cancelMeeting;
        }).collect(Collectors.toList());
        return listDto;
    }

    @Override
    public Boolean JoinMeeting(Integer meetingid,Integer random) {
        Meeting meeting = meetingMapper.selectById(meetingid);
        if (random!= meeting.getRandom()){
            return false;
        }
        return true;
    }

    @Override
    public List<SevenDayMeeting> ongoingMeeting(Integer employeeid) {
         List<Meeting> list = meetingMapper.getMeetingById(employeeid);
        LambdaQueryWrapper<MeetingRoom> Wrapper = new LambdaQueryWrapper();
        Wrapper.select(MeetingRoom::getId,MeetingRoom::getRoomname);
        List<MeetingRoom> roomDTOS= meetingRoomMapper.selectList(Wrapper);
        List<RoomDTO> listroomDTO= copyList1(roomDTOS);

         Date now = new Date();

        //?????? ?????????????????????
        List<Meeting> listNext7Day =
                list.stream().filter(s ->
                        s.getEndtime().getTime() >= now.getTime() &&
                                s.getStarttime().getTime() <= now.getTime())
                        .collect(Collectors.toList());

        //??????Map<roomid,roomname>??????????????????,???????????????roomname
        Map<Integer, String> resultMap = listroomDTO.stream().collect(
                HashMap::new,(map, roomDTO) -> map.put(roomDTO.getRoomId(),roomDTO.getRoomName()),
                HashMap::putAll);

        //??????Meeting???SevenDayMeeting,????????????View??????,??????Meeting???????????????
        List<SevenDayMeeting> listDto = listNext7Day.stream().map(meeting -> {

            SevenDayMeeting sevenDayMeeting = new SevenDayMeeting();

            BeanUtils.copyProperties(meeting, sevenDayMeeting);

            sevenDayMeeting.setRoomname(resultMap.get(meeting.getRoomid()));

            return sevenDayMeeting;
        }).collect(Collectors.toList());

        return listDto;
    }

    @Override
    public Result meetingDismiss() {
        return null;
    }

    private List<RoomDTO> copyList1(List<MeetingRoom> records) {
        List<RoomDTO> meetingRoomList = new ArrayList<>();
        for (MeetingRoom record : records) {
            meetingRoomList.add(copy1(record));
        }
        return meetingRoomList;
    }
    private RoomDTO copy1(MeetingRoom meeting){
        RoomDTO meetingDTO = new RoomDTO();
        BeanUtils.copyProperties(meeting,meetingDTO);
        return meetingDTO;
    }

}
