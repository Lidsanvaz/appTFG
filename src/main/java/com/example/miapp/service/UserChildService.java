package com.example.miapp.service;
import com.example.miapp.domain.UserChild;
import com.example.miapp.repository.UserChildRepository;
import com.example.miapp.repository.UserRepository;
import com.example.miapp.service.dto.UserChildDTO;
import org.springframework.stereotype.Service;


/**
 * Service class for managing users.
 */
@Service
public class UserChildService {


    private final UserRepository userRepository;
    private final UserChildRepository userChildRepository;


    public UserChildService(UserRepository userRepository, UserChildRepository userChildRepository) {
        this.userRepository = userRepository;
        this.userChildRepository = userChildRepository;
    }
   

    public UserChild createUserChild(UserChildDTO userChildDTO) {
        UserChild userChild = new UserChild();
        userChild.setNameUserChild(userChildDTO.getNameUserChild());
        userChild.setBornDate(userChildDTO.getBornDate());

/*         if (familyDTO.getUsers() != null) {
            Set<User> users = familyDTO.getUsers().stream()
                .map(userRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
            family.setUsers(users);
        } */
        userChildRepository.save(userChild);
        return userChild;
    }

    
  
/*     public Optional<Family> getFamilyWithUsers(String id) {
        return familyRepository.findById(id);
    } */
  
    /**
     * Gets a list of all the users.
     * @return a list of all the users.
     */
 /*    public List<String> getUsers() {
        return userRepository.findAll().stream().map(User::getLogin).collect(Collectors.toList());
    } */



}
