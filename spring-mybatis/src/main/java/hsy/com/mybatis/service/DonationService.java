package hsy.com.mybatis.service;

import hsy.com.mybatis.entity.Donation;
import hsy.com.mybatis.mapper.DonationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DonationService {
    @Autowired
    private DonationMapper donationMapper;


    public List<Donation> getList(){
        return donationMapper.getList();
    }

    public Integer selectCount(){
        Integer integer = donationMapper.selectCount();
        return integer;
    }

    public  List<Donation> selectCaseDetial(){
        List<Donation> donations = donationMapper.selectCaseDetial();
        return donations;
    }
}
