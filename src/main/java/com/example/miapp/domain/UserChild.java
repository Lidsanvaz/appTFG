package com.example.miapp.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.validation.constraints.NotNull;



import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;


@org.springframework.data.mongodb.core.mapping.Document(collection = "jhi_userchild")
public class UserChild implements Serializable {
    private static final long serialVersionUID = 1L;

   
    @Id
    @Size(max = 50)
    @Field("nameUserChild")
    @NotNull
    private String nameUserChild;

    @Field("born_date")
    @NotNull
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


   
}
