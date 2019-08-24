package com.example.miapp.service;
import com.example.miapp.domain.Family;
import com.example.miapp.domain.User;
import com.example.miapp.repository.FamilyRepository;
import com.example.miapp.repository.UserRepository;
import com.example.miapp.service.dto.FamilyDTO;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for managing users.
 */
@Service
public class FamilyService {


    private final UserRepository userRepository;
    private final FamilyRepository familyRepository;


    public FamilyService(UserRepository userRepository, FamilyRepository familyRepository) {
        this.userRepository = userRepository;
        this.familyRepository = familyRepository;
    }
   

    public Family createFamily(FamilyDTO familyDTO) {
        Family family = new Family();
        family.setNameFamily(familyDTO.getNameFamily());
/*         if (familyDTO.getUsers() != null) {
            Set<User> users = familyDTO.getUsers().stream()
                .map(userRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
            family.setUsers(users);
        } */
        familyRepository.save(family);
/*         log.debug("Created Information for User: {}", family);
 */        return family;
    }

   

 
    
  
    public Optional<Family> getFamilyWithUsers(String id) {
        return familyRepository.findById(id);
    }
  
    /**
     * Gets a list of all the authorities.
     * @return a list of all the authorities.
     */
    public List<String> getUsers() {
        return userRepository.findAll().stream().map(User::getLogin).collect(Collectors.toList());
    }

    
 /*     public Page<FamilyDTO> getAllManagedFamilies(Pageable pageable) {
        return familyRepository.findAllFamilies(pageable).map(FamilyDTO::new);
    }  */

}
