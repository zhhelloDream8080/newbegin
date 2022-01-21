package com.hao.newbegin.test.dao;

import com.hao.newbegin.test.entity.PsnNoDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PsnNoDao {
    //此处需要注意查出来的是一条还是多条，如果是多条，要用list接收，不然会报错
    PsnNoDTO selectById(String id);
}
