package com.example.miapp.web.rest;
import com.example.miapp.domain.UserChild;
import com.example.miapp.repository.UserChildRepository;
import com.example.miapp.service.UserChildService;
import com.example.miapp.service.dto.UserChildDTO;
import com.example.miapp.security.AuthoritiesConstants;
import org.springframework.security.access.prepost.PreAuthorize;
import io.github.jhipster.web.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class UserChildResource {

    private final Logger log = LoggerFactory.getLogger(UserChildResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserChildService userChildService;

    private final UserChildRepository userChildRepository;


    public UserChildResource(UserChildService userChildService, UserChildRepository userChildRepository) {

        this.userChildService = userChildService;
        this.userChildRepository = userChildRepository;
    }

    
    @PostMapping("/userChild")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<UserChild> createUserChild(@Valid @RequestBody UserChildDTO userChildDTO) throws URISyntaxException {
        log.debug("REST request to save UserChild : {}", userChildDTO);
        UserChild newUserChild = userChildService.createUserChild(userChildDTO);
            return ResponseEntity.created(new URI("/api/userChild/" + newUserChild.getNameUserChild()))
                .headers(HeaderUtil.createAlert(applicationName,  "userManagement.created", newUserChild.getNameUserChild()))
                .body(newUserChild);
        
    }

 
   
 
    /**
     * Gets a list of all roles.
     * @return a string list of all roles.
     */
 /*    @GetMapping("/families/users")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public List<String> getUsers() {
        return familyService.getUsers();
    }
 */
    

}