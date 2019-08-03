package com.example.miapp.service;

import com.example.miapp.config.Constants;
import com.example.miapp.domain.Family;
import com.example.miapp.domain.User;
import com.example.miapp.repository.FamilyRepository;
import com.example.miapp.repository.UserRepository;
import com.example.miapp.security.AuthoritiesConstants;
import com.example.miapp.security.SecurityUtils;
import com.example.miapp.service.dto.FamilyDTO;
import com.example.miapp.service.util.RandomUtil;
import com.example.miapp.web.rest.errors.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
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

    /**
     * Update basic information (first name) for the current family.
     *
     * @param nameFamily name of family.
     */
    /* public void updateUser(String firstName, String lastName, String email, String langKey, String imageUrl) {
        SecurityUtils.getCurrentUserLogin()
            .flatMap(userRepository::findOneByLogin)
            .ifPresent(user -> {
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email.toLowerCase());
                user.setLangKey(langKey);
                user.setImageUrl(imageUrl);
                userRepository.save(user);
                this.clearUserCaches(user);
                log.debug("Changed Information for User: {}", user);
            });
    } */

    /**
     * Update all information for a specific user, and return the modified user.
     *
     * @param userDTO user to update.
     * @return updated user.
     */
   /* public Optional<UserDTO> updateUser(UserDTO userDTO) {
        return Optional.of(userRepository
            .findById(userDTO.getId()))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(user -> {
                this.clearUserCaches(user);
                user.setLogin(userDTO.getLogin().toLowerCase());
                user.setFirstName(userDTO.getFirstName());
                user.setLastName(userDTO.getLastName());
                user.setEmail(userDTO.getEmail().toLowerCase());
                user.setImageUrl(userDTO.getImageUrl());
                user.setLangKey(userDTO.getLangKey());
                Set<Authority> managedAuthorities = user.getAuthorities();
                managedAuthorities.clear();
                userDTO.getAuthorities().stream()
                    .map(authorityRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .forEach(managedAuthorities::add);
                userRepository.save(user);
                this.clearUserCaches(user);
                log.debug("Changed Information for User: {}", user);
                return user;
            })
            .map(UserDTO::new);
    }
 */

    
  
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
