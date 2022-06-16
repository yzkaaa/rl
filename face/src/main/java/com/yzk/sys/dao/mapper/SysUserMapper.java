package com.yzk.sys.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzk.sys.dao.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 分页获得员工
     *
     * @param sysUser 员工
     * @param page     从第几行数据开始查
     * @param pageSize 查几条数据
     * @return List<Employee>
     */
    List<SysUser> getAllEmps(@Param("emp") SysUser sysUser, @Param("page") Integer page,
                             @Param("pagesize") Integer pageSize);

    /**
     * 获得员工的数量
     *
     * @param sysUser 员工
     * @return 数量
     */
    Long getTotal(SysUser sysUser);
}
