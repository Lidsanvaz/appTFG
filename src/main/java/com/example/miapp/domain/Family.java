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
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@org.springframework.data.mongodb.core.mapping.Document(collection = "jhi_family")
public class Family implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Size(max = 50)
    @Field("nameFamily")
    private String nameFamily;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getNameFamily() {
        return nameFamily;
    }

    public void setNameFamily(String nameFamily) {
        this.nameFamily = nameFamily;
    }

    @JsonIgnore
    private Set<User> users = new HashSet<>();


    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}
