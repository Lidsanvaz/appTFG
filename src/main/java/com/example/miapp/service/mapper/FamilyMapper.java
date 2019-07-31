package com.example.miapp.service.mapper;

import com.example.miapp.domain.Authority;
import com.example.miapp.domain.Family;
import com.example.miapp.domain.User;

import com.example.miapp.service.dto.FamilyDTO;

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
public class FamilyMapper {

    public List<FamilyDTO> familiesToFamilyDTOs(List<Family> families) {
        return families.stream()
            .filter(Objects::nonNull)
            .map(this::familyToFamilyDTO)
            .collect(Collectors.toList());
    }

    public FamilyDTO familyToFamilyDTO(Family family) {
        return new FamilyDTO(family);
    }

    public List<Family> familyDTOsToFamilies(List<FamilyDTO> familyDTOs) {
        return familyDTOs.stream()
            .filter(Objects::nonNull)
            .map(this::familyDTOToFamily)
            .collect(Collectors.toList());
    }

    public Family familyDTOToFamily(FamilyDTO familyDTO) {
        if (familyDTO == null) {
            return null;
        } else {
            Family family = new Family();
            family.setId(familyDTO.getId());
            family.setNameFamily(familyDTO.getNameFamily());
            Set<User> users = this.usersFromStrings(familyDTO.getUsers());
            family.setUsers(users);
            return family;
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

    public Family familyFromId(String id) {
        if (id == null) {
            return null;
        }
        Family family = new Family();
        family.setId(id);
        return family;
    }
   
}
