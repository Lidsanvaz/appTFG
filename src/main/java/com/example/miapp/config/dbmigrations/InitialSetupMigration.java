package com.example.miapp.config.dbmigrations;

import com.example.miapp.domain.Authority;
import com.example.miapp.domain.User;
import com.example.miapp.domain.Family;
import com.example.miapp.domain.UserChild;
import com.example.miapp.domain.Task;
import com.example.miapp.security.AuthoritiesConstants;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.Instant;
import java.time.Month;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet;



/**
 * Creates the initial database setup.
 */
@ChangeLog(order = "001")
public class InitialSetupMigration {

    @ChangeSet(order = "01", author = "initiator", id = "01-addAuthorities")
    public void addAuthorities(MongoTemplate mongoTemplate) {
        Authority adminAuthority = new Authority();
        adminAuthority.setName(AuthoritiesConstants.ADMIN);
        Authority userAuthority = new Authority();
        userAuthority.setName(AuthoritiesConstants.USER);
        mongoTemplate.save(adminAuthority);
        mongoTemplate.save(userAuthority);
    }

    @ChangeSet(order = "02", author = "initiator", id = "02-addUsers")
    public void addUsers(MongoTemplate mongoTemplate) {

        Authority adminAuthority = new Authority();
        adminAuthority.setName(AuthoritiesConstants.ADMIN);
        Authority userAuthority = new Authority();
        userAuthority.setName(AuthoritiesConstants.USER);

        Family family = new Family();
        family.setNameFamily("Familia López");


        UserChild userChild = new UserChild();
        userChild.setNameUserChild("Carlota");
        userChild.setBornDate(LocalDate.of(2011,Month.MAY,23));
        UserChild userChild1 = new UserChild();
        userChild1.setNameUserChild("Ángela");
        userChild1.setBornDate(LocalDate.of(2016,Month.APRIL,12));
        UserChild userChild2 = new UserChild();
        userChild2.setNameUserChild("Jaime");
        userChild2.setBornDate(LocalDate.of(2014,Month.FEBRUARY,10));


        Task task = new Task();
        task.setNameTask("Dentista");
        task.setDescription("En Triana. Llevar coche");
        Set<String> setTypeTasks = new HashSet<>();
        setTypeTasks.add("Médico");
        task.setTypeTasks(setTypeTasks);
        task.setStartDate(LocalDateTime.of(2019,Month.NOVEMBER,23,17,30));
        task.setEndDate(LocalDateTime.of(2019,Month.NOVEMBER,23,19,00));

        Task task1 = new Task();
        task1.setNameTask("Cumpleaños Alberto");
        task1.setDescription("Junto al parque de bolas");
        Set<String> setTypeTasks1 = new HashSet<>();
        setTypeTasks1.add("Cumpleaños");
        task1.setTypeTasks(setTypeTasks1);
        task1.setStartDate(LocalDateTime.of(2019,Month.NOVEMBER,30,11,00));
        task1.setEndDate(LocalDateTime.of(2019,Month.NOVEMBER,30,18,20));

        Task task2 = new Task();
        task2.setNameTask("Clases de piano");
        task2.setDescription("Llevar carnet de inscripción");
        Set<String> setTypeTasks2 = new HashSet<>();
        setTypeTasks2.add("Extraescolares");
        task2.setTypeTasks(setTypeTasks2);
        Set<String> weekDays2 = new HashSet<>();
        weekDays2.add("Lunes");
        weekDays2.add("Miércoles");
        task2.setWeekDays(weekDays2);
        task2.setStartDate(LocalDateTime.of(2019,Month.NOVEMBER,15,17,00));
        task2.setEndDate(LocalDateTime.of(2020,Month.MARCH,30,19,30));

        User systemUser = new User();
        systemUser.setId("user-0");
        systemUser.setLogin("system");
        systemUser.setPassword("$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG");
        systemUser.setFirstName("");
        systemUser.setLastName("System");
        systemUser.setEmail("system@localhost");
        systemUser.setLangKey("es");
        systemUser.setCreatedBy(systemUser.getLogin());
        systemUser.setCreatedDate(Instant.now());
        systemUser.getAuthorities().add(adminAuthority);
        systemUser.getAuthorities().add(userAuthority);
        mongoTemplate.save(systemUser);

        User anonymousUser = new User();
        anonymousUser.setId("user-1");
        anonymousUser.setLogin("anonymoususer");
        anonymousUser.setPassword("$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO");
        anonymousUser.setFirstName("Anonymous");
        anonymousUser.setLastName("User");
        anonymousUser.setEmail("anonymous@localhost");
        anonymousUser.setLangKey("es");
        anonymousUser.setCreatedBy(systemUser.getLogin());
        anonymousUser.setCreatedDate(Instant.now());
        mongoTemplate.save(anonymousUser);

        User adminUser = new User();
        adminUser.setId("user-2");
        adminUser.setLogin("admin");
        adminUser.setPassword("$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC");
        adminUser.setFirstName("admin");
        adminUser.setLastName("Administrator");
        adminUser.setEmail("admin@localhost");
        adminUser.setLangKey("es");
        adminUser.setCreatedBy(systemUser.getLogin());
        adminUser.setCreatedDate(Instant.now());
        adminUser.getAuthorities().add(adminAuthority);
        adminUser.getAuthorities().add(userAuthority);
        mongoTemplate.save(adminUser);

        User userUser = new User();
        userUser.setId("user-3");
        userUser.setLogin("user");
        userUser.setPassword("$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K");
        userUser.setFirstName("");
        userUser.setLastName("User");
        userUser.setEmail("user@localhost");
        userUser.setLangKey("es");
        userUser.setCreatedBy(systemUser.getLogin());
        userUser.setCreatedDate(Instant.now());
        userUser.getAuthorities().add(userAuthority);
        userUser.getFamilies().add(family);
        userUser.getUserChilds().add(userChild2);
        userUser.getTasks().add(task1);
        
        mongoTemplate.save(userUser);


        User userUser2 = new User();
        userUser2.setId("user-4");
        userUser2.setLogin("francisco1970");
        userUser2.setPassword("$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K");
        userUser2.setFirstName("Francisco");
        userUser2.setLastName("López");
        userUser2.setEmail("francis@localhost");
        userUser2.setLangKey("es");
        userUser2.setCreatedBy(systemUser.getLogin());
        userUser2.setCreatedDate(Instant.now());
        userUser2.getAuthorities().add(userAuthority);
        userUser2.getFamilies().add(family);
        userUser2.getUserChilds().add(userChild);
        userUser2.getUserChilds().add(userChild1);
        userUser2.getTasks().add(task);
        userUser2.getTasks().add(task2);

        mongoTemplate.save(userUser2);

    }

