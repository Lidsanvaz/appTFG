package com.example.miapp.web.rest;

import com.example.miapp.config.Constants;
import com.example.miapp.domain.Family;
import com.example.miapp.repository.FamilyRepository;
import com.example.miapp.security.AuthoritiesConstants;
import com.example.miapp.service.FamilyService;
import com.example.miapp.service.dto.FamilyDTO;
import com.example.miapp.web.rest.errors.BadRequestAlertException;
import com.example.miapp.web.rest.errors.EmailAlreadyUsedException;
import com.example.miapp.web.rest.errors.LoginAlreadyUsedException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

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

   /*      if (familyDTO.getId() != null) {
            throw new BadRequestAlertException("A new family cannot already have an ID", "userManagement", "idexists");
            // Lowercase the user login before comparing with database
          } else { */
            Family newFamily = familyService.createFamily(familyDTO);
            return ResponseEntity.created(new URI("/api/family/" + newFamily.getNameFamily()))
                .headers(HeaderUtil.createAlert(applicationName,  "userManagement.created", newFamily.getNameFamily()))
                .body(newFamily);
        
    }

 

    /**
     * {@code GET /users} : get all users.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all users.
     */
 /*     @GetMapping("/family")
    public ResponseEntity<List<FamilyDTO>> getAllFamilies(@RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder, Pageable pageable) {
        final Page<FamilyDTO> page = familyService.getAllManagedFamilies(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    } */
 
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
    

    /**
     * {@code DELETE /users/:login} : delete the "login" User.
     *
     * @param login the login of the user to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
  /*   @DeleteMapping("/users/{login:" + Constants.LOGIN_REGEX + "}")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteUser(@PathVariable String login) {
        log.debug("REST request to delete User: {}", login);
        userService.deleteUser(login);
        return ResponseEntity.noContent().headers(HeaderUtil.createAlert(applicationName,  "userManagement.deleted", login)).build();
    } */
}