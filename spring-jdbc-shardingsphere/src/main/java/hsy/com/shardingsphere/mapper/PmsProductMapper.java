package hsy.com.shardingsphere.mapper;

import hsy.com.shardingsphere.domain.PmsProduct;
import hsy.com.shardingsphere.domain.PmsProductExample;

import java.util.List;

public interface PmsProductMapper {


    List<PmsProduct> selectByExample(PmsProductExample example);



}