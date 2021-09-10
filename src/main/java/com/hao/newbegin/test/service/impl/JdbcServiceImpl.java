package com.hao.newbegin.test.service.impl;

import com.hao.newbegin.test.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("JdbcServiceImpl")
public class JdbcServiceImpl implements JdbcService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> queryData(String sql) {
        //报错记录：
        //1，这里如果用queryForMap会提示“Incorrect result size: expected 1, actual 0] with root cause”
        //2，用queryForList替换，因为ForMap和ForObject都要求必须有值
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }
}
