package com.harvest.basic.repository.mapper;

import com.harvest.basic.repository.entity.BizWebConfigEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 提供给前端的业务自由配置表 Mapper 接口
 * </p>
 *
 * @author Alodi
 * @since 2023-01-08 23:11:04
 */
@Mapper
public interface BizWebConfigMapper extends BaseMapper<BizWebConfigEntity> {

}
