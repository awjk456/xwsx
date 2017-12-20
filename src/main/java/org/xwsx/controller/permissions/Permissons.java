package org.xwsx.controller.permissions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/permissons")
public class Permissons {

    @RequestMapping()
    public String init(){

        return "/permissions/permissions";
    }
}
