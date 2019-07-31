package com.example.miapp.service.dto;


import com.example.miapp.domain.Family;
import com.example.miapp.domain.User;


import javax.validation.constraints.*;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO representing a family, with his users.
 */
public class FamilyDTO {

    private String id;


    @Size(max = 50)
    private String nameFamily;

    public String getNameFamily() {
        return nameFamily;
    }

    public void setNameFamily(String nameFamily) {
        this.nameFamily = nameFamily;
    }

    private Set<String> users;


    public Set<String> getUsers() {
        return users;
    }

    public void setUsers(Set<String> users) {
        this.users = users;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public FamilyDTO() {
        // Empty constructor needed for Jackson.
    }

    public FamilyDTO(Family family) {
        this.id = family.getId();
        this.nameFamily = family.getNameFamily();
        this.users = family.getUsers().stream()
        .map(User::getLogin)
        .collect(Collectors.toSet());
    }

   

    @Override
    public String toString() {
        return "FamilyDTO{" +
            "nameFamily='" + nameFamily + '\'' +
            ", users='" + users + '\'' +
            "}";
    }
}
