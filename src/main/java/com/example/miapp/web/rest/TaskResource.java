package com.example.miapp.web.rest;
import com.example.miapp.domain.Task;
import com.example.miapp.repository.TaskRepository;
import com.example.miapp.service.TaskService;
import com.example.miapp.service.dto.TaskDTO;
import com.example.miapp.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;


@RestController
@RequestMapping("/api")
public class TaskResource {

    private final Logger log = LoggerFactory.getLogger(TaskResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaskService taskService;

    private final TaskRepository taskRepository;


    public TaskResource(TaskService taskService, TaskRepository taskRepository) {

        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

   
    @PostMapping("/task")
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskDTO taskDTO) throws URISyntaxException {
        log.debug("REST request to save Task : {}", taskDTO);

            Task newTask = taskService.createTask(taskDTO);
            return ResponseEntity.created(new URI("/api/task/" + newTask.getNameTask()))
                .headers(HeaderUtil.createAlert(applicationName,  "userManagement.created", newTask.getNameTask()))
                .body(newTask);
        
    }

    @GetMapping("/task/userChilds")
    public List<String> getUserChilds() {
        return taskService.getUserChilds();
    }

 
}