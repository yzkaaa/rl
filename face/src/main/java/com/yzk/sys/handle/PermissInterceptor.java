package com.yzk.sys.handle;

import com.yzk.sys.dao.pojo.SysUser;
import com.yzk.sys.service.LoginService;
import com.yzk.sys.utils.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 拦截器
 */
@Component
public class PermissInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginService loginService;
    /**
     * 默认页面、登陆相关页面、注册相关页面放行不拦截
     * role：1为普通用户  2为管理员
     *
     * @param request
     * @param response
     * @param handler
     * @return true为放行 false拦截
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        SysUser sysUser = loginService.checkToken(token);
        System.out.println(sysUser);

        if (sysUser.getRole() == 2) {
            return true;
        } else {
            return false;
        }
    }

}
