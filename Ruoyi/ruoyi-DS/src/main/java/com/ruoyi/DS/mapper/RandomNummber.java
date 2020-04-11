package com.ruoyi.DS.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RandomNummber {

    @Select("select number_random from randomNumber where id =1 ")
    public Long getNumber();

    @Update("UPDATE randomnumber SET number_random = #{id} WHERE id = 1")
    public Long setNumber(@Param("id") Long id);
}
