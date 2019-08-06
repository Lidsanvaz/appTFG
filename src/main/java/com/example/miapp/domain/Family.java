package com.example.miapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@org.springframework.data.mongodb.core.mapping.Document(collection = "jhi_family")
public class Family implements Serializable {
    private static final long serialVersionUID = 1L;

   
    @Id
    @Size(max = 50)
    @Field("nameFamily")
    private String nameFamily;


  

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
