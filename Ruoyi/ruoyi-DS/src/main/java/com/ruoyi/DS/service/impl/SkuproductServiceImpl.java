package com.ruoyi.DS.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.DS.mapper.SkuproductMapper;
import com.ruoyi.DS.domain.Skuproduct;
import com.ruoyi.DS.service.ISkuproductService;
import com.ruoyi.common.core.text.Convert;

/**
 * SKU单品Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-04-30
 */
@Service
public class SkuproductServiceImpl implements ISkuproductService 
{
    @Autowired
    private SkuproductMapper skuproductMapper;

    /**
     * 查询SKU单品
     * 
     * @param id SKU单品ID
     * @return SKU单品
     */
    @Override
    public Skuproduct selectSkuproductById(Integer id)
    {
        return skuproductMapper.selectSkuproductById(id);
    }

    @Override
    public Skuproduct selectSkuproductByCode(String code) {
        return skuproductMapper.selectSkuproductByCode(code);
    }

    /**
     * 查询SKU单品列表
     * 
     * @param skuproduct SKU单品
     * @return SKU单品
     */
    @Override
    public List<Skuproduct> selectSkuproductList(Skuproduct skuproduct)
    {
        return skuproductMapper.selectSkuproductList(skuproduct);
    }

    /**
     * 新增SKU单品
     * 
     * @param skuproduct SKU单品
     * @return 结果
     */
    @Override
    public int insertSkuproduct(Skuproduct skuproduct)
    {
        return skuproductMapper.insertSkuproduct(skuproduct);
    }

    /**
     * 修改SKU单品
     * 
     * @param skuproduct SKU单品
     * @return 结果
     */
    @Override
    public int updateSkuproduct(Skuproduct skuproduct)
    {
        return skuproductMapper.updateSkuproduct(skuproduct);
    }

    /**
     * 删除SKU单品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSkuproductByIds(String ids)
    {
        return skuproductMapper.deleteSkuproductByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除SKU单品信息
     * 
     * @param id SKU单品ID
     * @return 结果
     */
    @Override
    public int deleteSkuproductById(Long id)
    {
        return skuproductMapper.deleteSkuproductById(id);
    }
}
