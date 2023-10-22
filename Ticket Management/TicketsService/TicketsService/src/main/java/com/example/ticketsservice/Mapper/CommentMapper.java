package com.example.ticketsservice.Mapper;

import com.example.ticketsservice.Model.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    public void addComment(String comment, int tid, String author);
    public List<Comment> getAllComments();
    public Comment selectLast();
    public Comment selectLastComment(int tid);
}
