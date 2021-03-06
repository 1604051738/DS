package com.ruoyi.DS.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 目录分类对象 category
 * 
 * @author ruoyi
 * @date 2020-05-08
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category extends TreeEntity
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

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 排序值 */
    @Excel(name = "排序值")
    private String ordervalue;

    /** 父目录 */
    @Excel(name = "父目录")
    private Long parentdirectory;

    /** 排序路径 */
    @Excel(name = "排序路径")
    private String sp;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("chinesename", getChinesename())
            .append("remark", getRemark())
            .append("state", getStatus())
            .append("ordervalue", getOrdervalue())
            .append("parentdirectory", getParentdirectory())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("sp", getSp())
            .toString();
    }
}
