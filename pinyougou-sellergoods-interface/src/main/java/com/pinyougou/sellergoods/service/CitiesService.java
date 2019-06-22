package com.pinyougou.sellergoods.service;
import java.util.List;
import com.pinyougou.pojo.TbCities;

import com.github.pagehelper.PageInfo;
import com.pinyougou.core.service.CoreService;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface CitiesService extends CoreService<TbCities> {
	
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	 PageInfo<TbCities> findPage(Integer pageNo, Integer pageSize);
	
	

	/**
	 * 分页
	 * @param pageNo 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	PageInfo<TbCities> findPage(Integer pageNo, Integer pageSize, TbCities Cities);
	
}
