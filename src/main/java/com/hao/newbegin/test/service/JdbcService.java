package com.hao.newbegin.test.service;

import java.util.List;
import java.util.Map;

public interface JdbcService {
    public List<Map<String, Object>> queryData(String sql);
}
