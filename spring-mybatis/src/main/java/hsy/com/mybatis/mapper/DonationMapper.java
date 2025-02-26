package hsy.com.mybatis.mapper;

import hsy.com.mybatis.entity.Donation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface DonationMapper {

    List<Donation>  getList();

    Integer selectCount();

    List<Donation> selectCaseDetial();
}
