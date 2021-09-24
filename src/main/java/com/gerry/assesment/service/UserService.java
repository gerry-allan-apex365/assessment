package com.gerry.assesment.service;

import com.gerry.assesment.model.entity.User;
import com.gerry.assesment.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> getAllUsers() { return userRepository.findAll(); }

    public Iterable<User> getAllUsersByFirstName( String firstName ) {
        return userRepository.findAllByFirstName(firstName);
    }

    public Iterable<User> getAllUsersBySurname( String surname ) {
        return userRepository.findAllBySurname(surname);
    }

    public User getUserById( String id ) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseGet(User::new);
    }

    public User createUser(
            String title,
            String firstName,
            String surname,
            LocalDate dateOfBirth,
            String jobTitle ) {
        return userRepository.save(new User(
                UUID.randomUUID().toString(), title, firstName, surname,
                dateOfBirth, jobTitle, LocalDateTime.now()
        ));
    }

    public User updateUser( User user ) {
        return userRepository.save(user);
    }

    public User updateUserTitle(
            String id,
            String title ) {
        User user = this.getUserById(id);
        if (user.getId() == null || user.getId().equals("")) {
            return user;
        } else {
            user.setTitle(title);
            return userRepository.save(user);
        }
    }

    public User updateUserFirstName(
            String id,
            String firstName ) {
        User user = this.getUserById(id);
        if (user.getId() == null || user.getId().equals("")) {
            return user;
        } else {
            user.setFirstName(firstName);
            return userRepository.save(user);
        }
    }

    public User updateUserSurname(
            String id,
            String surname ) {
        User user = this.getUserById(id);
        if (user.getId() == null || user.getId().equals("")) {
            return user;
        } else {
            user.setSurname(surname);
            return userRepository.save(user);
        }
    }

    public User updateUserDateOfBirth(
            String id,
            LocalDate dateOfBirth ) {
        User user = this.getUserById(id);
        if (user.getId() == null || user.getId().equals("")) {
            return user;
        } else {
            user.setDateOfBirth(dateOfBirth);
            return userRepository.save(user);
        }
    }

    public User updateUserJobTitle(
            String id,
            String jobTitle ) {
        User user = this.getUserById(id);
        if (user.getId() == null || user.getId().equals("")) {
            return user;
        } else {
            user.setJobTitle(jobTitle);
            return userRepository.save(user);
        }
    }

    public User deleteUser(
            String id
    ) {
        User user = this.getUserById(id);
        if (!Objects.equals(user.getId(), "")) {
            userRepository.delete(user);
        }
        return user;
    }
}
