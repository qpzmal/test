package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.fcf_vu.SysFuction;

import java.util.List;

public interface SysFuctionMapper extends ISqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysFuction record);

    int insertSelective(SysFuction record);

    SysFuction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysFuction record);

    int updateByPrimaryKey(SysFuction record);

    /**
     * 查询用户id对应的function
     * @param userid
     * @return
     */
    List<SysFuction> queryFunctionByUserId(String userid);
}