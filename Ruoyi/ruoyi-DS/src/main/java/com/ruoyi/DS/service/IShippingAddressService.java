package com.ruoyi.DS.service;

import com.ruoyi.DS.domain.ShippingAddress;
import java.util.List;

/**
 * 收货人信息Service接口
 * 
 * @author ruoyi
 * @date 2020-05-06
 */
public interface IShippingAddressService 
{
    /**
     * 查询收货人信息
     * 
     * @param id 收货人信息ID
     * @return 收货人信息
     */
    public ShippingAddress selectShippingAddressById(Long id);

    /**
     * 查询收货人信息列表
     * 
     * @param shippingAddress 收货人信息
     * @return 收货人信息集合
     */
    public List<ShippingAddress> selectShippingAddressList(ShippingAddress shippingAddress);

    /**
     * 新增收货人信息
     * 
     * @param shippingAddress 收货人信息
     * @return 结果
     */
    public int insertShippingAddress(ShippingAddress shippingAddress);

    /**
     * 修改收货人信息
     * 
     * @param shippingAddress 收货人信息
     * @return 结果
     */
    public int updateShippingAddress(ShippingAddress shippingAddress);

    /**
     * 批量删除收货人信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteShippingAddressByIds(String ids);

    /**
     * 删除收货人信息信息
     * 
     * @param id 收货人信息ID
     * @return 结果
     */
    public int deleteShippingAddressById(Long id);
}
