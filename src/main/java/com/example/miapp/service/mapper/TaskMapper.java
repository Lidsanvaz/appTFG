package com.example.miapp.service.mapper;
import com.example.miapp.domain.Task;
import com.example.miapp.domain.User;
import com.example.miapp.domain.UserChild;

import com.example.miapp.service.dto.TaskDTO;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Mapper for the entity {@link User} and its DTO called {@link UserDTO}.
 *
 * Normal mappers are generated using MapStruct, this one is hand-coded as MapStruct
 * support is still in beta, and requires a manual step with an IDE.
 */
@Service
public class TaskMapper {

    public List<TaskDTO> tasksToTaskDTOs(List<Task> tasks) {
        return tasks.stream()
            .filter(Objects::nonNull)
            .map(this::taskToTaskDTO)
            .collect(Collectors.toList());
    }

    public TaskDTO taskToTaskDTO(Task task) {
        return new TaskDTO(task);
    }

    public List<Task> taskDTOsToTasks(List<TaskDTO> taskDTOs) {
        return taskDTOs.stream()
            .filter(Objects::nonNull)
            .map(this::taskDTOToTask)
            .collect(Collectors.toList());
    }

    public Task taskDTOToTask(TaskDTO taskDTO) {
        if (taskDTO == null) {
            return null;
        } else {
            Task task = new Task();
            task.setNameTask(taskDTO.getNameTask());
            Set<User> users = this.usersFromStrings(taskDTO.getUsers());
            task.setUsers(users);
            Set<UserChild> userChilds = this.userChildsFromStrings(taskDTO.getUserChilds());
            task.setUserChilds(userChilds);

            return task;
        }
    }


    private Set<User> usersFromStrings(Set<String> usersAsString) {
        Set<User> users = new HashSet<>();

        if(usersAsString != null){
            users = usersAsString.stream().map(string -> {
                User auth = new User();
                auth.setLogin(string);
                return auth;
            }).collect(Collectors.toSet());
        }

        return users;
    }

    private Set<UserChild> userChildsFromStrings(Set<String> userChildsAsString) {
        Set<UserChild> userChilds = new HashSet<>();

        if(userChildsAsString != null){
            userChilds = userChildsAsString.stream().map(string -> {
                UserChild auth = new UserChild();
                auth.setNameUserChild(string);
                return auth;
            }).collect(Collectors.toSet());
        }

        return userChilds;
    }
   
}
