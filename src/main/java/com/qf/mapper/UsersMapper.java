package com.qf.mapper;

import com.qf.pojo.UserRoles;
import com.qf.pojo.Users;

import java.util.List;

public interface UsersMapper {

    public int delUser(int uid);

    public int addUser(Users user);

    public int updUser(Users user);

    public List<Users> getUsers();

    public Users getUserByUsername(String username);

    public UserRoles getRoleNameByUserName(String username);
}
