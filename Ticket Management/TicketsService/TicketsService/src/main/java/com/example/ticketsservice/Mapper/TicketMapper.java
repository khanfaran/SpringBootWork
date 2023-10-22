package com.example.ticketsservice.Mapper;

import com.example.ticketsservice.Model.Comment;
import com.example.ticketsservice.Model.Ticket;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TicketMapper {
    public List<Ticket> returnAll();
    public void addTicket(Ticket ticket);
    public List<Ticket> findTicket (String status);
    public void changeStatus(int tid,String status);
    public Ticket findById(int tid);
    public Ticket getComments(int tid);
}
