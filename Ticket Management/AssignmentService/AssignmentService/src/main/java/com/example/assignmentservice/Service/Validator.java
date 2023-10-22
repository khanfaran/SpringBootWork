package com.example.assignmentservice.Service;

import com.example.assignmentservice.Model.User;
import org.springframework.stereotype.Service;

@Service
public class Validator {
    public boolean checkUserTickets(User user){
        if(user.getTickets()>4){
            return false;
        }
        return true;
    }
}
