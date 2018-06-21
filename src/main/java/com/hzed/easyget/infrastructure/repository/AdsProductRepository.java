package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.AdsProduct;
import com.hzed.easyget.persistence.auto.entity.example.AdsProductExample;
import com.hzed.easyget.persistence.auto.mapper.AdsProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wuchengwu
 * @date 2018/6/15
 */
@Repository
public class AdsProductRepository {

    @Autowired
    private AdsProductMapper adsProductMapper;

    public List<AdsProduct> getAdsProductList() {
        AdsProductExample example = new AdsProductExample();
        example.setOrderByClause(AdsProduct.Column.weights.desc());
        example.createCriteria().andIsUseEqualTo(true);
        return adsProductMapper.selectByExample(example);
    }


}