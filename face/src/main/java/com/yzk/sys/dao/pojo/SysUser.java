package com.yzk.sys.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 用户
 */
@Data
public class SysUser {

    //@TableId(type = IdType.ASSIGN_ID)//默认id类型
    // 以后用户多了之后，要进行分表操作，id就需要用分布式id 了
     @TableId(type = IdType.AUTO)
    private Integer id;

    private String account;

    private Integer role;

    private String avatar;

    private Long createDate;

    private Integer deleted;

    private String email;

    private Long lastLogin;

    private String mobilePhoneNumber;

    private String nickname;

    private String password;

    private String salt;

    private String status;
}

