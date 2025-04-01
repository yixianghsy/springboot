package hsy.com.mongodb.service;

import hsy.com.mongodb.vo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 事务服务测试！
 */
@Service
public class TransactionExample {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Transactional(rollbackFor = Exception.class)
    public Object transactionTest(){
        Person person =new Person();
        person.setId(1l);
        person.setUserName("张三");
        person.setPassWord("123456");
        person.setCreateTime(new Date());
        Person newPerson = mongoTemplate.insert(person);
        // 测试抛出异常，观察数据是否进行回滚
        if(1 == 1){
            throw new RuntimeException("异常");
        }
        return newPerson;
    }
}