package com.gerry.assesment.controller;

import com.gerry.assesment.model.entity.User;
import com.gerry.assesment.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @GetMapping("/get/all")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/get/first-name")
    public ResponseEntity<Iterable<User>> getAllUsersByFirstName(
            @RequestParam("first-name") String firstName
    ) {
        return ResponseEntity.ok(userService.getAllUsersByFirstName(firstName));
    }

    @GetMapping("/get/surname")
    public ResponseEntity<Iterable<User>> getAllUsersBySurname(
            @RequestParam("surname") String surname
    ) {
        return ResponseEntity.ok(userService.getAllUsersBySurname(surname));
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<User> getUserByID(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(
            @RequestParam("title") String title,
            @RequestParam("first-name") String firstName,
            @RequestParam("surname") String surname,
            @RequestParam("date-of-birth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,
            @RequestParam("job-title") String jobTitle
    ) {
        return ResponseEntity.ok(userService.createUser(
                title, firstName, surname, dateOfBirth, jobTitle));
    }

    @PostMapping("/update")
    public ResponseEntity<User> updateUser(
            @RequestBody User user
    ) {
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @PostMapping("/{id}/update/title")
    public ResponseEntity<User> updateUserTitle(
            @PathVariable String id,
            @RequestParam("title") String title
    ) {
        return ResponseEntity.ok(userService.updateUserTitle(id, title));
    }

    @PostMapping("/{id}/update/first-name")
    public ResponseEntity<User> updateUserFirstName(
            @PathVariable String id,
            @RequestParam("first-name") String firstName
    ) {
        return ResponseEntity.ok(userService.updateUserFirstName(id, firstName));
    }

    @PostMapping("/{id}/update/surname")
    public ResponseEntity<User> updateUserSurname(
            @PathVariable String id,
            @RequestParam("surname") String surname
    ) {
        return ResponseEntity.ok(userService.updateUserSurname(id, surname));
    }

    @PostMapping("/{id}/update/date-of-birth")
    public ResponseEntity<User> updateUserDateOfBirth(
            @PathVariable String id,
            @RequestParam("date-of-birth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth
    ) {
        return ResponseEntity.ok(userService.updateUserDateOfBirth(id, dateOfBirth));
    }

    @PostMapping("/{id}/update/job-title")
    public ResponseEntity<User> updateUserJobTitle(
            @PathVariable String id,
            @RequestParam("job-title") String jobTitle
    ) {
        return ResponseEntity.ok(userService.updateUserJobTitle(id, jobTitle));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

}
