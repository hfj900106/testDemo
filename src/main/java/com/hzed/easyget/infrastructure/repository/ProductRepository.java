package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.Product;
import com.hzed.easyget.persistence.auto.entity.example.ProductExample;
import com.hzed.easyget.persistence.auto.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author wuchengwu
 * @date 2018/5/23
 */
@Repository
public class ProductRepository {

    @Autowired
    private ProductMapper productMapper;

    public Product getProductInfo() {
        ProductExample example = new ProductExample();
        example.createCriteria().andIsUseEqualTo(true);
        return productMapper.selectOneByExample(example);
    }
}