package com.example.miapp.service.dto;
import com.example.miapp.domain.UserChild;
import javax.validation.constraints.*;
import java.time.LocalDateTime;


/**
 * A DTO representing a family, with his users.
 */
public class UserChildDTO {



    @Size(max = 50)
    private String nameUserChild;

    private LocalDateTime bornDate;

    public String getNameUserChild() {
        return nameUserChild;
    }

    public void setNameUserChild(String nameUserChild) {
        this.nameUserChild = nameUserChild;
    }

    
    public LocalDateTime getBornDate() {
        return bornDate;
    }

    public void setBornDate(LocalDateTime bornDate) {
        this.bornDate = bornDate;
    }



    public UserChildDTO() {
        // Empty constructor needed for Jackson.
    }

    public UserChildDTO(UserChild userChild) {
        this.nameUserChild = userChild.getNameUserChild();
        this.bornDate = userChild.getBornDate();
       
    }

   

   
}
