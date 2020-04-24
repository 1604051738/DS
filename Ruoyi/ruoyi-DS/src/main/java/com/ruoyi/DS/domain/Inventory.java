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
 * @date 2019-12-26
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
    private String sku;

    /** 货位 */
    @Excel(name = "货位")
    private String allocation;

    /** 仓库 */
    @Excel(name = "仓库")
    private Long warehouse;

    /** 在库量 */
    @Excel(name = "在库量")
    private String tqol;

    /** 已分配量 */
    @Excel(name = "已分配量")
    private String allocated;

    /** 可售量 */
    @Excel(name = "可售量")
    private String aoa;

    /** 采购在途量 */
    @Excel(name = "采购在途量")
    private String pit;

    /** 最近盘点时间 */
    @Excel(name = "最近盘点时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checktime;

    /** 最近入库时间 */
    @Excel(name = "最近入库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ihtime;

    /** 超卖量 */
    @Excel(name = "超卖量")
    private String oversold;

    /** 缺货量 */
    @Excel(name = "缺货量")
    private String oosq;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sku", getSku())
            .append("allocation", getAllocation())
            .append("warehouse", getWarehouse())
            .append("tqol", getTqol())
            .append("allocated", getAllocated())
            .append("aoa", getAoa())
            .append("pit", getPit())
            .append("createby", getCreateBy())
            .append("createtime", getCreateTime())
            .append("checktime", getChecktime())
            .append("ihtime", getIhtime())
            .append("updateby", getUpdateBy())
            .append("oversold", getOversold())
            .append("oosq", getOosq())
            .toString();
    }
}
