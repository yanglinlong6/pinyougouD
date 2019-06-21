package com.pinyougou.sellergoods.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.core.service.CoreServiceImpl;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.Specification;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.sellergoods.service.SpecificationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;




/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl extends CoreServiceImpl<TbSpecification> implements SpecificationService {

    @Autowired
    private TbSpecificationOptionMapper optionMapper;
	
	private TbSpecificationMapper specificationMapper;

	@Autowired
	public SpecificationServiceImpl(TbSpecificationMapper specificationMapper) {
		super(specificationMapper, TbSpecification.class);
		this.specificationMapper=specificationMapper;
	}

	
	

	
	@Override
    public PageInfo<TbSpecification> findPage(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<TbSpecification> all = specificationMapper.selectAll();
        PageInfo<TbSpecification> info = new PageInfo<TbSpecification>(all);

        //序列化再反序列化
        String s = JSON.toJSONString(info);
        PageInfo<TbSpecification> pageInfo = JSON.parseObject(s, PageInfo.class);
        return pageInfo;
    }

	
	

	 @Override
    public PageInfo<TbSpecification> findPage(Integer pageNo, Integer pageSize, TbSpecification specification) {
        PageHelper.startPage(pageNo,pageSize);

        Example example = new Example(TbSpecification.class);
        Example.Criteria criteria = example.createCriteria();

        if(specification!=null){			
						if(StringUtils.isNotBlank(specification.getSpecName())){
				criteria.andLike("specName","%"+specification.getSpecName()+"%");
				//criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}
	
		}
        List<TbSpecification> all = specificationMapper.selectByExample(example);
        PageInfo<TbSpecification> info = new PageInfo<TbSpecification>(all);
        //序列化再反序列化
        String s = JSON.toJSONString(info);
        PageInfo<TbSpecification> pageInfo = JSON.parseObject(s, PageInfo.class);

        return pageInfo;
    }

    @Override
    public void add(Specification specification) {
        TbSpecification specification1 = specification.getSpecification();

        specificationMapper.insert(specification1);
        List<TbSpecificationOption> optionList = specification.getOptionList();

        for (TbSpecificationOption tbSpecificationOption : optionList) {
            tbSpecificationOption.setSpecId(specification1.getId());
            optionMapper.insert(tbSpecificationOption);
        }
    }

    @Override
    public Specification findOne(Long id) {
        Specification specification = new Specification();
        TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);

        TbSpecificationOption option = new TbSpecificationOption();
        option.setSpecId(tbSpecification.getId());
        List<TbSpecificationOption> options = optionMapper.select(option);

        specification.setSpecification(tbSpecification);
        specification.setOptionList(options);
        return specification;
    }

    @Override
    public void update(Specification specification) {
        specificationMapper.updateByPrimaryKey(specification.getSpecification());
        TbSpecificationOption option= new TbSpecificationOption();
        option.setSpecId(specification.getSpecification().getId());
        int delete = optionMapper.delete(option);

        List<TbSpecificationOption> optionList = specification.getOptionList();
        for (TbSpecificationOption tbSpecificationOption : optionList) {
            tbSpecificationOption.setSpecId(specification.getSpecification().getId());
            optionMapper.insert(tbSpecificationOption);
        }
        //批量插入 要求 主键为ID 并且是自增才可以
        //optionMapper.insertList(optionList);

    }

    @Override
    public void delete(Long[] ids) {
        //删除规格
        Example example = new Example(TbSpecification.class);
        example.createCriteria().andIn("id", Arrays.asList(ids));
        specificationMapper.deleteByExample(example);

        //删除规格关联的规格的选项
        Example exampleOption = new Example(TbSpecificationOption.class);
        exampleOption.createCriteria().andIn("specId", Arrays.asList(ids));
        optionMapper.deleteByExample(exampleOption);
    }

}
