package com.example.miapp.repository;

import com.example.miapp.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

/*     Page<Task> findAllTasks(Pageable pageable);
 */
}