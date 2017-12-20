package org.xwsx.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xwsx.constant.PageCodeEnum;
import org.xwsx.dto.PageCodeDto;
import org.xwsx.dto.UserDto;
import org.xwsx.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 获取用户列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<UserDto> init(){

        return userService.getList();
    }

    /**
     * 新增
     * @param userDto
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public PageCodeDto addUser(UserDto userDto){
        PageCodeDto result;
        if(userService.insert(userDto)){
            result = new PageCodeDto(PageCodeEnum.ADD_SUCCESS);
        }else{
            result = new PageCodeDto(PageCodeEnum.USERNAME_EXITES);
        }
        return result;
    }

    /**
     * 修改
     */
    @RequestMapping(value="/{id}",method=RequestMethod.PUT)
    public PageCodeDto update(UserDto userDto){
        PageCodeDto result;
        if(userService.update(userDto)){
            result = new PageCodeDto(PageCodeEnum.UPDATE_SUCCESS);
        }else{
            result = new PageCodeDto(PageCodeEnum.UPDATEP_FAIL);
        }
        return result;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/{id}",method =RequestMethod.DELETE)
    public PageCodeDto delete(){
        return  null;
    }

}
