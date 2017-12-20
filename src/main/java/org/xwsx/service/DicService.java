package org.xwsx.service;

import org.xwsx.bean.Dic;

import java.util.List;

public interface DicService {
    List<Dic> getListByType(String parame);
}
