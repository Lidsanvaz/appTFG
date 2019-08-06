package com.example.miapp.repository;

import com.example.miapp.domain.UserChild;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



/**
 * Spring Data MongoDB repository for the {@link User} entity.
 */
@Repository
public interface UserChildRepository extends MongoRepository<UserChild, String> {

/*      Page<Family> findAllFamilies(Pageable pageable);
 */ 
}