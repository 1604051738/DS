package com.ruoyi.DS.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.DS.mapper.ShippingAddressMapper;
import com.ruoyi.DS.domain.ShippingAddress;
import com.ruoyi.DS.service.IShippingAddressService;
import com.ruoyi.common.core.text.Convert;

/**
 * 收货人信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-06
 */
@Service
public class ShippingAddressServiceImpl implements IShippingAddressService 
{
    @Autowired
    private ShippingAddressMapper shippingAddressMapper;

    /**
     * 查询收货人信息
     * 
     * @param id 收货人信息ID
     * @return 收货人信息
     */
    @Override
    public ShippingAddress selectShippingAddressById(Long id)
    {
        return shippingAddressMapper.selectShippingAddressById(id);
    }

    /**
     * 查询收货人信息列表
     * 
     * @param shippingAddress 收货人信息
     * @return 收货人信息
     */
    @Override
    public List<ShippingAddress> selectShippingAddressList(ShippingAddress shippingAddress)
    {
        return shippingAddressMapper.selectShippingAddressList(shippingAddress);
    }

    /**
     * 新增收货人信息
     * 
     * @param shippingAddress 收货人信息
     * @return 结果
     */
    @Override
    public int insertShippingAddress(ShippingAddress shippingAddress)
    {
        return shippingAddressMapper.insertShippingAddress(shippingAddress);
    }

    /**
     * 修改收货人信息
     * 
     * @param shippingAddress 收货人信息
     * @return 结果
     */
    @Override
    public int updateShippingAddress(ShippingAddress shippingAddress)
    {
        return shippingAddressMapper.updateShippingAddress(shippingAddress);
    }

    /**
     * 删除收货人信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteShippingAddressByIds(String ids)
    {
        return shippingAddressMapper.deleteShippingAddressByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除收货人信息信息
     * 
     * @param id 收货人信息ID
     * @return 结果
     */
    @Override
    public int deleteShippingAddressById(Long id)
    {
        return shippingAddressMapper.deleteShippingAddressById(id);
    }
}
