package com.qf.service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.mapper.UsersMapper;
import com.qf.pojo.UserRoles;
import com.qf.pojo.Users;
import com.qf.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;


    @Override
    public int addUser(Users user) {
        return usersMapper.addUser(user);
    }

    @Override
    public int delUser(int uid) {
        return usersMapper.delUser(uid);
    }

    @Override
    public int updUser(Users user) {
        return usersMapper.updUser(user);
    }

    @Override
    public PageInfo<Users> getUsers(int page, int pageNum) {
        PageHelper.startPage(page,pageNum);
        List<Users> users = usersMapper.getUsers();
        PageInfo<Users> usersPageInfo = new PageInfo<Users>(users);
        return usersPageInfo;
    }

    @Override
    public Users getUserByUsername(String username) {
        Users userByUsername = usersMapper.getUserByUsername(username);
       // System.out.println("userByUsername:"+userByUsername);

        return usersMapper.getUserByUsername(username);
    }

    @Override
    public UserRoles getRoleNameByUserName(String username) {
        return usersMapper.getRoleNameByUserName(username);
    }
}
