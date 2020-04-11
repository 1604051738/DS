package com.ruoyi.DS.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 销售订单对象 sales_order
 * 
 * @author ruoyi
 * @date 2019-12-30
 */
public class SalesOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String saleId;

    /** 原订单id */
    @Excel(name = "原订单id")
    private String saleIdO;

    /** 总金额 */
    @Excel(name = "总金额")
    private BigDecimal totalCost;

    /** 总采购货本 */
    @Excel(name = "总采购货本")
    private BigDecimal totalPurchase;

    /** 预计运费 */
    @Excel(name = "预计运费")
    private BigDecimal exceptFreight;

    /** 货代运费 */
    @Excel(name = "货代运费")
    private BigDecimal forwarderFreight;

    /** 收货地址 */
    @Excel(name = "收货地址")
    private String address;

    /** 物流跟踪号 */
    @Excel(name = "物流跟踪号")
    private String salesOrdercol;

    /** 外部物流订单ID */
    @Excel(name = "外部物流订单ID")
    private String salesOrdercolE;

    /** 出库重量 */
    @Excel(name = "出库重量")
    private Long outboundWeight;

    /** 数量类型 */
    @Excel(name = "数量类型")
    private String numberType;

    /** 是否退款 */
    @Excel(name = "是否退款")
    private Long isRefunded;

    /** 退款时间 */
    @Excel(name = "退款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date refundedTime;

    /** 退款原因 */
    @Excel(name = "退款原因")
    private String refundedReason;

    /** 备注给用户 */
    @Excel(name = "备注给用户")
    private String remarkToCustom;

    /** 发货仓库 */
    @Excel(name = "发货仓库")
    private Long warehouse;

    /** 货代重量 */
    @Excel(name = "货代重量")
    private Long forwarderWeight;

    /** 转单号 */
    @Excel(name = "转单号")
    private String newOdd;

    /** 物流渠道 */
    @Excel(name = "物流渠道")
    private Long logisticsChannel;

    /** 退款金额 */
    @Excel(name = "退款金额")
    private BigDecimal refundedAmount;

    /** 总重 */
    @Excel(name = "总重")
    private Long totalWeight;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private Long orderStatus;

    /** 买家ID */
    @Excel(name = "买家ID")
    private Long customId;

    /** 付款状态 */
    @Excel(name = "付款状态")
    private Long payStatus;

    /** 物流跟踪状态 */
    @Excel(name = "物流跟踪状态")
    private String logisticsStatus;

    /** 是否为换货订单 */
    @Excel(name = "是否为换货订单")
    private String isExchange;

    /** 出库时间 */
    @Excel(name = "出库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date storageTime;

    /** 标记发货时间 */
    @Excel(name = "标记发货时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliverRemarkTime;

    /** 物流方接单时间 */
    @Excel(name = "物流方接单时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date logisticsAcceptTime;

    /** 下载时间 */
    @Excel(name = "下载时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dowloadTime;

    /** 客户购买时间 */
    @Excel(name = "客户购买时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date customBuyTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSaleId(String saleId) 
    {
        this.saleId = saleId;
    }

    public String getSaleId() 
    {
        return saleId;
    }
    public void setSaleIdO(String saleIdO) 
    {
        this.saleIdO = saleIdO;
    }

    public String getSaleIdO() 
    {
        return saleIdO;
    }
    public void setTotalCost(BigDecimal totalCost)
    {
        this.totalCost = totalCost;
    }

    public BigDecimal getTotalCost()
    {
        return totalCost;
    }
    public void setTotalPurchase(BigDecimal totalPurchase)
    {
        this.totalPurchase = totalPurchase;
    }

    public BigDecimal getTotalPurchase()
    {
        return totalPurchase;
    }
    public void setExceptFreight(BigDecimal exceptFreight)
    {
        this.exceptFreight = exceptFreight;
    }

    public BigDecimal getExceptFreight()
    {
        return exceptFreight;
    }
    public void setForwarderFreight(BigDecimal forwarderFreight)
    {
        this.forwarderFreight = forwarderFreight;
    }

    public BigDecimal getForwarderFreight()
    {
        return forwarderFreight;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setSalesOrdercol(String salesOrdercol) 
    {
        this.salesOrdercol = salesOrdercol;
    }

    public String getSalesOrdercol() 
    {
        return salesOrdercol;
    }
    public void setSalesOrdercolE(String salesOrdercolE) 
    {
        this.salesOrdercolE = salesOrdercolE;
    }

    public String getSalesOrdercolE() 
    {
        return salesOrdercolE;
    }
    public void setOutboundWeight(Long outboundWeight) 
    {
        this.outboundWeight = outboundWeight;
    }

    public Long getOutboundWeight() 
    {
        return outboundWeight;
    }
    public void setNumberType(String numberType) 
    {
        this.numberType = numberType;
    }

    public String getNumberType() 
    {
        return numberType;
    }
    public void setIsRefunded(Long isRefunded) 
    {
        this.isRefunded = isRefunded;
    }

    public Long getIsRefunded() 
    {
        return isRefunded;
    }
    public void setRefundedTime(Date refundedTime) 
    {
        this.refundedTime = refundedTime;
    }

    public Date getRefundedTime() 
    {
        return refundedTime;
    }
    public void setRefundedReason(String refundedReason)
    {
        this.refundedReason = refundedReason;
    }

    public String getRefundedReason()
    {
        return refundedReason;
    }
    public void setRemarkToCustom(String remarkToCustom) 
    {
        this.remarkToCustom = remarkToCustom;
    }

    public String getRemarkToCustom() 
    {
        return remarkToCustom;
    }
    public void setWarehouse(Long warehouse)
    {
        this.warehouse = warehouse;
    }

    public Long getWarehouse()
    {
        return warehouse;
    }
    public void setForwarderWeight(Long forwarderWeight) 
    {
        this.forwarderWeight = forwarderWeight;
    }

    public Long getForwarderWeight() 
    {
        return forwarderWeight;
    }
    public void setNewOdd(String newOdd) 
    {
        this.newOdd = newOdd;
    }

    public String getNewOdd()
    {
        return newOdd;
    }
    public void setLogisticsChannel(Long logisticsChannel)
    {
        this.logisticsChannel = logisticsChannel;
    }

    public Long getLogisticsChannel()
    {
        return logisticsChannel;
    }
    public void setRefundedAmount(BigDecimal refundedAmount)
    {
        this.refundedAmount = refundedAmount;
    }

    public BigDecimal getRefundedAmount()
    {
        return refundedAmount;
    }
    public void setTotalWeight(Long totalWeight) 
    {
        this.totalWeight = totalWeight;
    }

    public Long getTotalWeight() 
    {
        return totalWeight;
    }
    public void setOrderStatus(Long orderStatus) 
    {
        this.orderStatus = orderStatus;
    }

    public Long getOrderStatus() 
    {
        return orderStatus;
    }
    public void setCustomId(Long customId)
    {
        this.customId = customId;
    }

    public Long getCustomId()
    {
        return customId;
    }
    public void setPayStatus(Long payStatus) 
    {
        this.payStatus = payStatus;
    }

    public Long getPayStatus() 
    {
        return payStatus;
    }
    public void setLogisticsStatus(String logisticsStatus) 
    {
        this.logisticsStatus = logisticsStatus;
    }

    public String getLogisticsStatus() 
    {
        return logisticsStatus;
    }
    public void setIsExchange(String isExchange) 
    {
        this.isExchange = isExchange;
    }

    public String getIsExchange() 
    {
        return isExchange;
    }
    public void setStorageTime(Date storageTime) 
    {
        this.storageTime = storageTime;
    }

    public Date getStorageTime() 
    {
        return storageTime;
    }
    public void setDeliverRemarkTime(Date deliverRemarkTime) 
    {
        this.deliverRemarkTime = deliverRemarkTime;
    }

    public Date getDeliverRemarkTime() 
    {
        return deliverRemarkTime;
    }
    public void setLogisticsAcceptTime(Date logisticsAcceptTime) 
    {
        this.logisticsAcceptTime = logisticsAcceptTime;
    }

    public Date getLogisticsAcceptTime() 
    {
        return logisticsAcceptTime;
    }
    public void setDowloadTime(Date dowloadTime) 
    {
        this.dowloadTime = dowloadTime;
    }

    public Date getDowloadTime() 
    {
        return dowloadTime;
    }
    public void setCustomBuyTime(Date customBuyTime) 
    {
        this.customBuyTime = customBuyTime;
    }

    public Date getCustomBuyTime() 
    {
        return customBuyTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("saleId", getSaleId())
            .append("saleIdO", getSaleIdO())
            .append("totalCost", getTotalCost())
            .append("totalPurchase", getTotalPurchase())
            .append("exceptFreight", getExceptFreight())
            .append("forwarderFreight", getForwarderFreight())
            .append("address", getAddress())
            .append("salesOrdercol", getSalesOrdercol())
            .append("salesOrdercolE", getSalesOrdercolE())
            .append("outboundWeight", getOutboundWeight())
            .append("numberType", getNumberType())
            .append("isRefunded", getIsRefunded())
            .append("refundedTime", getRefundedTime())
            .append("refundedReason", getRefundedReason())
            .append("remark", getRemark())
            .append("remarkToCustom", getRemarkToCustom())
            .append("warehouse", getWarehouse())
            .append("forwarderWeight", getForwarderWeight())
            .append("newOdd", getNewOdd())
            .append("logisticsChannel", getLogisticsChannel())
            .append("refunded amount", getRefundedAmount())
            .append("totalWeight", getTotalWeight())
            .append("orderStatus", getOrderStatus())
            .append("customId", getCustomId())
            .append("payStatus", getPayStatus())
            .append("logisticsStatus", getLogisticsStatus())
            .append("isExchange", getIsExchange())
            .append("storageTime", getStorageTime())
            .append("deliverRemarkTime", getDeliverRemarkTime())
            .append("logisticsAcceptTime", getLogisticsAcceptTime())
            .append("dowloadTime", getDowloadTime())
            .append("customBuyTime", getCustomBuyTime())
            .append("createBy", getCreateBy())
            .toString();
    }
}
