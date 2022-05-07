package com.yzk.sys.service;

import com.yzk.sys.dao.pojo.SysUser;
import com.yzk.sys.vo.Result;

public interface SysUserService {

    /**
     * 作者id查询作者信息
     * @return
     */
    SysUser findUserById(Long id);

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


}
