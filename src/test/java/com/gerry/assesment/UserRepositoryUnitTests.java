package com.gerry.assesment;

import com.gerry.assesment.model.entity.User;
import com.gerry.assesment.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryUnitTests {
    @Autowired
    UserRepository userRepository;

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
    public void findAllByFirstName() {
        userRepository.saveAll(users);
        List<User> returnedUsers = new ArrayList<>();
        userRepository.findAllByFirstName("Gerry").forEach(returnedUsers::add);
        assertEquals(users.get(0), returnedUsers.get(0));
    }

    @Test
    public void findAllBySurname() {
        userRepository.saveAll(users);
        List<User> returnedUsers = new ArrayList<>();
        userRepository.findAllBySurname("Thatcher").forEach(returnedUsers::add);
        assertEquals(users.get(1), returnedUsers.get(0));
    }
}
