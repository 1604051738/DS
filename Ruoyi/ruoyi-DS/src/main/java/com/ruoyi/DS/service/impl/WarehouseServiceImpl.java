package com.ruoyi.DS.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.DS.mapper.WarehouseMapper;
import com.ruoyi.DS.domain.Warehouse;
import com.ruoyi.DS.service.IWarehouseService;
import com.ruoyi.common.core.text.Convert;

/**
 * 仓库Service业务层处理
 * 
 * @author ruoyi
 * @date 2019-12-25
 */
@Service
public class WarehouseServiceImpl implements IWarehouseService 
{
    @Autowired
    private WarehouseMapper warehouseMapper;

    /**
     * 查询仓库
     * 
     * @param id 仓库ID
     * @return 仓库
     */
    @Override
    public Warehouse selectWarehouseById(Long id)
    {
        return warehouseMapper.selectWarehouseById(id);
    }

    /**
     * 查询仓库列表
     * 
     * @param warehouse 仓库
     * @return 仓库
     */
    @Override
    public List<Warehouse> selectWarehouseList(Warehouse warehouse)
    {
        return warehouseMapper.selectWarehouseList(warehouse);
    }

    /**
     * 新增仓库
     * 
     * @param warehouse 仓库
     * @return 结果
     */
    @Override
    public int insertWarehouse(Warehouse warehouse)
    {
        return warehouseMapper.insertWarehouse(warehouse);
    }

    /**
     * 修改仓库
     * 
     * @param warehouse 仓库
     * @return 结果
     */
    @Override
    public int updateWarehouse(Warehouse warehouse)
    {
        return warehouseMapper.updateWarehouse(warehouse);
    }

    /**
     * 删除仓库对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWarehouseByIds(String ids)
    {
        return warehouseMapper.deleteWarehouseByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除仓库信息
     * 
     * @param id 仓库ID
     * @return 结果
     */
    @Override
    public int deleteWarehouseById(Long id)
    {
        return warehouseMapper.deleteWarehouseById(id);
    }
}
