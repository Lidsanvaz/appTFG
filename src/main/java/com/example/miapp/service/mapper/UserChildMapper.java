package com.example.miapp.service.mapper;
import com.example.miapp.domain.UserChild;
import com.example.miapp.domain.User;

import com.example.miapp.service.dto.UserChildDTO;

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
public class UserChildMapper {

    public List<UserChildDTO> userChildsToUserChildDTOs(List<UserChild> userChilds) {
        return userChilds.stream()
            .filter(Objects::nonNull)
            .map(this::userChildToUserChildDTO)
            .collect(Collectors.toList());
    }

    public UserChildDTO userChildToUserChildDTO(UserChild userChild) {
        return new UserChildDTO(userChild);
    }

    public List<UserChild> userChildDTOsToUserChilds(List<UserChildDTO> userChildDTOs) {
        return userChildDTOs.stream()
            .filter(Objects::nonNull)
            .map(this::userChildDTOToUserChild)
            .collect(Collectors.toList());
    }

    public UserChild userChildDTOToUserChild(UserChildDTO userChildDTO) {
        if (userChildDTO == null) {
            return null;
        } else {
            UserChild userChild = new UserChild();
            userChild.setNameUserChild(userChildDTO.getNameUserChild());
            userChild.setBornDate(userChildDTO.getBornDate());
           /*  Set<User> users = this.usersFromStrings(familyDTO.getUsers());
            family.setUsers(users); */
            return userChild;
        }
    }


    /* private Set<User> usersFromStrings(Set<String> usersAsString) {
        Set<User> users = new HashSet<>();

        if(usersAsString != null){
            users = usersAsString.stream().map(string -> {
                User auth = new User();
                auth.setLogin(string);
                return auth;
            }).collect(Collectors.toSet());
        }

        return users;
    } */

 
}
