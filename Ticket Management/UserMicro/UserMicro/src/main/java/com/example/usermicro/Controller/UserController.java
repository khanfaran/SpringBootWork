package com.example.usermicro.Controller;

import com.example.usermicro.Mapper.UserMapper;
import com.example.usermicro.Model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    UserMapper userMapper;
    public UserController(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    //gets all users
    @RequestMapping("/users/getAll")
    public List<User> getList(){
        return userMapper.getUsers();
    }

    //adds ticket
    @GetMapping("users/addTicket/{id}")
    public User addTicket(@PathVariable int id){
        userMapper.addTicket(id);
        return userMapper.findUser(id);
    }
    //finds user with less than 5 tickets
    @GetMapping("users/getEligible")
    public User getEligibleUser(){
        return userMapper.getEligibleUser();
    }

    //finds user according to id
    @GetMapping ("users/findUser/{id}")
    public User findUser(@PathVariable int id){
        return userMapper.findUser(id);
    }
}
