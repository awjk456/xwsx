package org.xwsx.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xwsx.bean.User;
import org.xwsx.dao.UserDao;
import org.xwsx.dto.UserDto;
import org.xwsx.service.UserService;
import org.xwsx.util.MD5Util;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public boolean LoginCheck(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        List<User> list = userDao.select(user);
        if(list.size() == 1){
            return true;
        }

        return false;
    }

    @Override
    public List<UserDto> getList() {
        List<UserDto> result =new ArrayList<UserDto>();
        List<User> list = userDao.select(new User());
        for(User user:list){
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user,userDto);
            userDto.setpId(0);
            result.add(userDto);
        }
        return result;
    }

    @Override
    public boolean insert(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        System.out.println("======MD5Password========="+user.getPassword());
        int i = userDao.insert(user);
        if(i==1){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        int i = userDao.update(user);
        if(i == 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if(userDao.delete(id)==1){
            return true;
        }
        return false;
    }
}
