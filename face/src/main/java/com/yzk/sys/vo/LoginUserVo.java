package com.yzk.sys.vo;

import lombok.Data;

@Data
public class LoginUserVo {
	//与页面交互

    private Integer id;

    private String account;

    private String nickname;

    private String avatar;
}

