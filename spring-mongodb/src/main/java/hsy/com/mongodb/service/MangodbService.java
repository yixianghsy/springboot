package hsy.com.mongodb.service;

import com.alibaba.fastjson.JSON;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import hsy.com.mongodb.domain.UmsMemberLevel;
import hsy.com.mongodb.mapper.UmsMemberLevelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class MangodbService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private UmsMemberLevelMapper memberLevelMapper;
    //新增
    public void insertOrder(String id){
        UmsMemberLevel umsMemberLevel = memberLevelMapper.selectByPrimaryKey(Long.valueOf(id));
        if(null!=umsMemberLevel){
            mongoTemplate.insert(umsMemberLevel,"order"); //调用的是insert(T objectToSave)该方法,因为T是实体类,通过注解就知道查到哪个collection了,mango不像mysql一样需要先创建表,它不用,第一条插入的数据,发现没有对应的collection就会创建
        }
    }
    //批量插入
    public void insertAll(){
        List<UmsMemberLevel> umsMemberLevel = memberLevelMapper.selectByExample();
        Collection<UmsMemberLevel> mongoOrders = mongoTemplate.insert(umsMemberLevel,"order");
        return ;
    }
    //删除单条
//    public void deleteByOrderId(String id){
//        UmsMemberLevel umsMemberLevel = memberLevelMapper.selectByPrimaryKey(Long.valueOf(id));
//        mongoTemplate.remove(umsMemberLevel,"order");
//    }
    public void deleteByOrderId(String id){
        Criteria c = Criteria.where("id").is(id); //相当于mysql的where orderId="ddd"
        Query query = new Query(c);
        DeleteResult ret = mongoTemplate.remove(query,UmsMemberLevel.class,"order");
    }
    //更新订单
    public void updateByOrderId(String orderId){
        //使用updateFirst(Query query, Update update, Class<?> entityClass)该方法,updateFirst和update方法的区别是:updateFirst只更改查询到的第一条,update是更改所有满足条件的
        //new Update().set("orderTitle", "iphone").set("a","b") 可以使用链式表达式
        UpdateResult updateResult = mongoTemplate.updateFirst(new Query(Criteria.where("id").is(orderId)), new Update().set("orderTitle", "iphone"), UmsMemberLevel.class,"order");

    }
    //查找
    public UmsMemberLevel selectOrderId(String orderId){
        //findOne(Query query, Class<T> entityClass)
        UmsMemberLevel mongoOrder = mongoTemplate.findOne(new Query(Criteria.where("id").is(orderId)), UmsMemberLevel.class,"order");
        return mongoOrder;
    }
    //高级查询: 排序 分页
    public List<UmsMemberLevel> getList() {
        Query query = new Query();
        Criteria criteria = new Criteria();
        //下面条件相当于select * from order where orderStatus in(16,32) and payStatus=4 and finishTime<=2019-07-05 00:00:00 and (customerId=139 or customerId=1360)
        criteria.and("orderStatus").in(16, 32).andOperator(Criteria.where("payStatus").is(4)).and("finishTime").lte("2019-07-05 00:00:00")
                .orOperator(Criteria.where("customerId").is("139"), Criteria.where("customerId").is("1360"));
        //1360
        query.addCriteria(criteria);
        System.out.println(JSON.toJSONString(criteria));
        long count = mongoTemplate.count(query, UmsMemberLevel.class,"order"); //计算总数,用于算法分页数
        System.out.println(count);
        int pageNum = 1;
        int pageSize = 20;
        int pageTotal = (int) (count % pageSize == 0 ? count / pageSize : count / pageSize + 1); //总页数
        System.out.println(pageTotal);
        int offset = (pageNum - 1) * pageSize;
        query.with(Sort.by(Sort.Order.desc("deliveryTime"))); //排序逻辑
        query.skip(offset).limit(pageSize); // 分页逻辑
        List<UmsMemberLevel> shopMongoOrders = mongoTemplate.find(query, UmsMemberLevel.class,"order");
//　　　　　List<String> customerId = mongoTemplate.findDistinct(query, "customerId", ShopMongoOrder.class, String.class); //查询用户编号,去重
        return shopMongoOrders;
    }
    public void aggress(){
        //java的bigDeciaml类型在mongodb中会变成字符串,所以对bigDeciaml进行sum操作,永远都是返回0
        //match是条件:match在group后面就相当于where 语句,在后面相当于having语句,sort要在group后面才有意义
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("orderStatus").count().as("total")
                        .sum("orderPayAmount").as("payAmount")
                        .first("orderStatus").as("orderStatus")

        ).withOptions(Aggregation.newAggregationOptions().allowDiskUse(true).build());
        //如果不加first的时候,取不到orderStatus的值,所以加了后,分组的值才会有数据
        //Aggregation.newAggregationOptions().allowDiskUse(true)是用来解除mongodb 查询数据默认占用最大内存的(默认100M).不然会抛出异常:
        AggregationResults<UmsMemberLevel> order = mongoTemplate.aggregate(aggregation, "order", UmsMemberLevel.class);
        List<UmsMemberLevel> mappedResults = order.getMappedResults();
        System.out.println(mappedResults);
        System.out.println(order);


    }
}