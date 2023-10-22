package com.example.ticketsservice.Service;

import com.example.ticketsservice.Model.Ticket;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@Service
public class Sorter {
    public void sortArray(List<Ticket> tickets){
        Collections.sort(tickets, new Comparator<Ticket>() {
            @Override
            public int compare(Ticket t1, Ticket t2) {
                return Integer.valueOf(t1.getPriority()).compareTo(t2.getPriority());
            }
        });
    }
}
