package com.ruoyi.DS.service;

import com.ruoyi.DS.domain.Warehouse;
import java.util.List;

/**
 * 仓库Service接口
 * 
 * @author ruoyi
 * @date 2019-12-25
 */
public interface IWarehouseService 
{
    /**
     * 查询仓库
     * 
     * @param id 仓库ID
     * @return 仓库
     */
    public Warehouse selectWarehouseById(Long id);

    /**
     * 查询仓库列表
     * 
     * @param warehouse 仓库
     * @return 仓库集合
     */
    public List<Warehouse> selectWarehouseList(Warehouse warehouse);

    /**
     * 新增仓库
     * 
     * @param warehouse 仓库
     * @return 结果
     */
    public int insertWarehouse(Warehouse warehouse);

    /**
     * 修改仓库
     * 
     * @param warehouse 仓库
     * @return 结果
     */
    public int updateWarehouse(Warehouse warehouse);

    /**
     * 批量删除仓库
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWarehouseByIds(String ids);

    /**
     * 删除仓库信息
     * 
     * @param id 仓库ID
     * @return 结果
     */
    public int deleteWarehouseById(Long id);
}
