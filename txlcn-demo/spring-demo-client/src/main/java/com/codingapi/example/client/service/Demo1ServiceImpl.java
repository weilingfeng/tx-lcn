package com.codingapi.example.client.service;

import com.codingapi.example.client.mapper.ClientDemoMapper;
import com.codingapi.example.common.db.domain.Demo;
import com.codingapi.txlcn.client.bean.DTXLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Description:
 * Date: 2018/12/25
 *
 * @author ujued
 */
@Service
public class Demo1ServiceImpl{

    @Autowired
    private ClientDemoMapper demoMapper;

    @Value("${spring.application.name}")
    private String appName;

    @Transactional
    public String execute(String value) {
        Demo demo = new Demo();
        demo.setDemoField(value);
        demo.setAppName(appName + "1");
        demo.setCreateTime(new Date());
        demo.setGroupId(DTXLocal.getOrNew().getGroupId());
        demo.setUnitId(DTXLocal.getOrNew().getUnitId());
        demoMapper.save(demo);
//        int i = 1/0;
        return "success";
    }


}
