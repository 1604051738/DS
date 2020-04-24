package com.ruoyi.DS.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 销售订单对象 sales_order
 * 
 * @author admin
 * @date 2020-04-23
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Integer id;

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
    private BigDecimal outboundWeight;

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
    private BigDecimal forwarderWeight;

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
    private BigDecimal totalWeight;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private Long orderStatus;

    /** 买家ID */
    @Excel(name = "买家ID")
    private String customId;

    /** 付款状态 */
    @Excel(name = "付款状态")
    private Long payStatus;

    /** 物流跟踪状态 */
    @Excel(name = "物流跟踪状态")
    private String logisticsStatus;

    /** 是否为换货订单 */
    @Excel(name = "是否为换货订单")
    private Long isExchange;

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
            .append("refundedAmount", getRefundedAmount())
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
