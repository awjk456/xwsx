package org.xwsx.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xwsx.bean.Group;
import org.xwsx.dao.GroupDao;
import org.xwsx.dto.GroupDto;
import org.xwsx.service.GroupService;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupDao groupDao;

    @Override
    public List<GroupDto> getList() {
        List<Group> list = groupDao.getList();
        List<GroupDto> result = new ArrayList<GroupDto>();
        for(Group group : list){
            GroupDto groupDto = new GroupDto();
            BeanUtils.copyProperties(group,groupDto);
            groupDto.setpId(0);
            result.add(groupDto);
        }
        return result;
    }
}
