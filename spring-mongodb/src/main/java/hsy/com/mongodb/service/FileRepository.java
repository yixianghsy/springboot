package hsy.com.mongodb.service;

import hsy.com.mongodb.model.FileModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepository extends MongoRepository<FileModel,String> {

}
