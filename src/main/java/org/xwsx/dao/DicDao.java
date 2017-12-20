package org.xwsx.dao;

import org.xwsx.bean.Dic;

import java.util.List;

public interface DicDao {
    List<Dic> findByParamte(String parame);
}
