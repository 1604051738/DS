package com.ruoyi.DS.service;

import com.ruoyi.DS.domain.Supplier;
import java.util.List;

/**
 * 供应商Service接口
 * 
 * @author ruoyi
 * @date 2019-12-26
 */
public interface ISupplierService 
{
    /**
     * 查询供应商
     * 
     * @param id 供应商ID
     * @return 供应商
     */
    public Supplier selectSupplierById(Long id);


    /**
     * 查询供应商列表
     * 
     * @param supplier 供应商
     * @return 供应商集合
     */
    public List<Supplier> selectSupplierList(Supplier supplier);

    /**
     * 新增供应商
     * 
     * @param supplier 供应商
     * @return 结果
     */
    public int insertSupplier(Supplier supplier);

    /**
     * 修改供应商
     * 
     * @param supplier 供应商
     * @return 结果
     */
    public int updateSupplier(Supplier supplier);

    /**
     * 批量删除供应商
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSupplierByIds(String ids);

    /**
     * 删除供应商信息
     * 
     * @param id 供应商ID
     * @return 结果
     */
    public int deleteSupplierById(Long id);
}
