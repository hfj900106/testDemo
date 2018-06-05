package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.News;
import com.hzed.easyget.persistence.auto.entity.example.NewsExample;
import com.hzed.easyget.persistence.auto.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author wuchengwu
 * @date 2018/5/25
 */
@Repository
public class NewsRepository {

    @Autowired
    private NewsMapper newsMapper;

    public List<News> getBombList() {
        NewsExample example = new NewsExample();
        example.setOrderByClause(News.Column.upTime.desc());
        example.createCriteria().andIsUseEqualTo(true);
        return newsMapper.selectByExample(example);
    }
}