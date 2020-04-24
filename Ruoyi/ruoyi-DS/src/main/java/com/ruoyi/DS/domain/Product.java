package com.ruoyi.DS.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
@Data
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
