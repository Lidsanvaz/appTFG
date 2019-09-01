package com.example.miapp.service.dto;
import com.example.miapp.domain.Task;
import com.example.miapp.domain.User;
import com.example.miapp.domain.Family;
import com.example.miapp.domain.UserChild;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;



public class TaskDTO {



    @Size(max = 50)
    private String nameTask;

    private Set<String> typeTasks;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;


    private Set<String> users;

    private Set<String> userChilds;

    private Set<String> weekDays;

    private Set<String> families;

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
    


    public Set<String> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(Set<String> weekDays) {
        this.weekDays = weekDays;
    }


    public Set<String> getFamilies() {
        return families;
    }

    public void setFamilies(Set<String> families) {
        this.families = families;
    }


    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }


    public Set<String> getTypeTasks() {
        return typeTasks;
    }

    public void setTypeTasks(Set<String> typeTasks) {
        this.typeTasks = typeTasks;
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
        this.typeTasks = task.getTypeTasks();
        this.description = task.getDescription();
        this.startDate = task.getStartDate();
        this.endDate = task.getEndDate();
        this.weekDays = task.getWeekDays();
        this.users = task.getUsers().stream()
        .map(User::getLogin)
        .collect(Collectors.toSet());
        this.userChilds = task.getUserChilds().stream()
        .map(UserChild::getNameUserChild)
        .collect(Collectors.toSet());
        this.families = task.getFamilies().stream()
        .map(Family::getNameFamily)
        .collect(Collectors.toSet());
    }

   

   
}
