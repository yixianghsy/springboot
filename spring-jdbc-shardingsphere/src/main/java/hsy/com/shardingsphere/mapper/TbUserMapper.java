package hsy.com.shardingsphere.mapper;


import hsy.com.shardingsphere.user.TbUser;
import hsy.com.shardingsphere.user.TbUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TbUserMapper {
    int countByExample(TbUserExample example);

    int deleteByExample(TbUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbUser record);

    int insertSelective(TbUser record);

    List<TbUser> selectByExample(TbUserExample example);

    TbUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbUser record, @Param("example") TbUserExample example);

    int updateByExample(@Param("record") TbUser record, @Param("example") TbUserExample example);

    int updateByPrimaryKeySelective(TbUser record);

    int updateByPrimaryKey(TbUser record);

    List<TbUser> selectByKey(Map<String, Object> paramMap);
    List<TbUser> selectUserByNameOrPwd(Map<String, Object> paramMap);
}