    @ChangeSet(order = "03", author = "initiator", id = "03-addFamilies")
    public void addFamilies(MongoTemplate mongoTemplate) {
        Family family = new Family();
        family.setNameFamily("Familia López");
        Family family1 = new Family();
        family1.setNameFamily("Familia Rodríguez");
        mongoTemplate.save(family);
        mongoTemplate.save(family1);
    }


    @ChangeSet(order = "04", author = "initiator", id = "04-addUserChilds")
    public void addUserChilds(MongoTemplate mongoTemplate) {
        UserChild userChild = new UserChild();
        userChild.setNameUserChild("Carlota");
        userChild.setBornDate(LocalDate.of(2011,Month.MAY,23));
        UserChild userChild1 = new UserChild();
        userChild1.setNameUserChild("Ángela");
        userChild1.setBornDate(LocalDate.of(2016,Month.APRIL,12));
        mongoTemplate.save(userChild);
        mongoTemplate.save(userChild1);
    }

    @ChangeSet(order = "05", author = "initiator", id = "05-addTasks")
    public void addTasks(MongoTemplate mongoTemplate) {

        Authority adminAuthority = new Authority();
        adminAuthority.setName(AuthoritiesConstants.ADMIN);
        Authority userAuthority = new Authority();
        userAuthority.setName(AuthoritiesConstants.USER);

        Family family = new Family();
        family.setNameFamily("Familia López");

        UserChild userChild = new UserChild();
        userChild.setNameUserChild("Carlota");
        userChild.setBornDate(LocalDate.of(2011,Month.MAY,23));
        UserChild userChild1 = new UserChild();
        userChild1.setNameUserChild("Ángela");
        userChild1.setBornDate(LocalDate.of(2016,Month.APRIL,12));
        UserChild userChild2 = new UserChild();
        userChild2.setNameUserChild("Jaime");
        userChild2.setBornDate(LocalDate.of(2014,Month.FEBRUARY,10));


        User systemUser = new User();
        systemUser.setId("user-0");
        systemUser.setLogin("system");
        systemUser.setPassword("$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG");
        systemUser.setFirstName("");
        systemUser.setLastName("System");
        systemUser.setEmail("system@localhost");
        systemUser.setLangKey("es");
        systemUser.setCreatedBy(systemUser.getLogin());
        systemUser.setCreatedDate(Instant.now());
        systemUser.getAuthorities().add(adminAuthority);
        systemUser.getAuthorities().add(userAuthority);
        mongoTemplate.save(systemUser);

        User userUser = new User();
        userUser.setId("user-3");
        userUser.setLogin("user");
        userUser.setPassword("$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K");
        userUser.setFirstName("");
        userUser.setLastName("User");
        userUser.setEmail("user@localhost");
        userUser.setLangKey("es");
        userUser.setCreatedBy(systemUser.getLogin());
        userUser.setCreatedDate(Instant.now());
        userUser.getAuthorities().add(userAuthority);
        userUser.getFamilies().add(family);
        userUser.getUserChilds().add(userChild2);
/*         userUser.getTasks().add(task1);
 */        


        User userUser2 = new User();
        userUser2.setId("user-4");
        userUser2.setLogin("francisco1970");
        userUser2.setPassword("$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K");
        userUser2.setFirstName("Francisco");
        userUser2.setLastName("López");
        userUser2.setEmail("francis@localhost");
        userUser2.setLangKey("es");
        userUser2.setCreatedBy(systemUser.getLogin());
        userUser2.setCreatedDate(Instant.now());
        userUser2.getAuthorities().add(userAuthority);
        userUser2.getFamilies().add(family);
        userUser2.getUserChilds().add(userChild);
        userUser2.getUserChilds().add(userChild1);
/*         userUser2.getTasks().add(task);
        userUser2.getTasks().add(task2); */


 


        
     

        Task task = new Task();
        task.setNameTask("Dentista");
        task.setDescription("En Triana. Llevar coche");
        Set<String> setTypeTasks = new HashSet<>();
        setTypeTasks.add("Médico");
        task.setTypeTasks(setTypeTasks);
        task.setStartDate(LocalDateTime.of(2019,Month.NOVEMBER,23,17,30));
        task.setEndDate(LocalDateTime.of(2019,Month.NOVEMBER,23,19,00));
        task.getFamilies().add(family);
        task.getUserChilds().add(userChild);
        task.getUsers().add(userUser2);
        
        Task task1 = new Task();
        task1.setNameTask("Cumpleaños Alberto");
        task1.setDescription("Junto al parque de bolas");
        Set<String> setTypeTasks1 = new HashSet<>();
        setTypeTasks1.add("Cumpleaños");
        task1.setTypeTasks(setTypeTasks1);
        task1.setStartDate(LocalDateTime.of(2019,Month.NOVEMBER,30,11,00));
        task1.setEndDate(LocalDateTime.of(2019,Month.NOVEMBER,30,18,20));
        task.getFamilies().add(family);
        task.getUserChilds().add(userChild2);
        task.getUsers().add(userUser);



        Task task2 = new Task();
        task2.setNameTask("Clases de piano");
        task2.setDescription("Llevar carnet de inscripción");
        Set<String> setTypeTasks2 = new HashSet<>();
        setTypeTasks2.add("Extraescolares");
        task2.setTypeTasks(setTypeTasks2);
        Set<String> weekDays2 = new HashSet<>();
        weekDays2.add("Lunes");
        weekDays2.add("Miércoles");
        task2.setWeekDays(weekDays2);
        task2.setStartDate(LocalDateTime.of(2019,Month.NOVEMBER,15,17,00));
        task2.setEndDate(LocalDateTime.of(2020,Month.MARCH,30,19,30));
        task.getFamilies().add(family);
        task.getUserChilds().add(userChild1);
        task.getUsers().add(userUser2);

        mongoTemplate.save(task);
        mongoTemplate.save(task1);
        mongoTemplate.save(task2);
    }
}
