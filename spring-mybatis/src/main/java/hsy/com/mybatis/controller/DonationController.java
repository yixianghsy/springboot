package hsy.com.mybatis.controller;

import hsy.com.mybatis.entity.Donation;
import hsy.com.mybatis.entity.Response;
import hsy.com.mybatis.entity.User;
import hsy.com.mybatis.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DonationController {
    @Autowired
    private DonationService donationService;

    @GetMapping("getList")
    public List<Donation> getByUserNameAndPassword() {

        List<Donation> list = donationService.getList();
        return list;


    }
    @GetMapping("selectCount")
    public Integer selectCount() {

        Integer integer = donationService.selectCount();
        return integer;


    }
    @GetMapping("selectCaseDetial")
    public List<Donation> selectCaseDetial() {

        List<Donation> list = donationService.selectCaseDetial();
        return list;


    }


}
