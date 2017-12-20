package org.xwsx.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xwsx.dto.GroupDto;
import org.xwsx.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupService groupService;

    @RequestMapping(method = RequestMethod.GET)
    public List<GroupDto> init(){

        return groupService.getList();
    }
}
