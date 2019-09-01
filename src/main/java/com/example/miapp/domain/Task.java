package com.example.miapp.domain;

import com.example.miapp.config.Constants;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * A user.
 */
@org.springframework.data.mongodb.core.mapping.Document(collection = "jhi_task")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Size(max=50)
    @Field("name_task")
    @NotNull
    private String nameTask;

  

    @Size(max = 100)
    @Field("description")
    private String description;

    @Field("start_date")
    @NotNull
    private LocalDateTime startDate;

    @Field("end_date")
    @NotNull
    private LocalDateTime endDate;

      
    @JsonIgnore
    private Set<String> typeTasks = new HashSet<>();

    @JsonIgnore
    private Set<User> users = new HashSet<>(); 

    @JsonIgnore
    private Set<UserChild> userChilds = new HashSet<>();

    @JsonIgnore
    private Set<Family> families = new HashSet<>();

    @JsonIgnore
    private Set<String> weekDays = new HashSet<>();

    public Set<UserChild> getUserChilds() {
        return userChilds;
    }

    public void setUserChilds(Set<UserChild> userChilds) {
        this.userChilds = userChilds;
    }

    public Set<Family> getFamilies() {
        return families;
    }

    public void setFamilies(Set<Family> families) {
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


    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<String> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(Set<String> weekDays) {
        this.weekDays = weekDays;
    }
    
}
