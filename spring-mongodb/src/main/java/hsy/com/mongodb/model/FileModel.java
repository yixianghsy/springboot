package hsy.com.mongodb.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;

/**
 * @Author 三分恶
 * @Date 2020/1/11
 * @Description 文档类
 */
@Getter
@Setter
@Document
public class FileModel {

    @Id  // 主键
    private String id;
    private String name; // 文件名称
    private String contentType; // 文件类型
    private long size;
    private Date uploadDate;
    private String md5;
    private Binary content; // 文件内容
    private String path; // 文件路径

    /**
     *省略getter/setter
     */
    protected FileModel() {
    }


    public FileModel(String name, String contentType, long size,  Binary content) {
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.content = content;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileModel)) return false;
        FileModel fileModel = (FileModel) o;
        return size == fileModel.size &&
                Objects.equals(id, fileModel.id) &&
                Objects.equals(name, fileModel.name) &&
                Objects.equals(contentType, fileModel.contentType) &&
                Objects.equals(uploadDate, fileModel.uploadDate) &&
                Objects.equals(md5, fileModel.md5) &&
                Objects.equals(content, fileModel.content) &&
                Objects.equals(path, fileModel.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contentType, size, uploadDate, md5, content, path);
    }
}