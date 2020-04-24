package com.ruoyi.DS.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 单品项对象 order_sku
 * 
 * @author admin
 * @date 2020-04-24
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderSku extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 平台订单项ID */
    @Excel(name = "平台订单项ID")
    private String platformOrderItemId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String productName;

    /** SKU编码 */
    @Excel(name = "SKU编码")
    private String skuCode;

    /** 大小尺码 */
    @Excel(name = "大小尺码")
    private String size;

    /** 颜色 */
    @Excel(name = "颜色")
    private String color;

    /** 数量 */
    @Excel(name = "数量")
    private Long quantity;

    /** 已分配 */
    @Excel(name = "已分配")
    private String allocated;

    /** 已释放 */
    @Excel(name = "已释放")
    private Integer released;

    /** 单个售价 */
    @Excel(name = "单个售价")
    private Double price;

    /** 运费 */
    @Excel(name = "运费")
    private Double shipping;

    /** 重量(克) */
    @Excel(name = "重量(克)")
    private Double weight;

    /** 采购货本 */
    @Excel(name = "采购货本")
    private Double purchaseCost;

    /** skuproduct外键 */
    @Excel(name = "skuproduct外键")
    private Integer productSkuId;

    /** salesorder外键 */
    @Excel(name = "salesorder外键")
    private Integer salesOrderId;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("platformOrderItemId", getPlatformOrderItemId())
            .append("productName", getProductName())
            .append("skuCode", getSkuCode())
            .append("size", getSize())
            .append("color", getColor())
            .append("quantity", getQuantity())
            .append("allocated", getAllocated())
            .append("released", getReleased())
            .append("price", getPrice())
            .append("shipping", getShipping())
            .append("weight", getWeight())
            .append("purchaseCost", getPurchaseCost())
            .append("productSkuId", getProductSkuId())
            .append("salesOrderId", getSalesOrderId())
            .toString();
    }
}
