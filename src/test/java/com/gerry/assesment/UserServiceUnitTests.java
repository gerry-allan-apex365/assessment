package com.gerry.assesment;

import com.gerry.assesment.model.entity.User;
import com.gerry.assesment.repository.UserRepository;
import com.gerry.assesment.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserService.class)
public class UserServiceUnitTests {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    @InjectMocks
    private UserService userService;

    User user1 = new User(
            "6781b7fb-b758-43cb-ad80-f707a588df8f", "Mr", "Gerry", "Allan", LocalDate.of(1996, 10, 22),
            "Software Developer", LocalDateTime.of(LocalDate.of(2020, 1, 1), LocalTime.of(0,0))
    );

    User user2 = new User(
            "513f8d5f-848a-4f2b-8db3-0e2184f8b314", "Miss", "Margaret", "Thatcher", LocalDate.of(1925, 10, 13),
            "Politician", LocalDateTime.of(LocalDate.of(2020, 1, 1), LocalTime.of(0,0))
    );

    List<User> users = Arrays.asList(user1, user2);

    @Test
    public void getAllUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(users);
        assertEquals(users, userService.getAllUsers());
    }

    @Test
    public void getAllUsersByFirstName() {
        Mockito.when(userRepository.findAllByFirstName(Mockito.anyString())).thenReturn(users);
        assertEquals(users, userService.getAllUsersByFirstName(Mockito.anyString()));
    }

    @Test
    public void getAllUsersBySurname() {
        Mockito.when(userRepository.findAllBySurname(Mockito.anyString())).thenReturn(users);
        assertEquals(users, userService.getAllUsersBySurname(Mockito.anyString()));
    }

    @Test
    public void getUserByID() {
        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.ofNullable(user1));
        assertEquals(user1, userService.getUserById(Mockito.anyString()));
    }

    @Test
    public void createUser() {
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user1);
        User savedUser = userService.createUser(
                "Mr",
                "Gerry",
                "Allan",
                LocalDate.of(1996, 10, 22),
                "Software Developer"
        );
        assertEquals(user1, savedUser);
    }

    @Test
    public void updateUser() {
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user1);
        User updatedUser = userService.updateUser(Mockito.mock(User.class));
        assertEquals(user1, updatedUser);
    }

    @Test
    public void updateUserTitle_present() {
        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.ofNullable(user1));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user1);
        User updatedUser = userService.updateUserTitle("", "");
        assertEquals(user1, updatedUser);
    }

    @Test
    public void updateUserTitle_absent() {
        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(new User()));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user1);
        User updatedUser = userService.updateUserTitle("", "");
        assertNull(updatedUser.getId());
    }

    @Test
    public void updateUserFirstName_present() {
        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.ofNullable(user1));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user1);
        User updatedUser = userService.updateUserFirstName("", "");
        assertEquals(user1, updatedUser);
    }

    @Test
    public void updateUserFirstName_absent() {
        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(new User()));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user1);
        User updatedUser = userService.updateUserFirstName("", "");
        assertNull(updatedUser.getId());
    }

    @Test
    public void updateUserSurname_present() {
        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.ofNullable(user1));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user1);
        User updatedUser = userService.updateUserSurname("", "");
        assertEquals(user1, updatedUser);
    }

    @Test
    public void updateUserSurname_absent() {
        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(new User()));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user1);
        User updatedUser = userService.updateUserSurname("", "");
        assertNull(updatedUser.getId());
    }

    @Test
    public void updateUserDateOfBirth_present() {
        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.ofNullable(user1));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user1);
        User updatedUser = userService.updateUserDateOfBirth("", LocalDate.now());
        assertEquals(user1, updatedUser);
    }

    @Test
    public void updateUserDateOfBirth_absent() {
        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(new User()));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user1);
        User updatedUser = userService.updateUserDateOfBirth("", LocalDate.now());
        assertNull(updatedUser.getId());
    }

    @Test
    public void updateUserJobTitle_present() {
        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.ofNullable(user1));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user1);
        User updatedUser = userService.updateUserJobTitle("", "");
        assertEquals(user1, updatedUser);
    }

    @Test
    public void updateUserJobTitle_absent() {
        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(new User()));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user1);
        User updatedUser = userService.updateUserJobTitle("", "");
        assertNull(updatedUser.getId());
    }

    @Test
    public void deleteUser() {
        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(new User()));
        User updatedUser = userService.deleteUser("");
        assertNull(updatedUser.getId());
    }

}
