package com.yzk.sys.utils;


import com.yzk.sys.dao.pojo.SysUser;

public class UserThreadLocal {

    private UserThreadLocal(){}
    //线程变量隔离
    /**  每个线程都有的一个ThreadLocal，线程之间的ThreadLocal没有关联
     * 在每个线程中存储每个线程的信息时，使用ThreadLocal
     */
    private static final ThreadLocal<SysUser> LOCAL = new ThreadLocal<>();

    //存放
    public static void put(SysUser sysUser){
        LOCAL.set(sysUser);
    }
    //读取
    public static SysUser get(){
        return LOCAL.get();
    }
    //删除
    public static void remove(){
        LOCAL.remove();
    }
}

