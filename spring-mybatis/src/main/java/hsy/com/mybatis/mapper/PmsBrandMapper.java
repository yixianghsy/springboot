package hsy.com.mybatis.mapper;


import hsy.com.mybatis.entity.Donation;
import hsy.com.mybatis.entity.PmsBrand;
import hsy.com.mybatis.entity.PmsBrandExample;
import hsy.com.mybatis.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmsBrandMapper {

    List<PmsBrand> selectByExample(PmsBrandExample example);

    Integer selectCount();

    List<PmsBrand> selectCaseDetial(@Param("keyword")String keyword,@Param("start") Integer start, @Param("pageSize")Integer pageSize);
}