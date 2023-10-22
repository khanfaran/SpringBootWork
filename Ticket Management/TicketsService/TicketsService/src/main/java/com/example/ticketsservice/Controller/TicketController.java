package com.example.ticketsservice.Controller;

import com.example.ticketsservice.Mapper.CommentMapper;
import com.example.ticketsservice.Mapper.JoinerMapper;
import com.example.ticketsservice.Mapper.TicketMapper;
import com.example.ticketsservice.Model.Comment;
import com.example.ticketsservice.Model.Ticket;
import com.example.ticketsservice.Model.User;
import com.example.ticketsservice.Service.Sorter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class TicketController {
    @Autowired
    Sorter sorter;
    @Autowired
    RestTemplate restTemplate;
    TicketMapper ticketMapper;
    CommentMapper commentMapper;
    JoinerMapper joinerMapper;

    public TicketController (TicketMapper ticketMapper, CommentMapper commentMapper, JoinerMapper joinerMapper){
        this.ticketMapper = ticketMapper;
        this.commentMapper = commentMapper;
        this.joinerMapper = joinerMapper;
    }

    //gets all tickets in a sorted manner
    @RequestMapping("/tickets/getAll")
    public List<Ticket> getList(){
        List<Ticket> tickets = ticketMapper.returnAll();
        sorter.sortArray(tickets);
        return tickets;
    }

    //adds ticket with the status set to Ready
    @PostMapping("tickets/add")
    public String addTicket(@RequestBody Ticket ticket){
        ticket.setStatus("Ready");
        ticketMapper.addTicket(ticket);
        return "Ticket with ready status added";
    }

    //gets tickets according to status in a sorted manner.
    @GetMapping("tickets/get/{status}")
    public List<Ticket> findTicket(@PathVariable String status){
        List<Ticket> tickets = ticketMapper.findTicket(status);
        sorter.sortArray(tickets);
        return tickets;
    }

    //Changes the status of the ticket. Adds a comment, along with data and author.
    @PutMapping("tickets/changeStatus/{tid}/{status}/{uid}")
    public String changeStatus (@PathVariable int tid, @PathVariable String status, @PathVariable int uid){
            Ticket ticket = ticketMapper.findById(tid);
            ticketMapper.changeStatus(tid, status);
            String comment = "\nTicket with id " +tid +" status has been changed from " +ticket.getStatus() +" to " +status;
            User user = restTemplate.getForObject("http://localhost:8080/users/findUser/"+uid, User.class);
            commentMapper.addComment(comment, tid,user.getName());
            Comment newComment = commentMapper.selectLast();
            joinerMapper.createJoiner(newComment.getCId(),tid);
            return "Ticket with id " +tid +" status has been changed from " +ticket.getStatus() +" to " +status;

    }
    //returns all comments
    @GetMapping("tickets/getComments/{tid}")
    public Ticket getComments(@PathVariable int tid) {
        return ticketMapper.getComments(tid);
    }

    //finds ticket by Id
    @GetMapping("tickets/findById/{tid}")
    public Ticket findById(@PathVariable int tid) {
        return ticketMapper.findById(tid);
    }

    //Gets all tickets along with the last comments
    @GetMapping("tickets/getTickets")
    public List<Ticket> getTickets(){
        List<Ticket> tickets = ticketMapper.returnAll();
        sorter.sortArray(tickets);
        for(Ticket ticket : tickets){
            Comment lastComment = commentMapper.selectLastComment(ticket.getId());
            if (lastComment != null){
                ticket.getComments().add(lastComment);}
        }
        return tickets;
    }

}
