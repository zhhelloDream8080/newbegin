package com.hao.newbegin.test.service.impl;

import com.hao.newbegin.test.dao.PsnNoDao;
import com.hao.newbegin.test.entity.PsnNoDTO;
import com.hao.newbegin.test.service.PsnNoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PsnNoServiceImpl")
public class PsnNoServiceImpl implements PsnNoService {
    @Autowired
    private PsnNoDao psnNoDao;
    @Override
    public PsnNoDTO queryDTOById(String psnNo) {
        return psnNoDao.selectById(psnNo);
    }
}
