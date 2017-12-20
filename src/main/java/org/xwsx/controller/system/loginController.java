package org.xwsx.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.xwsx.constant.PageCodeEnum;
import org.xwsx.constant.SessionKetConst;
import org.xwsx.dto.UserDto;
import org.xwsx.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class loginController {

    @Autowired
    UserService userService;

    @RequestMapping()
    public String loginInit(){
        return "/system/login";
    }

    @RequestMapping("/sessionTimeout")
    public String sessionTimeout(RedirectAttributes attr){
        attr.addFlashAttribute(PageCodeEnum.KEY,PageCodeEnum.LOGIN_TIMEOUT);
        return "redirect:/login";
    }


    @RequestMapping("/validate")
    public String validate(UserDto userDto, RedirectAttributes attr, HttpSession session){
        System.out.println("========Md5密码=======:"+userDto);
        if(userService.LoginCheck(userDto)){
            session.setAttribute(SessionKetConst.KEY,userDto);
            return "redirect:/index";
        }
        attr.addFlashAttribute(PageCodeEnum.KEY,PageCodeEnum.LOGIN_FAIL);
        return "redirect:/login";
    }
}
