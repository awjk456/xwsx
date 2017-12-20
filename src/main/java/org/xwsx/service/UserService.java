package org.xwsx.service;

import org.xwsx.dto.UserDto;

import java.util.List;

public interface UserService {
    boolean LoginCheck(UserDto userDto);
    List<UserDto> getList();
    boolean insert(UserDto userDto);
    boolean update(UserDto userDto);
    boolean delete(int id);
}
