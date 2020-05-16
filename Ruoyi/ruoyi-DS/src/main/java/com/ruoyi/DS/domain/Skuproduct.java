package com.ruoyi.DS.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * SKU单品对象 skuproduct
 * 
 * @author ruoyi
 * @date 2020-04-30
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Skuproduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Integer id;

    /** SKU编码 */
    @Excel(name = "SKU编码")
    private String code;

    /** SKU外部码 */
    @Excel(name = "SKU外部码")
    private String codeExt;

    /** size */
    @Excel(name = "size")
    private String size;

    /** 颜色 */
    @Excel(name = "颜色")
    private String color;

    /** 净重 */
    @Excel(name = "净重")
    private Double netWeight;

    /** 毛重 */
    @Excel(name = "毛重")
    private Double grossWeight;

    /** 成本价(RMB) */
    @Excel(name = "成本价(RMB)")
    private Double costPrice;

    /** 售价 */
    @Excel(name = "售价")
    private Double price;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sortOrder;

    /** 七天销量 */
    @Excel(name = "七天销量")
    private String sales7days;

    /** 七天销量天数 */
    @Excel(name = "七天销量天数")
    private String sales7daysDay;

    /** null */
    @Excel(name = "十四天销量")
    private String sales14days;

    /** null */
    @Excel(name = "十四天销量天数")
    private String sales14daysDay;

    /** 三十天销量 */
    @Excel(name = "三十天销量")
    private String sales30days;

    /** 三十天销量天数 */
    @Excel(name = "三十天销量天数")
    private String sales30daysDay;

    /** 待采购量 */
    @Excel(name = "待采购量")
    private String purchasePlan;

    /** 采购天数 */
    @Excel(name = "采购天数")
    private String purchasePlanDays;

    /** 采购交期 */
    @Excel(name = "采购交期")
    private String purchaseDeliveryDay;

    /** 统计时间 */
    @Excel(name = "统计时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape =JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date salesStatTime;

    /** 库存刊登策略 */
    @Excel(name = "库存刊登策略")
    private String inventoryPublishPolicy;

    /** 商品 */
    @Excel(name = "商品")
    private Long product;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String filepath;

    /** 长 */
    @Excel(name = "长")
    private Double length;

    /** 宽 */
    @Excel(name = "宽")
    private Double width;

    /** 高 */
    @Excel(name = "高")
    private Double height;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("codeExt", getCodeExt())
            .append("size", getSize())
            .append("color", getColor())
            .append("netWeight", getNetWeight())
            .append("grossWeight", getGrossWeight())
            .append("costPrice", getCostPrice())
            .append("price", getPrice())
            .append("status", getStatus())
            .append("sortOrder", getSortOrder())
            .append("sales7days", getSales7days())
            .append("sales7daysDay", getSales7daysDay())
            .append("sales14days", getSales14days())
            .append("sales14daysDay", getSales14daysDay())
            .append("sales30days", getSales30days())
            .append("sales30daysDay", getSales30daysDay())
            .append("purchasePlan", getPurchasePlan())
            .append("purchasePlanDays", getPurchasePlanDays())
            .append("purchaseDeliveryDay", getPurchaseDeliveryDay())
            .append("salesStatTime", getSalesStatTime())
            .append("inventoryPublishPolicy", getInventoryPublishPolicy())
            .append("product", getProduct())
            .append("filepath", getFilepath())
            .append("length", getLength())
            .append("width", getWidth())
            .append("height", getHeight())
            .toString();
    }
}
