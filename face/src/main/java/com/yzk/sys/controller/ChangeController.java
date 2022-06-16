package com.yzk.sys.controller;


import com.yzk.sys.service.LoginService;
import com.yzk.sys.vo.Result;
import com.yzk.sys.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChangeController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/change")
    public Result Change(@RequestBody LoginParam loginParam){
        return loginService.Change(loginParam);
    }

}