package hsy.com.mongodb.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @Author 三分恶
 * @Date 2020/1/11
 * @Description
 */
@Getter
@Setter
@Document
public class FileDocument {
    @Id  // 主键
    private String id;
    private String name;        // 文件名称
    private long size;          // 文件大小
    private Date uploadDate;    // 上传时间
    private String md5;         // 文件MD5值
    private byte[] content;     // 文件内容
    private String contentType; // 文件类型
    private String suffix;      // 文件后缀名
    private String description; // 文件描述
    private String gridfsId;    // 大文件管理GridFS的ID

    /**
     * 省略getter、setter、equales、hashCode、toString方法
     */
}