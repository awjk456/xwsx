package org.xwsx.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xwsx.bean.Menu;
import org.xwsx.dao.MenuDao;
import org.xwsx.dto.MenuDto;
import org.xwsx.service.MenuService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuDao menuDao;

    @Override
    public List<MenuDto> getList() {
        List<Menu> list = menuDao.getList();
        List<MenuDto> result = new ArrayList<MenuDto>();
        for(Menu menu:list){
            MenuDto menuDto = new MenuDto();
            BeanUtils.copyProperties(menu,menuDto);
            result.add(menuDto);
        }
        return result;
    }
}
