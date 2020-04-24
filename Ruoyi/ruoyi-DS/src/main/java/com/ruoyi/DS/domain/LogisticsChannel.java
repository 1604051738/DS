package com.ruoyi.DS.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.annotation.sql.DataSourceDefinitions;

/**
 * 物流渠道对象 logistics_channel
 * 
 * @author ruoyi
 * @date 2020-01-04
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LogisticsChannel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 物流名称 */
    @Excel(name = "物流名称")
    private String logisticsName;

    /** 物流编码 */
    @Excel(name = "物流编码")
    private String logisticsCode;

    /** 物流商物流名称 */
    @Excel(name = "物流商物流名称")
    private String logisticsProviderName;

    /** 物流商编码 */
    @Excel(name = "物流商编码")
    private String logisticsProviderCode;

    /** 起重（克） */
    @Excel(name = "起重", readConverterExp = "克=")
    private Long minWeight;

    /** 最大（克） */
    @Excel(name = "最大", readConverterExp = "克=")
    private Long maxWeight;

    /** 体积重 */
    @Excel(name = "体积重")
    private Long volumeWeight;

    /** 敏感类型 */
    @Excel(name = "敏感类型")
    private String sensitiveType;

    /** 优先级 */
    @Excel(name = "优先级")
    private String priority;

    /** 是否挂号渠道 */
    @Excel(name = "是否挂号渠道")
    private String isRegistration;

    /** 发件人地址 */
    @Excel(name = "发件人地址")
    private String senderAddress;

    /** 揽收地址 */
    @Excel(name = "揽收地址")
    private String lanshouAddress;

    /** 是否停用 */
    @Excel(name = "是否停用")
    private String isStop;

    /** 线上结算 */
    @Excel(name = "线上结算")
    private String onlineSettlement;

    /** 物流商（公司） */
    @Excel(name = "物流商", readConverterExp = "公=司")
    private String logisticsCompany;

    /** 物流商（公司）编码 */
    @Excel(name = "物流商", readConverterExp = "公=司")
    private String logisticsCompanyCode;

    /** 所属仓库 */
    @Excel(name = "所属仓库")
    private String warehouse;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("logisticsName", getLogisticsName())
            .append("logisticsCode", getLogisticsCode())
            .append("logisticsProviderName", getLogisticsProviderName())
            .append("logisticsProviderCode", getLogisticsProviderCode())
            .append("minWeight", getMinWeight())
            .append("maxWeight", getMaxWeight())
            .append("volumeWeight", getVolumeWeight())
            .append("sensitiveType", getSensitiveType())
            .append("priority", getPriority())
            .append("isRegistration", getIsRegistration())
            .append("senderAddress", getSenderAddress())
            .append("lanshouAddress", getLanshouAddress())
            .append("isStop", getIsStop())
            .append("onlineSettlement", getOnlineSettlement())
            .append("logisticsCompany", getLogisticsCompany())
            .append("logisticsCompanyCode", getLogisticsCompanyCode())
            .append("warehouse", getWarehouse())
            .toString();
    }
}
