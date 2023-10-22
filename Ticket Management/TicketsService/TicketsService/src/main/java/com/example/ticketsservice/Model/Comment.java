package com.example.ticketsservice.Model;

import java.sql.Timestamp;
import java.util.Date;

public class Comment {
    private int cid;
    private String comment;
    private int tid;
    private Timestamp date;
    String author;
    public Comment() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getCId() {
        return cid;
    }

    public void setId(int cid) {
        this.cid = cid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

}
