package hsy.com.mybatiSplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import hsy.com.mybatiSplus.entity.User;
import hsy.com.mybatiSplus.mapper.UserMapper;
import hsy.com.mybatiSplus.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author crush
 * @since 2021-07-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
