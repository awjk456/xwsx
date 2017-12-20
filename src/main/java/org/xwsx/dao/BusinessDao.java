package org.xwsx.dao;

import org.xwsx.bean.Business;

import java.util.List;

public interface BusinessDao {
    int insert(Business business);
    List<Business> findByPage(Business business);
    Business findById(int id);
    int deleteById(int id);
    List<Business> selectLikeByPage(Business business);
}

