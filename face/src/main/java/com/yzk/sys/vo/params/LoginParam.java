package com.yzk.sys.vo.params;

import lombok.Data;

@Data
public class LoginParam {

    private Integer role;

    private String email;

    private String account;

    private String password;

    private String avatar;//头像

    private String nickname;//用户名

    private String newpassword;//新密码

    private String mobilePhoneNumber;//手机号


}

