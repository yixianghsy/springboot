package hsy.com.mybatiSplus.controller;
import hsy.com.mybatiSplus.entity.User;
import hsy.com.mybatiSplus.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author crush
 * @since 2021-07-23
 */
@RestController
public class UserController {

    private final IUserService tbUserService;

    public UserController(IUserService tbUserService) {
        this.tbUserService = tbUserService;
    }

    @RequestMapping("/list")
    public List<User> list(){
       return tbUserService.list();
    }

}

