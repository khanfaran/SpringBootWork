package com.example.assignmentservice.Controller;

import com.example.assignmentservice.Mapper.AssignmentMapper;
import com.example.assignmentservice.Model.Assignment;
import com.example.assignmentservice.Model.Ticket;
import com.example.assignmentservice.Model.User;
import com.example.assignmentservice.Service.Validator;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.xml.stream.events.Comment;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class AssignmentController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    Validator validator;

    AssignmentMapper assignmentMapper;
    public AssignmentController(AssignmentMapper assignmentMapper){
        this.assignmentMapper = assignmentMapper;
    }

    //Assigns tickets and checks if user has hit the maximum number of tickets or not.
    @PostMapping("assignment/create")
    public String createAssignment(@RequestBody Assignment assignment){
        String message = "";
        User user = restTemplate.getForObject("http://localhost:8080/users/findUser/"+assignment.getUid(), User.class);
        System.out.println(user.getId());
        if (!validator.checkUserTickets(user)){
           message += "Maximum tickets reached. Ticket will be assigned to next user\n";
           user = restTemplate.getForObject("http://localhost:8080/users/getEligible", User.class);
           if (user == null){
               return "All users are assigned the maximum number of tickets";
           }
            else{
                assignment.setUid(user.getId());
           }
        }
        assignmentMapper.createAssignment(assignment);
        user = restTemplate.getForObject("http://localhost:8080/users/addTicket/"+assignment.getUid(), User.class);
        message+="Ticket Added";
        return message;
    }

    //gets all assignments
    @GetMapping("assignment/getAll")
    public List<Assignment> getAssignments (){
        return assignmentMapper.getAll();
    }

    //gets user and their assigned tickets
    @GetMapping("assignment/findUserTickets/{uid}")
    public Pair<String,List<Ticket>> findUserTickets(@PathVariable int uid){
        List<Assignment> assignments = assignmentMapper.findUserTickets(uid);
        List<Ticket> tickets = new ArrayList<>();
        List<Comment> comments = new ArrayList<>();
        User user = restTemplate.getForObject("http://localhost:8080/users/findUser/"+uid,User.class);
        for(Assignment assignment:assignments){
            tickets.add(restTemplate.getForObject("http://localhost:8081/tickets/findById/"+assignment.getTid(),Ticket.class));
        }

        String message = "Tickets assigned to user " +user.getName() +"  are:";
        Pair<String, List<Ticket>> result = new Pair<>(message,tickets);
        return result;
    }
}
