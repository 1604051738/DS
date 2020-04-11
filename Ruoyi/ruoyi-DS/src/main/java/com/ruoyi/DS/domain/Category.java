package com.ruoyi.DS.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 目录分类对象 category
 * 
 * @author ruoyi
 * @date 2019-12-26
 */
public class Category extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 中文名称 */
    @Excel(name = "中文名称")
    private String chinesename;

    /** 描述 */
    @Excel(name = "描述")
    private String remark;

    /** 状态 */
    @Excel(name = "状态")
    private Long state;

    /** 排序值 */
    @Excel(name = "排序值")
    private String ordervalue;

    /** 父目录 */
    @Excel(name = "父目录")
    private String parentdirectory;

    /** 排序路径 */
    @Excel(name = "排序路径")
    private String sp;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setChinesename(String chinesename) 
    {
        this.chinesename = chinesename;
    }

    public String getChinesename() 
    {
        return chinesename;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getRemark()
    {
        return remark;
    }
    public void setState(Long state) 
    {
        this.state = state;
    }

    public Long getState() 
    {
        return state;
    }
    public void setOrdervalue(String ordervalue) 
    {
        this.ordervalue = ordervalue;
    }

    public String getOrdervalue() 
    {
        return ordervalue;
    }
    public void setParentdirectory(String parentdirectory) 
    {
        this.parentdirectory = parentdirectory;
    }

    public String getParentdirectory() 
    {
        return parentdirectory;
    }
    public void setSp(String sp) 
    {
        this.sp = sp;
    }

    public String getSp() 
    {
        return sp;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("chinesename", getChinesename())
            .append("remark", getRemark())
            .append("state", getState())
            .append("ordervalue", getOrdervalue())
            .append("parentdirectory", getParentdirectory())
            .append("createby", getCreateBy())
            .append("updateby", getUpdateBy())
            .append("createtime", getCreateTime())
            .append("updatetime", getUpdateTime())
            .append("sp", getSp())
            .toString();
    }
    public Category(Long id){
        this.id = id;
    }
    public Category(){}
}
