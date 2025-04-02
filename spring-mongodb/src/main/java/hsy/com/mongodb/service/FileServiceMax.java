package hsy.com.mongodb.service;

import hsy.com.mongodb.domain.FileDocument;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface FileServiceMax {


    /**
     * 保存文件
     * @param file
     * @return
     */
    FileDocument saveFile(FileDocument file);
    /**
     * 上传文件到Mongodb的GridFs中
     * @param in
     * @param contentType
     * @return
     */
    String uploadFileToGridFS(InputStream in , String contentType);
    /**
     * 删除文件
     * @param id
     */
   void removeFile(String id);
    /**
     * 根据id查看文件
     * @param id
     * @return
     */
    Optional<FileDocument> getFileById(String id);
    /**
     * 分页列出文件
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<FileDocument> listFilesByPage(int pageIndex, int pageSize);

}
