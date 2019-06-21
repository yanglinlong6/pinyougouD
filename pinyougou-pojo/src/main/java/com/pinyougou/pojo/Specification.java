package com.pinyougou.pojo;


import java.io.Serializable;
import java.util.List;

/**
 * 项目名:pinyougou-parent
 * 包名: entity
 * 作者: Yanglinlong
 * 日期: 2019/6/20 19:30
 */
public class Specification implements Serializable {
    private TbSpecification specification;//一个规格
    private List<TbSpecificationOption> optionList;//多个规格选项

    public TbSpecification getSpecification() {
        return specification;
    }

    public void setSpecification(TbSpecification specification) {
        this.specification = specification;
    }

    public List<TbSpecificationOption> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<TbSpecificationOption> optionList) {
        this.optionList = optionList;
    }
}
