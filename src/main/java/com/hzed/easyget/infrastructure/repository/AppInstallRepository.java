package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.AppInstall;
import com.hzed.easyget.persistence.auto.entity.example.AppInstallExample;
import com.hzed.easyget.persistence.auto.mapper.AppInstallMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 记录app安装量
 *
 * @author wuchengwu
 * @date 2018/9/7
 */
@Repository
public class AppInstallRepository {

    @Autowired
    private AppInstallMapper appInstallMapper;

    public void insert(AppInstall appInstall) {
        appInstallMapper.insertSelective(appInstall);
    }

    public AppInstall findOneByImei(String imei) {
        AppInstallExample example = new AppInstallExample();
        example.createCriteria().andImeiEqualTo(imei);
        return appInstallMapper.selectOneByExample(example);
    }
}