package com.hao.newbegin.test.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hao.newbegin.test.entity.PsnNoDTO;
import com.hao.newbegin.test.service.JdbcService;
import com.hao.newbegin.test.service.PsnNoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PsnNoController {
    @Autowired
    private PsnNoService psnNoService;

    @Autowired
    private JdbcService jdbcService;


    //测试controller
    @PostMapping("/testPsnNo")
    public String testPsnController(PsnNoDTO psnNoDTO){
        PsnNoDTO psnNoDTO1 = new PsnNoDTO();
        psnNoDTO1.setPersonId("12321312");
        psnNoDTO1.setPsnNo("660000000123123");
        psnNoDTO1.setPsnName("张浩");
        psnNoDTO1.setCertNo("12312321312");
        psnNoDTO1.setSex("1");
        psnNoDTO1.setAddr("甘肃省天水市");
        Map<String, Object> map = new HashMap<>();
        map.put("data", psnNoDTO1);
        return JSON.toJSONString(map);
    }

    @GetMapping("/testPsnNo")
    public String testController(String psnNo){
        PsnNoDTO psnNoDTO1 = new PsnNoDTO();
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isNotEmpty(psnNo)){
            psnNoDTO1.setPersonId("12321312");
            psnNoDTO1.setPsnNo("660000000123123");
            psnNoDTO1.setPsnName("张浩");
            psnNoDTO1.setCertNo("12312321312");
            psnNoDTO1.setSex("1");
            psnNoDTO1.setAddr("甘肃省天水市");
            map.put("data", psnNoDTO1);
        }
        return JSON.toJSONString(map);
    }

    //jdbcTemplate的使用
    @GetMapping("/jdbcTemplateTest")
    public void testJdbctemplate(String psnName){
        String sql = "SELECT * FROM tab_person WHERE PSN_NAME = '"+psnName+"'";
        List<Map<String, Object>> queryData = jdbcService.queryData(sql);
        System.out.println(queryData);
    }
}
