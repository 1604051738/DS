package com.ruoyi.DS.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 收货人信息对象 shipping_address
 * 
 * @author ruoyi
 * @date 2020-05-06
 */
public class ShippingAddress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 收货人 */
    @Excel(name = "收货人")
    private String name;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String phoneNumber;

    /** 国家 */
    @Excel(name = "国家")
    private String country;

    /** 邮编 */
    @Excel(name = "邮编")
    private String zipcode;

    /** 省份 */
    @Excel(name = "省份")
    private String state;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 街道地址1 */
    @Excel(name = "街道地址1")
    private String streetAddress1;

    /** 街道地址2 */
    @Excel(name = "街道地址2")
    private String streetAddress2;

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
    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }
    public void setCountry(String country) 
    {
        this.country = country;
    }

    public String getCountry() 
    {
        return country;
    }
    public void setZipcode(String zipcode) 
    {
        this.zipcode = zipcode;
    }

    public String getZipcode() 
    {
        return zipcode;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setStreetAddress1(String streetAddress1) 
    {
        this.streetAddress1 = streetAddress1;
    }

    public String getStreetAddress1() 
    {
        return streetAddress1;
    }
    public void setStreetAddress2(String streetAddress2) 
    {
        this.streetAddress2 = streetAddress2;
    }

    public String getStreetAddress2() 
    {
        return streetAddress2;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("phoneNumber", getPhoneNumber())
            .append("country", getCountry())
            .append("zipcode", getZipcode())
            .append("state", getState())
            .append("city", getCity())
            .append("streetAddress1", getStreetAddress1())
            .append("streetAddress2", getStreetAddress2())
            .toString();
    }
}
