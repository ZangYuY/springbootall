package com.qf.service;

import com.github.pagehelper.PageInfo;
import com.qf.pojo.UserRoles;
import com.qf.pojo.Users;

public interface UsersService {
    public int addUser(Users user);
    public int delUser(int uid);
    public int updUser(Users user);

//    public List<Users> getUsers();
    public PageInfo<Users> getUsers(int page, int pageNum);

    public Users getUserByUsername(String username);

    public UserRoles getRoleNameByUserName(String username);
}
