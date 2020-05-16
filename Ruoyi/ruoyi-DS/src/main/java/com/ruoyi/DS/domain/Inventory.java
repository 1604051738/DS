package com.ruoyi.DS.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 库存对象 inventory
 * 
 * @author ruoyi
 * @date 2020-05-11
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Inventory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** SKU */
    @Excel(name = "SKU")
    private Integer sku;

    /** 货位 */
    @Excel(name = "货位")
    private String allocation;

    /** 仓库 */
    @Excel(name = "仓库")
    private Long warehouse;

    /** 在库量 */
    @Excel(name = "在库量")
    private Long quantity;

    /** 已分配 */
    @Excel(name = "已分配")
    private Long allocated;

    /** 可售量 */
    @Excel(name = "可售量")
    private Long available;

    /** 采购在途 */
    @Excel(name = "采购在途")
    private Long quantityInTransit;

    /** 最近盘点时间 */
    @Excel(name = "最近盘点时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date takeStockTime;

    /** 最近入库时间 */
    @Excel(name = "最近入库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date stockInTime;

    /** 超卖量 */
    @Excel(name = "超卖量")
    private Long quantityOversell;

    /** 缺货量 */
    @Excel(name = "缺货量")
    private Long oosq;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sku", getSku())
            .append("allocation", getAllocation())
            .append("warehouse", getWarehouse())
            .append("quantity", getQuantity())
            .append("allocated", getAllocated())
            .append("available", getAvailable())
            .append("quantityInTransit", getQuantityInTransit())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("takeStockTime", getTakeStockTime())
            .append("stockInTime", getStockInTime())
            .append("updateBy", getUpdateBy())
            .append("quantityOversell", getQuantityOversell())
            .append("oosq", getOosq())
            .toString();
    }
}
