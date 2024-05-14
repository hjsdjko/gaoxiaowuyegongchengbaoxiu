package com.dao;

import com.entity.GongchengshiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.GongchengshiView;

/**
 * 工程师 Dao 接口
 *
 * @author 
 */
public interface GongchengshiDao extends BaseMapper<GongchengshiEntity> {

   List<GongchengshiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
