package com.example.miapp.service.dto;
import com.example.miapp.domain.Task;
import com.example.miapp.domain.User;
import com.example.miapp.domain.UserChild;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;



public class TaskDTO {



    @Size(max = 50)
    private String nameTask;

    private String typeTask;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;


    private Set<String> users;

    private Set<String> userChilds;

    public Set<String> getUsers() {
        return users;
    }

    public void setUsers(Set<String> users) {
        this.users = users;
    }

    public Set<String> getUserChilds(){
        return userChilds;
    }


    public void setUserChilds(Set<String> userChilds){
        this.userChilds = userChilds;
    }
    

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }


    public String getTypeTask() {
        return typeTask;
    }

    public void setTypeTask(String typeTask) {
        this.typeTask = typeTask;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public TaskDTO() {
        // Empty constructor needed for Jackson.
    }

    public TaskDTO(Task task) {
        this.nameTask = task.getNameTask();
        this.typeTask = task.getTypeTask();
        this.description = task.getDescription();
        this.startDate = task.getStartDate();
        this.endDate = task.getEndDate();
        this.users = task.getUsers().stream()
        .map(User::getLogin)
        .collect(Collectors.toSet());
        this.userChilds = task.getUserChilds().stream()
        .map(UserChild::getNameUserChild)
        .collect(Collectors.toSet());
       
    }

   

   
}
