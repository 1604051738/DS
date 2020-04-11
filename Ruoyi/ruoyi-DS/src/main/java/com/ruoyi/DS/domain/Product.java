package com.ruoyi.DS.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品对象 product
 * 
 * @author ruoyi
 * @date 2019-12-26
 */
public class Product extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 目录分类 */
    @Excel(name = "目录分类")
    private Long category;

    /** 供应商 */
    @Excel(name = "供应商")
    private Long supplier;

    /** 排序值 */
    @Excel(name = "排序值")
    private String ordervalue;

    /** 中文名称 */
    @Excel(name = "中文名称")
    private String chinesename;

    /** 英文名称 */
    @Excel(name = "英文名称")
    private String englishname;

    /** 报关名称中文 */
    @Excel(name = "报关名称中文")
    private String nocdc;

    /** 报关名称英文 */
    @Excel(name = "报关名称英文")
    private String nocde;

    /** 编码 */
    @Excel(name = "编码")
    private Long code;

    /** 外部编码 */
    @Excel(name = "外部编码")
    private String ocode;

    /** 关键字 */
    @Excel(name = "关键字")
    private String keyword;

    /** 详细描述 */
    @Excel(name = "详细描述")
    private String dd;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String filepath;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCategory(Long category)
    {
        this.category = category;
    }

    public Long getCategory()
    {
        return category;
    }
    public void setSupplier(Long supplier)
    {
        this.supplier = supplier;
    }

    public Long getSupplier()
    {
        return supplier;
    }
    public void setOrdervalue(String ordervalue)
    {
        this.ordervalue = ordervalue;
    }

    public String getOrdervalue()
    {
        return ordervalue;
    }
    public void setChinesename(String chinesename) 
    {
        this.chinesename = chinesename;
    }

    public String getChinesename() 
    {
        return chinesename;
    }
    public void setEnglishname(String englishname) 
    {
        this.englishname = englishname;
    }

    public String getEnglishname() 
    {
        return englishname;
    }
    public void setNocdc(String nocdc) 
    {
        this.nocdc = nocdc;
    }

    public String getNocdc() 
    {
        return nocdc;
    }
    public void setNocde(String nocde) 
    {
        this.nocde = nocde;
    }

    public String getNocde() 
    {
        return nocde;
    }
    public void setCode(Long code)
    {
        this.code = code;
    }

    public Long getCode()
    {
        return code;
    }
    public void setOcode(String ocode) 
    {
        this.ocode = ocode;
    }

    public String getOcode() 
    {
        return ocode;
    }
    public void setKeyword(String keyword) 
    {
        this.keyword = keyword;
    }

    public String getKeyword() 
    {
        return keyword;
    }
    public void setDd(String dd) 
    {
        this.dd = dd;
    }

    public String getDd() 
    {
        return dd;
    }
    public void setFilepath(String filepath)
    {
        this.filepath = filepath;
    }

    public String getFilepath()
    {
        return filepath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("category", getCategory())
            .append("supplier", getSupplier())
            .append("ordervalue", getOrdervalue())
            .append("chinesename", getChinesename())
            .append("englishname", getEnglishname())
            .append("nocdc", getNocdc())
            .append("nocde", getNocde())
            .append("code", getCode())
            .append("ocode", getOcode())
            .append("keyword", getKeyword())
            .append("dd", getDd())
            .append("remark", getRemark())
            .append("filepath", getFilepath())
            .append("createtime", getCreateTime())
            .append("updatetime", getUpdateTime())
            .append("createby", getCreateBy())
            .append("updateby", getUpdateBy())
            .toString();
    }
}
