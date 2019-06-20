package com.pinyougou.sellergoods.service;

import com.github.pagehelper.PageInfo;
import com.pinyougou.core.service.CoreService;
import com.pinyougou.pojo.TbBrand;

import java.util.List;

public interface BrandService {
    /**
     * 查询所有的品牌列表
     * @return
     */
    List<TbBrand> findAll();

    PageInfo<TbBrand> findPage(Integer pageNo, Integer pageSize);

    public void add(TbBrand brand);

    public void update(TbBrand brand);

    public TbBrand findOne(Long id);

    public void delete(Long [] ids);

    /**
     * 分页
     *
     * @param pageNo   当前页 码
     * @param pageSize 每页记录数
     * @return
     */
    PageInfo<TbBrand> findPage(Integer pageNo, Integer pageSize, TbBrand brand);
}
