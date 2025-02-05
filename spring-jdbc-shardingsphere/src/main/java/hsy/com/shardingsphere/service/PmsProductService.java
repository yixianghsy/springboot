package hsy.com.shardingsphere.service;



import hsy.com.shardingsphere.domain.PmsProduct;
import hsy.com.shardingsphere.domain.PmsProductQueryParam;

import java.util.List;

public interface PmsProductService {

    List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum);
}
