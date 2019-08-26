package com.example.miapp.service.dto;
import com.example.miapp.domain.UserChild;
import javax.validation.constraints.*;
import java.time.LocalDate;


/**
 * A DTO representing a family, with his users.
 */
public class UserChildDTO {



    @Size(max = 50)
    private String nameUserChild;

    private LocalDate bornDate;

    public String getNameUserChild() {
        return nameUserChild;
    }

    public void setNameUserChild(String nameUserChild) {
        this.nameUserChild = nameUserChild;
    }

    
    public LocalDate getBornDate() {
        return bornDate;
    }

    public void setBornDate(LocalDate bornDate) {
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
