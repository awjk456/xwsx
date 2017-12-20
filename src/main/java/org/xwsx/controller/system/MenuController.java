package org.xwsx.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xwsx.dto.MenuDto;
import org.xwsx.service.MenuService;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @RequestMapping(method = RequestMethod.GET)
    public List<MenuDto>  init(){

        return menuService.getList();
    }
}
