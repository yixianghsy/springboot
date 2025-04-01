package hsy.com.mongodb.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 使用@Document注解指定集合名称
 */
@Getter
@Setter
@Document(collection="persons")
public class Person implements Serializable {
    private static final long serialVersionUID = -3258839839160856613L;

    /**
     * 使用@Id注解指定MongoDB中的 _id 主键
     */
    @Id
    private Long id;

    private String userName;

    private String passWord;

    private Integer age;
    /**
     * 创建一个5秒之后文档自动删除的索引
     */
    @Indexed(expireAfterSeconds=5)
    private Date createTime;

    //...get/set

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", age=" + age +
                ", createTime=" + createTime +
                '}';
    }
}