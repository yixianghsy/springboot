package hsy.com.mongodb.test;

import com.mongodb.client.result.DeleteResult;
import hsy.com.mongodb.service.MangodbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MangodbServiceTest {
    @Autowired
    private MangodbService mangodbService;
    @Test
    public void insertOrder() {
        mangodbService.insertOrder(String.valueOf(1));
    }
    @Test
    public void insertAll(){
        mangodbService.insertAll();
    }
    @Test
    public void deleteByOrderId(){
        mangodbService.deleteByOrderId((String.valueOf(4)));
    }

}