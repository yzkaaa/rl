package com.yzk.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yzk.sys.dao.mapper.MeetingParticipantsMapper;
import com.yzk.sys.dao.mapper.SysUserMapper;
import com.yzk.sys.dao.pojo.SysUser;
import com.yzk.sys.service.LoginService;
import com.yzk.sys.service.SysUserService;
import com.yzk.sys.vo.ErrorCode;
import com.yzk.sys.vo.LoginUserVo;
import com.yzk.sys.vo.Result;
import com.yzk.sys.vo.params.LoginParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private LoginService loginService;
    @Autowired
    private MeetingParticipantsMapper meetingParticipantsMapper;

    @Override
    public SysUser findUser(String account, String password) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount,account);
        queryWrapper.eq(SysUser::getPassword,password);
        //account id 头像 名称
        queryWrapper.select(SysUser::getAccount,SysUser::getId,SysUser::getAvatar,SysUser::getNickname,SysUser::getRole);
        //增加查询效率，只查询一条
        queryWrapper.last("limit 1");

        //selectOne 查询0或1条信息，大于1会报错
        return sysUserMapper.selectOne(queryWrapper);
    }

     @Override
    public Result findUserByToken(String token) {
        /**
         * 1. token合法性校验
         *    是否为空，解析是否成功 redis是否存在
         * 2. 如果校验失败 返回错误
         * 3. 如果成功，返回对应的结果 LoginUserVo
         */
        SysUser sysUser = loginService.checkToken(token);
        if (sysUser == null){
            return Result.fail(ErrorCode.TOKEN_ERROR.getCode(),ErrorCode.TOKEN_ERROR.getMsg());
        }
        LoginUserVo loginUserVo = new LoginUserVo();
        loginUserVo.setId(sysUser.getId());
        loginUserVo.setNickname(sysUser.getNickname());
        loginUserVo.setAvatar(sysUser.getAvatar());
        loginUserVo.setAccount(sysUser.getAccount());
        return Result.success(loginUserVo);
    }


    @Override
    public SysUser findUserByAccount(String account) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount,account);
        //确保只能查询一条
        queryWrapper.last("limit 1");
        return sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public void save(SysUser sysUser) {
        this.sysUserMapper.insert(sysUser);
    }

    @Override
    public void Revise(SysUser sysUser) {
        this.sysUserMapper.updateById(sysUser);
    }

    @Override
    public Result getEmpsByid(Integer meetingid) {
        List<Integer> allBymeetingid = meetingParticipantsMapper.getAllBymeetingid(meetingid);

        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(SysUser::getId,allBymeetingid);
        List<SysUser> sysUsers = sysUserMapper.selectList(wrapper);
        return Result.success(copyList(sysUsers));
    }

    @Override
    public Result getAllEmps(SysUser sysUser, Integer page, Integer pageSize) {
        //这个page是从第几行数据开始查
        page = (page - 1) * pageSize;
        return  Result.success(copyList(sysUserMapper.getAllEmps(sysUser, page, pageSize)));
    }

    @Override
    public Long getTotal(SysUser sysUser) {
        return sysUserMapper.getTotal(sysUser);
    }

    @Override
    public Result getEmpsByDepId() {
        List<SysUser> sysUsers = sysUserMapper.selectList(null);
        return  Result.success(sysUsers);
    }

    private List<LoginParam> copyList(List<SysUser> records) {
        List<LoginParam> loginParams = new ArrayList<>();
        for (SysUser record : records) {
            loginParams.add(copy(record));
        }
        return loginParams;
    }
      private LoginParam copy(SysUser sysUser){
       LoginParam loginParam = new LoginParam();
        BeanUtils.copyProperties(sysUser,loginParam);
        return loginParam;
    }
}
