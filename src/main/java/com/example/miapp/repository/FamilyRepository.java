package com.example.miapp.repository;

import com.example.miapp.domain.Family;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.time.Instant;

/**
 * Spring Data MongoDB repository for the {@link User} entity.
 */
@Repository
public interface FamilyRepository extends MongoRepository<Family, String> {

/*     Page<Family> findAllFamilies(Pageable pageable);
 */
}