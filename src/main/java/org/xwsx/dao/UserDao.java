package org.xwsx.dao;

import org.xwsx.bean.User;

import java.util.List;

public interface UserDao {
    List<User> select(User user);
    List<User> getList();
    int insert(User user);
    int update(User user);
    int delete(int id);
}
