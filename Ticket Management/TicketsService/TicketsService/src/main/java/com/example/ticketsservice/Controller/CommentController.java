package com.example.ticketsservice.Controller;

import com.example.ticketsservice.Mapper.CommentMapper;
import com.example.ticketsservice.Model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {
    CommentController commentController;
    CommentMapper commentMapper;
    public CommentController(CommentMapper commentMapper){
        this.commentMapper = commentMapper;
    }
    @GetMapping("/comments/getAll")
    public List<Comment> getAllComments(){
        return commentMapper.getAllComments();
    }
    @GetMapping("/comments/getLast")
    public Comment getLast(){
        return commentMapper.selectLast();
    }
}
