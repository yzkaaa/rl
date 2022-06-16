package com.yzk.sys.service;

import com.yzk.sys.dao.pojo.SysUser;
import com.yzk.sys.vo.Result;

public interface SysUserService {

    /**
     * 验证账户是否存在
     * @param account 账户
     * @param password 密码
     * @return
     */
    SysUser findUser(String account, String password);

    /**
     *  根据token查询用户信息
     * @param token
     * @return
     */
    Result findUserByToken(String token);


    /**
     * 根据账户查找用户
     * @param account
     * @return
     */
    SysUser findUserByAccount(String account);
    /**
     * 保存用户
     * @param sysUser
     */
    void save(SysUser sysUser);

    void Revise(SysUser sysUser);
    /**
     * 通过meetingid获取员工信息
     *
     * @param meetingid Integer
     * @return
     */
     Result getEmpsByid(Integer meetingid);

      /**
     * 分页查询获得所有Emps
     *
     * @param sysUser 员工
     * @param page     页数
     * @param pageSize 查几条数据
     * @return
     */
     Result getAllEmps(SysUser sysUser, Integer page, Integer pageSize);
    /**
     * 获取员工数量
     *
     * @param sysUser 员工
     * @return 员工数量
     */
    Long getTotal(SysUser sysUser) ;

    /**
     * 获取员工信息
     * @return
     */
    Result getEmpsByDepId();
}
