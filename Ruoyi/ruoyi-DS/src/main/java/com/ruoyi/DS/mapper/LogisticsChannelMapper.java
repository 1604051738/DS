package com.ruoyi.DS.mapper;

import com.ruoyi.DS.domain.LogisticsChannel;
import java.util.List;

/**
 * 物流渠道Mapper接口
 * 
 * @author ruoyi
 * @date 2020-01-04
 */
public interface LogisticsChannelMapper 
{
    /**
     * 查询物流渠道
     * 
     * @param id 物流渠道ID
     * @return 物流渠道
     */
    public LogisticsChannel selectLogisticsChannelById(Long id);

    /**
     * 查询物流渠道列表
     * 
     * @param logisticsChannel 物流渠道
     * @return 物流渠道集合
     */
    public List<LogisticsChannel> selectLogisticsChannelList(LogisticsChannel logisticsChannel);

    /**
     * 新增物流渠道
     * 
     * @param logisticsChannel 物流渠道
     * @return 结果
     */
    public int insertLogisticsChannel(LogisticsChannel logisticsChannel);

    /**
     * 修改物流渠道
     * 
     * @param logisticsChannel 物流渠道
     * @return 结果
     */
    public int updateLogisticsChannel(LogisticsChannel logisticsChannel);

    /**
     * 删除物流渠道
     * 
     * @param id 物流渠道ID
     * @return 结果
     */
    public int deleteLogisticsChannelById(Long id);

    /**
     * 批量删除物流渠道
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLogisticsChannelByIds(String[] ids);
}
