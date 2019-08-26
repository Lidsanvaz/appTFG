package com.example.miapp.web.rest;
import com.example.miapp.domain.Family;
import com.example.miapp.repository.FamilyRepository;
import com.example.miapp.service.FamilyService;
import com.example.miapp.service.dto.FamilyDTO;
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

@RestController
@RequestMapping("/api")
public class FamilyResource {

    private final Logger log = LoggerFactory.getLogger(FamilyResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FamilyService familyService;

    private final FamilyRepository familyRepository;


    public FamilyResource(FamilyService familyService, FamilyRepository familyRepository) {

        this.familyService = familyService;
        this.familyRepository = familyRepository;
    }

    /**
     * {@code POST  /users}  : Creates a new user.
     * <p>
     * Creates a new user if the login and email are not already used, and sends an
     * mail with an activation link.
     * The user needs to be activated on creation.
     *
     * @param familyDTO the user to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new user, or with status {@code 400 (Bad Request)} if the login or email is already in use.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException {@code 400 (Bad Request)} if the login or email is already in use.
     */
    @PostMapping("/family")
    public ResponseEntity<Family> createFamily(@Valid @RequestBody FamilyDTO familyDTO) throws URISyntaxException {
        log.debug("REST request to save Family : {}", familyDTO);

            Family newFamily = familyService.createFamily(familyDTO);
            return ResponseEntity.created(new URI("/api/family/" + newFamily.getNameFamily()))
                .headers(HeaderUtil.createAlert(applicationName,  "userManagement.created", newFamily.getNameFamily()))
                .body(newFamily);
        
    }

 
}