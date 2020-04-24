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
 * 供应商对象 supplier
 * 
 * @author ruoyi
 * @date 2019-12-26
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
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

}
