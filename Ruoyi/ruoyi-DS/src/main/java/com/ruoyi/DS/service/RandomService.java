package com.ruoyi.DS.service;

import org.apache.ibatis.annotations.Param;


public interface RandomService {

    public Long getNumber();

    public Long setNumber(@Param("id") Long id);
}
