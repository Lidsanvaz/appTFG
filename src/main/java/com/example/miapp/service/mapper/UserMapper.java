package com.example.miapp.service.mapper;

import com.example.miapp.domain.Authority;
import com.example.miapp.domain.User;
import com.example.miapp.domain.Family;
import com.example.miapp.domain.Task;
import com.example.miapp.domain.UserChild;
import com.example.miapp.service.dto.UserDTO;

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
public class UserMapper {

    public List<UserDTO> usersToUserDTOs(List<User> users) {
        return users.stream()
            .filter(Objects::nonNull)
            .map(this::userToUserDTO)
            .collect(Collectors.toList());
    }

    public UserDTO userToUserDTO(User user) {
        return new UserDTO(user);
    }

    public List<User> userDTOsToUsers(List<UserDTO> userDTOs) {
        return userDTOs.stream()
            .filter(Objects::nonNull)
            .map(this::userDTOToUser)
            .collect(Collectors.toList());
    }

    public User userDTOToUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        } else {
            User user = new User();
            user.setId(userDTO.getId());
            user.setLogin(userDTO.getLogin());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setImageUrl(userDTO.getImageUrl());
            user.setLangKey(userDTO.getLangKey());
            Set<Authority> authorities = this.authoritiesFromStrings(userDTO.getAuthorities());
            user.setAuthorities(authorities);
            Set<Family> families = this.familiesFromStrings(userDTO.getFamilies());
            user.setFamilies(families);
            Set<UserChild> userChilds = this.userChildsFromStrings(userDTO.getUserChilds());
            user.setUserChilds(userChilds);
            Set<Task> tasks = this.tasksFromStrings(userDTO.getTasks());
            user.setTasks(tasks);
            return user;
        }
    }


    private Set<Authority> authoritiesFromStrings(Set<String> authoritiesAsString) {
        Set<Authority> authorities = new HashSet<>();

        if(authoritiesAsString != null){
            authorities = authoritiesAsString.stream().map(string -> {
                Authority auth = new Authority();
                auth.setName(string);
                return auth;
            }).collect(Collectors.toSet());
        }

        return authorities;
    }


    private Set<Family> familiesFromStrings(Set<String> familiesAsString) {
        Set<Family> families = new HashSet<>();

        if(familiesAsString != null){
            families = familiesAsString.stream().map(string -> {
                Family fam = new Family();
                fam.setNameFamily(string);
                return fam;
            }).collect(Collectors.toSet());
        }

        return families;
    }

    private Set<UserChild> userChildsFromStrings(Set<String> userChildsAsString) {
        Set<UserChild> userChilds = new HashSet<>();

        if(userChildsAsString != null){
            userChilds = userChildsAsString.stream().map(string -> {
                UserChild userc = new UserChild();
                userc.setNameUserChild(string);
                return userc;
            }).collect(Collectors.toSet());
        }

        return userChilds;
    }

    private Set<Task> tasksFromStrings(Set<String> tasksAsString) {
        Set<Task> tasks = new HashSet<>();

        if(tasksAsString != null){
            tasks = tasksAsString.stream().map(string -> {
                Task tas = new Task();
                tas.setNameTask(string);
                return tas;
            }).collect(Collectors.toSet());
        }

        return tasks;
    }


    public User userFromId(String id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}
