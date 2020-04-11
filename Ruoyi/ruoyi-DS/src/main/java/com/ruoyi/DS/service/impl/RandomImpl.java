package com.ruoyi.DS.service.impl;

import com.ruoyi.DS.mapper.RandomNummber;
import com.ruoyi.DS.service.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RandomImpl implements RandomService {
    @Autowired
    private RandomNummber randomNummber;

    @Override
    public Long getNumber() {
        return randomNummber.getNumber();
    }

    @Override
    public Long setNumber(Long id) {
        return randomNummber.setNumber(id);
    }
}
