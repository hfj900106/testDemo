package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.News;
import com.hzed.easyget.persistence.auto.entity.example.NewsExample;
import com.hzed.easyget.persistence.auto.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 公告
 *
 * @author wuchengwu
 * @since 2018/7/31
 */
@Repository
public class NewsRepository {

    @Autowired
    private NewsMapper newsMapper;

    public News findOne(String i18n) {
        NewsExample example = new NewsExample();
        example.createCriteria().andLanguageEqualTo(i18n).andIsReleaseEqualTo(true);
        example.setOrderByClause(News.Column.createTime.desc());
        return newsMapper.selectOneByExample(example);
    }

    public List<News> findList(String i18n) {
        NewsExample example = new NewsExample();
        example.createCriteria().andLanguageEqualTo(i18n).andIsReleaseEqualTo(true);
        example.setOrderByClause(News.Column.createTime.desc());
        return newsMapper.selectByExample(example);
    }

    public News findOneByIdAndLanguage(Long id) {
        NewsExample example = new NewsExample();
        example.createCriteria().andIdEqualTo(id);
        return newsMapper.selectOneByExampleWithBLOBs(example);
    }
}