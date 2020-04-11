package com.ruoyi.DS.domain;

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

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSku(String sku) 
    {
        this.sku = sku;
    }

    public String getSku() 
    {
        return sku;
    }
    public void setAllocation(String allocation) 
    {
        this.allocation = allocation;
    }

    public String getAllocation() 
    {
        return allocation;
    }
    public void setWarehouse(Long warehouse)
    {
        this.warehouse = warehouse;
    }

    public Long getWarehouse()
    {
        return warehouse;
    }
    public void setTqol(String tqol)
    {
        this.tqol = tqol;
    }

    public String getTqol()
    {
        return tqol;
    }
    public void setAllocated(String allocated)
    {
        this.allocated = allocated;
    }

    public String getAllocated()
    {
        return allocated;
    }
    public void setAoa(String aoa)
    {
        this.aoa = aoa;
    }

    public String getAoa()
    {
        return aoa;
    }
    public void setPit(String pit)
    {
        this.pit = pit;
    }

    public String getPit()
    {
        return pit;
    }
    public void setChecktime(Date checktime) 
    {
        this.checktime = checktime;
    }

    public Date getChecktime() 
    {
        return checktime;
    }
    public void setIhtime(Date ihtime) 
    {
        this.ihtime = ihtime;
    }

    public Date getIhtime() 
    {
        return ihtime;
    }
    public void setOversold(String oversold)
    {
        this.oversold = oversold;
    }

    public String getOversold()
    {
        return oversold;
    }
    public void setOosq(String oosq)
    {
        this.oosq = oosq;
    }

    public String getOosq()
    {
        return oosq;
    }

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
