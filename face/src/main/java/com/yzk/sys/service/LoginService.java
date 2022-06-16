package com.yzk.sys.service;


import com.yzk.sys.dao.pojo.SysUser;
import com.yzk.sys.vo.Result;
import com.yzk.sys.vo.params.LoginParam;
import org.springframework.transaction.annotation.Transactional;

@Transactional//出错就回滚防止异常
public interface LoginService {

    /**
     * 登录功能
     * @param loginParam
     * @return
     */
    Result login(LoginParam loginParam);

    /**
     * 检验token是否合法
     * @param token
     * @return
     */
    SysUser checkToken(String token);

    /**
     * 退出登录
     * @param token
     * @return
     */
    Result logout(String token);

    /**
     * 注册用户
     * @param loginParam
     * @return
     */
    Result register(LoginParam loginParam);

    /**
     * 修改密码
     * @param loginParam
     */
    Result Change(LoginParam loginParam);
}
