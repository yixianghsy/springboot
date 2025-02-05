package hsy.com.mybatis.service;

import hsy.com.mybatis.entity.User;
import hsy.com.mybatis.mapper.UserMapper;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {
  @Autowired
  UserMapper userMapper;

  public int insertUser(User user) {
    return userMapper.insertUser(user);
  }

  public User getByUserNameAndPassword(User user) {
    return userMapper.getByUserNameAndPassword(user);
  }
}
