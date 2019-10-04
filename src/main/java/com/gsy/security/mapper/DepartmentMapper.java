package com.gsy.security.mapper;

import com.gsy.security.entity.Department;
import org.apache.ibatis.annotations.*;

public interface DepartmentMapper {

    @Select("select * from department where id = #{id}")
    Department getDeptById(Integer id);

    @Insert("insert into department(department_name) values (#{departmentName})")
    @Options(useGeneratedKeys=true,keyProperty = "id")
    int add(Department department);

    @Update("update department set department_name=#{departmentName} where id=#{id}")
    int update(Department department);

    @Delete("delete from department where id=#{id}")
    int delete(Integer id);

    @Select("select count(1) from department")
    int sum();
}
