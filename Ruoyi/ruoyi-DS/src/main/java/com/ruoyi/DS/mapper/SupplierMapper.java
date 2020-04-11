package com.ruoyi.DS.mapper;

import com.ruoyi.DS.domain.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应商Mapper接口
 * 
 * @author ruoyi
 * @date 2019-12-26
 */
public interface SupplierMapper 
{
    /**
     * 查询供应商
     * 
     * @param id 供应商ID
     * @return 供应商
     */
    public Supplier selectSupplierById(Long id);

    public Supplier selectSupplierByName(@Param("name") String name);

    public Supplier selectSupplierBySc(@Param("sc") String sc);
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
     * 删除供应商
     * 
     * @param id 供应商ID
     * @return 结果
     */
    public int deleteSupplierById(Long id);

    /**
     * 批量删除供应商
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSupplierByIds(String[] ids);
}
