package com.pinyougou.sellergoods.service;

import com.github.pagehelper.PageInfo;
import com.pinyougou.core.service.CoreService;
import com.pinyougou.pojo.TbBrand;

public interface BrandService extends CoreService<TbBrand>{
    PageInfo<TbBrand> findPage(Integer pageNo, Integer pageSize);

    PageInfo<TbBrand> findPage(Integer pageNo, Integer pageSize, TbBrand brand);
}
