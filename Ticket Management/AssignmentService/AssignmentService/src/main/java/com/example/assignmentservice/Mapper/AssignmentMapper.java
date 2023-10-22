package com.example.assignmentservice.Mapper;

import com.example.assignmentservice.Model.Assignment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AssignmentMapper {
    public void createAssignment(Assignment assignment);
    public List<Assignment> getAll();
    public List<Assignment> getUserTickets (int id);
    public List<Assignment> findUserTickets(int uid);
}
