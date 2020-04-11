package com.ruoyi.DS.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 供应商对象 supplier
 * 
 * @author ruoyi
 * @date 2019-12-26
 */
public class Supplier extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String name;

    /** 供应商编码 */
    @Excel(name = "供应商编码")
    private String sc;

    /** 联系人 */
    @Excel(name = "联系人")
    private String lp;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String phonenumber;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 状态 */
    @Excel(name = "状态")
    private Long state;


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
    public void setSc(String sc) 
    {
        this.sc = sc;
    }

    public String getSc() 
    {
        return sc;
    }
    public void setLp(String lp) 
    {
        this.lp = lp;
    }

    public String getLp() 
    {
        return lp;
    }
    public void setPhonenumber(String phonenumber) 
    {
        this.phonenumber = phonenumber;
    }

    public String getPhonenumber() 
    {
        return phonenumber;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setState(Long state) 
    {
        this.state = state;
    }

    public Long getState() 
    {
        return state;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("sc", getSc())
            .append("lp", getLp())
            .append("phonenumber", getPhonenumber())
            .append("email", getEmail())
            .append("address", getAddress())
            .append("remark", getRemark())
            .append("state", getState())
            .append("createby", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateby", getUpdateBy())
            .append("updatetime", getUpdateTime())
            .toString();
    }

    public Supplier(Long id){
        this.id = id;
    }
    public Supplier(){}
}
