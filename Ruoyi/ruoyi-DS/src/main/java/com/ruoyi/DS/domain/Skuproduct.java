package com.ruoyi.DS.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * SKU单品对象 skuproduct
 * 
 * @author ruoyi
 * @date 2019-12-26
 */
public class Skuproduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** SKU编码 */
    @Excel(name = "SKU编码")
    private String skucode;

    /** SKU外部码 */
    @Excel(name = "SKU外部码")
    private String skuocode;

    /** size */
    @Excel(name = "size")
    private String size;

    /** 颜色 */
    @Excel(name = "颜色")
    private String color;

    /** 净重 */
    @Excel(name = "净重")
    private String suttle;

    /** 毛重 */
    @Excel(name = "毛重")
    private String rw;

    /** 成本价(RMB) */
    @Excel(name = "成本价(RMB)")
    private String costprice;

    /** 售价 */
    @Excel(name = "售价")
    private String sellprice;

    /** 状态 */
    @Excel(name = "状态")
    private Integer state;

    /** 排序 */
    @Excel(name = "排序")
    private String rank;

    /** 七天销量 */
    @Excel(name = "七天销量")
    private String ssq;

    /** 七天销量天数 */
    @Excel(name = "七天销量天数")
    private String ssqd;

    /** 三十天销量 */
    @Excel(name = "三十天销量")
    private String tsq;

    /** 三十天销量天数 */
    @Excel(name = "三十天销量天数")
    private String tsqd;

    /** 待采购量 */
    @Excel(name = "待采购量")
    private String tpq;

    /** 采购天数 */
    @Excel(name = "采购天数")
    private String pod;

    /** 采购交期 */
    @Excel(name = "采购交期")
    private String pd;

    /** 统计时间 */
    @Excel(name = "统计时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date stt;

    /** 库存刊登策略 */
    @Excel(name = "库存刊登策略")
    private String ips;

    /** 商品 */
    @Excel(name = "商品")
    private Long product;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String filepath;

    /** 长 */
    @Excel(name = "长")
    private Long length;

    /** 宽 */
    @Excel(name = "宽")
    private Long width;

    /** 高 */
    @Excel(name = "高")
    private Long height;

    /** SKU映射 */
    @Excel(name = "SKU映射")
    private String skumap;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSkucode(String skucode) 
    {
        this.skucode = skucode;
    }

    public String getSkucode() 
    {
        return skucode;
    }
    public void setSkuocode(String skuocode) 
    {
        this.skuocode = skuocode;
    }

    public String getSkuocode() 
    {
        return skuocode;
    }
    public void setSize(String size) 
    {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize()
    {
        return size;
    }

    public void setSuttle(String suttle) 
    {
        this.suttle = suttle;
    }

    public String getSuttle() 
    {
        return suttle;
    }
    public void setRw(String rw) 
    {
        this.rw = rw;
    }

    public String getRw() 
    {
        return rw;
    }
    public void setCostprice(String costprice) 
    {
        this.costprice = costprice;
    }

    public String getCostprice() 
    {
        return costprice;
    }
    public void setSellprice(String sellprice) 
    {
        this.sellprice = sellprice;
    }

    public String getSellprice() 
    {
        return sellprice;
    }
    public void setState(Integer state) 
    {
        this.state = state;
    }

    public Integer getState() 
    {
        return state;
    }
    public void setRank(String rank) 
    {
        this.rank = rank;
    }

    public String getRank() 
    {
        return rank;
    }
    public void setSsq(String ssq) 
    {
        this.ssq = ssq;
    }

    public String getSsq() 
    {
        return ssq;
    }
    public void setSsqd(String ssqd) 
    {
        this.ssqd = ssqd;
    }

    public String getSsqd() 
    {
        return ssqd;
    }
    public void setTsq(String tsq) 
    {
        this.tsq = tsq;
    }

    public String getTsq() 
    {
        return tsq;
    }
    public void setTsqd(String tsqd) 
    {
        this.tsqd = tsqd;
    }

    public String getTsqd() 
    {
        return tsqd;
    }
    public void setTpq(String tpq) 
    {
        this.tpq = tpq;
    }

    public String getTpq() 
    {
        return tpq;
    }
    public void setPod(String pod) 
    {
        this.pod = pod;
    }

    public String getPod() 
    {
        return pod;
    }
    public void setPd(String pd) 
    {
        this.pd = pd;
    }

    public String getPd() 
    {
        return pd;
    }
    public void setStt(Date stt) 
    {
        this.stt = stt;
    }

    public Date getStt() 
    {
        return stt;
    }
    public void setIps(String ips) 
    {
        this.ips = ips;
    }

    public String getIps() 
    {
        return ips;
    }
    public void setProduct(Long product)
    {
        this.product = product;
    }

    public Long getProduct()
    {
        return product;
    }
    public void setFilepath(String filepath) 
    {
        this.filepath = filepath;
    }

    public String getFilepath() 
    {
        return filepath;
    }
    public void setLength(Long length) 
    {
        this.length = length;
    }

    public Long getLength() 
    {
        return length;
    }
    public void setWidth(Long width) 
    {
        this.width = width;
    }


    public Long getWidth()
    {
        return width;
    }
    public void setHeight(Long height)
    {
        this.height = height;
    }

    public Long getHeight()
    {
        return height;
    }
    public void setSkumap(String skumap) 
    {
        this.skumap = skumap;
    }

    public String getSkumap() 
    {
        return skumap;
    }

    public Skuproduct( ) {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("skucode", getSkucode())
            .append("skuocode", getSkuocode())
            .append("size", getSize())
            .append("color", getColor())
            .append("suttle", getSuttle())
            .append("rw", getRw())
            .append("costprice", getCostprice())
            .append("sellprice", getSellprice())
            .append("state", getState())
            .append("rank", getRank())
            .append("ssq", getSsq())
            .append("ssqd", getSsqd())
            .append("tsq", getTsq())
            .append("tsqd", getTsqd())
            .append("tpq", getTpq())
            .append("pod", getPod())
            .append("pd", getPd())
            .append("stt", getStt())
            .append("ips", getIps())
            .append("product", getProduct())
            .append("filepath", getFilepath())
            .append("length", getLength())
            .append("width", getWidth())
            .append("height", getHeight())
            .append("skumap", getSkumap())
            .toString();
    }
}
