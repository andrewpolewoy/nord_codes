package com.nord.shorter.controller.rest;

import com.nord.shorter.model.Shorter;
import com.nord.shorter.model.Url;
import com.nord.shorter.model.User;
import com.nord.shorter.service.CodeGenerator;
import com.nord.shorter.service.api.IShortService;
import com.nord.shorter.service.api.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.net.URLDecoder;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@PreAuthorize("hasAnyAuthority('USER')")
@RequestMapping("/user")
public class ShorterControllerRest {

    private IShortService shortService;
    private IUserService userService;
    private CodeGenerator codeGenerator;
    private UrlValidator urlValidator;
    @Value("${shorter.length}")
    private Integer shorterLength;

    Logger logger = LoggerFactory.getLogger(ShorterControllerRest.class.getSimpleName());

    @Autowired
    public ShorterControllerRest(IShortService service, IUserService userService) {
        this.codeGenerator = new CodeGenerator();
        this.urlValidator = new UrlValidator(new String[]{"http", "https"});
        this.shortService = service;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Shorter>> readAllByUserId(@PathVariable(name = "id") Long userId) {
        List<Shorter> shorter = shortService.findByUserId(userId);
        return new ResponseEntity<>(shorter, HttpStatus.OK);
    }

//    @PostMapping("/{id}")
//    public ResponseEntity<Shorter> createShortUrl(@RequestBody Url shorter, @PathVariable(name = "id") Long id) {
//
//        String hash = codeGenerator.generate(shorterLength);
//        log.info("IN ShorterControllerRest createShortUrl {}", hash);
//        User user = userService.findUserById(id);
//
//        if (shorter != null && urlValidator.isValid(shorter.getOriginalUrl())) {
//            String shorterString = URLDecoder.decode(shorter.getOriginalUrl());
//            log.info("IN ShorterControllerRest createShortUrl {}", shorterString);
//            Shorter newShorter = new Shorter();
//            newShorter.setUser(user);
//            newShorter.setOriginalUrl(shorter.getOriginalUrl());
//            newShorter.setHash(hash);
//            newShorter.setCreatedAt(LocalDate.now());
//            return new ResponseEntity<>(shortService.save(newShorter), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    @PostMapping
    public ResponseEntity<Shorter> createShort(Url shorter, Principal id) {

        String hash = codeGenerator.generate(shorterLength);
        log.info("IN ShorterControllerRest createShortUrl {}", hash);
        User user = userService.findByUsername(id.getName());

        if (shorter != null && urlValidator.isValid(shorter.getOriginalUrl())) {
            String shorterString = URLDecoder.decode(shorter.getOriginalUrl());
            log.info("IN ShorterControllerRest createShortUrl {}", shorterString);
            Shorter newShorter = new Shorter();
            newShorter.setUser(user);
            newShorter.setOriginalUrl(shorter.getOriginalUrl());
            newShorter.setHash(hash);
            newShorter.setCreatedAt(LocalDate.now());
            return new ResponseEntity<>(shortService.save(newShorter), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        final boolean deleted = shortService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}