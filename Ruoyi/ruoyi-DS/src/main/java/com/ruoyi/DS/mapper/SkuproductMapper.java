package com.ruoyi.DS.mapper;

import com.ruoyi.DS.domain.Skuproduct;

import java.util.List;

/**
 * SKU单品Mapper接口
 * 
 * @author ruoyi
 * @date 2020-04-30
 */
public interface SkuproductMapper 
{
    /**
     * 查询SKU单品
     * 
     * @param id SKU单品ID
     * @return SKU单品
     */
    public Skuproduct selectSkuproductById(Integer id);


    /**
     * 根据code查询sku单品
     * @param code
     * @return
     */
    public Skuproduct selectSkuproductByCode(String code);
    /**
     * 查询SKU单品列表
     * 
     * @param skuproduct SKU单品
     * @return SKU单品集合
     */
    public List<Skuproduct> selectSkuproductList(Skuproduct skuproduct);

    /**
     * 新增SKU单品
     * 
     * @param skuproduct SKU单品
     * @return 结果
     */
    public int insertSkuproduct(Skuproduct skuproduct);

    /**
     * 修改SKU单品
     * 
     * @param skuproduct SKU单品
     * @return 结果
     */
    public int updateSkuproduct(Skuproduct skuproduct);

    /**
     * 删除SKU单品
     * 
     * @param id SKU单品ID
     * @return 结果
     */
    public int deleteSkuproductById(Long id);

    /**
     * 批量删除SKU单品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSkuproductByIds(String[] ids);
}
