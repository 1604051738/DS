package com.ruoyi.DS.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 仓库对象 warehouse
 * 
 * @author ruoyi
 * @date 2019-12-25
 */
public class Warehouse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 中文名称 */
    @Excel(name = "中文名称")
    private String chinesename;

    /** 英文名称 */
    @Excel(name = "英文名称")
    private String englishname;

    /** 编码 */
    @Excel(name = "编码")
    private String coding;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
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
    public void setCoding(String coding) 
    {
        this.coding = coding;
    }

    public String getCoding() 
    {
        return coding;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("chinesename", getChinesename())
            .append("englishname", getEnglishname())
            .append("coding", getCoding())
            .append("createby", getCreateBy())
            .append("updateby", getUpdateBy())
            .append("createtime", getCreateTime())
            .append("updatetime", getUpdateTime())
            .toString();
    }

    public Warehouse(Long id){
        this.id = id;
    }
    public Warehouse(){}

}
