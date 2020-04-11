package com.ruoyi.DS.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.DS.mapper.LogisticsChannelMapper;
import com.ruoyi.DS.domain.LogisticsChannel;
import com.ruoyi.DS.service.ILogisticsChannelService;
import com.ruoyi.common.core.text.Convert;

/**
 * 物流渠道Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-01-04
 */
@Service
public class LogisticsChannelServiceImpl implements ILogisticsChannelService 
{
    @Autowired
    private LogisticsChannelMapper logisticsChannelMapper;

    /**
     * 查询物流渠道
     * 
     * @param id 物流渠道ID
     * @return 物流渠道
     */
    @Override
    public LogisticsChannel selectLogisticsChannelById(Long id)
    {
        return logisticsChannelMapper.selectLogisticsChannelById(id);
    }

    /**
     * 查询物流渠道列表
     * 
     * @param logisticsChannel 物流渠道
     * @return 物流渠道
     */
    @Override
    public List<LogisticsChannel> selectLogisticsChannelList(LogisticsChannel logisticsChannel)
    {
        return logisticsChannelMapper.selectLogisticsChannelList(logisticsChannel);
    }

    /**
     * 新增物流渠道
     * 
     * @param logisticsChannel 物流渠道
     * @return 结果
     */
    @Override
    public int insertLogisticsChannel(LogisticsChannel logisticsChannel)
    {
        return logisticsChannelMapper.insertLogisticsChannel(logisticsChannel);
    }

    /**
     * 修改物流渠道
     * 
     * @param logisticsChannel 物流渠道
     * @return 结果
     */
    @Override
    public int updateLogisticsChannel(LogisticsChannel logisticsChannel)
    {
        return logisticsChannelMapper.updateLogisticsChannel(logisticsChannel);
    }

    /**
     * 删除物流渠道对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteLogisticsChannelByIds(String ids)
    {
        return logisticsChannelMapper.deleteLogisticsChannelByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除物流渠道信息
     * 
     * @param id 物流渠道ID
     * @return 结果
     */
    @Override
    public int deleteLogisticsChannelById(Long id)
    {
        return logisticsChannelMapper.deleteLogisticsChannelById(id);
    }
}
