package com.example.miapp.service;
import com.example.miapp.domain.Task;
import com.example.miapp.domain.User;
import com.example.miapp.domain.UserChild;
import com.example.miapp.repository.TaskRepository;
import com.example.miapp.repository.UserRepository;
import com.example.miapp.repository.UserChildRepository;
import com.example.miapp.service.dto.TaskDTO;
import org.springframework.stereotype.Service;
import com.example.miapp.security.SecurityUtils;
import java.util.*;
import java.util.stream.Collectors;
import java.time.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;





/**
 * Service class for managing users.
 */
@Service
public class TaskService {


    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final UserChildRepository userChildRepository;


    public TaskService(UserRepository userRepository, TaskRepository taskRepository, UserChildRepository userChildRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.userChildRepository = userChildRepository;
    }
   

    public Task createTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setNameTask(taskDTO.getNameTask());
        task.setTypeTask(taskDTO.getTypeTask());
        task.setDescription(taskDTO.getDescription());
        LocalDateTime hoy = LocalDateTime.now(); 
        if(taskDTO.getStartDate().isAfter(hoy) && taskDTO.getEndDate().isAfter(hoy) && taskDTO.getEndDate().isAfter(taskDTO.getStartDate())){
        task.setStartDate(taskDTO.getStartDate());
        task.setEndDate(taskDTO.getEndDate());
        if (taskDTO.getUserChilds() != null) {
            Set<UserChild> userChilds = taskDTO.getUserChilds().stream()
                .map(userChildRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
            task.setUserChilds(userChilds);
        }
       /*  SecurityUtils.getCurrentUserLogin()
        .flatMap(userRepository::findOneByLogin)
        .ifPresent(user -> {
            Set<User> users = task.getUsers();
            users.add(user);
            task.setUsers(users);
        }); */

        SecurityUtils.getCurrentUserLogin()
        .flatMap(userRepository::findOneByLogin)
        .ifPresent(user -> {
            Set<Task> tasks = user.getTasks();
            tasks.add(task);
            user.setTasks(tasks);
            userRepository.save(user); 
        });        
    }
        taskRepository.save(task);
        return task;
    }

    public List<String> getUserChilds() {
     /*    List<String> re;
        SecurityUtils.getCurrentUserLogin()
        .flatMap(userRepository::findOneByLogin)
        .ifPresent(user -> {
            Set<UserChild> userchilds = user.getUserChilds();
            re = userchilds.stream().map(UserChild::getNameUserChild).collect(Collectors.toList());
        });   */
         List<String> res = userChildRepository.findAll().stream().map(UserChild::getNameUserChild).collect(Collectors.toList());
         return res;
    }

    public Page<TaskDTO> getAllManagedTasks(Pageable pageable) {
        return taskRepository.findAll(pageable).map(TaskDTO::new);
    }



    public Task createPeriodicTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setNameTask(taskDTO.getNameTask());
        task.setTypeTask(taskDTO.getTypeTask());
        task.setDescription(taskDTO.getDescription());
        LocalDateTime hoy = LocalDateTime.now(); 
        if(taskDTO.getStartDate().isAfter(hoy) && taskDTO.getEndDate().isAfter(hoy) && taskDTO.getEndDate().isAfter(taskDTO.getStartDate())){
        task.setStartDate(taskDTO.getStartDate());
        task.setEndDate(taskDTO.getEndDate());
        if (taskDTO.getUserChilds() != null) {
            Set<UserChild> userChilds = taskDTO.getUserChilds().stream()
                .map(userChildRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
            task.setUserChilds(userChilds);
        }

        SecurityUtils.getCurrentUserLogin()
        .flatMap(userRepository::findOneByLogin)
        .ifPresent(user -> {
            Set<Task> tasks = user.getTasks();
            tasks.add(task);
            user.setTasks(tasks);
            userRepository.save(user); 
        });        
    }
        taskRepository.save(task);
        return task;
    }



}
