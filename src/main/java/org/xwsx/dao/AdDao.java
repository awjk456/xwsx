package org.xwsx.dao;


import org.xwsx.bean.Ad;

import java.util.List;

public interface AdDao {
    int insert(Ad ad);
    List<Ad> selectByPage(Ad ad);
    int deleteOne(int id);
    Ad findById(int id);
    int update(Ad ad);
}
