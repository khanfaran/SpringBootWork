package com.example.ticketsservice.Mapper;

import com.example.ticketsservice.Model.Joiner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JoinerMapper {
    public void createJoiner(int cid, int tid);
    public List<Joiner> getAll();
}
