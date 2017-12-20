package org.xwsx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xwsx.bean.Dic;
import org.xwsx.dao.DicDao;
import org.xwsx.service.DicService;

import java.util.List;

@Service
public class DicServiceImpl implements DicService {

    @Autowired
    DicDao dicDao;

    @Override
    public List<Dic> getListByType(String parame) {
        System.out.println("========+>"+parame);
        List<Dic> dicList = dicDao.findByParamte(parame);
        //System.out.println("========+>"+parame);
        return dicList;
    }
}
