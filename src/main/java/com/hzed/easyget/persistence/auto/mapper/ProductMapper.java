package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.Product;
import com.hzed.easyget.persistence.auto.entity.example.ProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
    long countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    List<Product> selectByExampleSelective(@Param("example") ProductExample example, @Param("selective") Product.Column ... selective);

    Product selectByPrimaryKey(Long id);

    Product selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Product.Column ... selective);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    Product selectOneByExample(ProductExample example);

    Product selectOneByExampleSelective(@Param("example") ProductExample example, @Param("selective") Product.Column ... selective);

    int batchInsert(@Param("list") List<Product> list);

    int batchInsertSelective(@Param("list") List<Product> list, @Param("selective") Product.Column ... selective);
}