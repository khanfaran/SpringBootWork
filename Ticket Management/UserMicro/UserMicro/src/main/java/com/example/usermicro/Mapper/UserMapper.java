package com.example.usermicro.Mapper;

import com.example.usermicro.Model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<User> getUsers();
    public void addTicket(int id);
    public User findUser(int id);
    public User getEligibleUser();
}
