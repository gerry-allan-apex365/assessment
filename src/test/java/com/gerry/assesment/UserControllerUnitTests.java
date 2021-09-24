package com.gerry.assesment;

import com.google.gson.*;
import com.gerry.assesment.controller.UserController;
import com.gerry.assesment.model.entity.User;
import com.gerry.assesment.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.reflect.Type;
import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerUnitTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class,
                    (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> LocalDateTime.parse(json.getAsJsonPrimitive().getAsString()))
            .registerTypeAdapter(LocalDate.class,
                    (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) -> LocalDate.parse(json.getAsJsonPrimitive().getAsString()))
            .create();

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
    public void getAllUsers() throws Exception {
        Mockito.when(userService.getAllUsers()).thenReturn(users);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/get/all");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        ArrayList<User> resultUserArrayList = gson.fromJson(result.getResponse().getContentAsString(), ArrayList.class);
        assertEquals(users.size(), resultUserArrayList.size());
    }

    @Test
    public void getAllUsersByFirstName() throws Exception {
        Mockito.when(userService.getAllUsersByFirstName(Mockito.anyString())).thenReturn(users);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/user/get/first-name")
                .param("first-name", Mockito.anyString());

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        ArrayList<User> resultUserArrayList = gson.fromJson(result.getResponse().getContentAsString(), ArrayList.class);
        assertEquals(users.size(), resultUserArrayList.size());
    }

    @Test
    public void getAllUsersBySurname() throws Exception {
        Mockito.when(userService.getAllUsersBySurname(Mockito.anyString())).thenReturn(users);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/user/get/surname")
                .param("surname", Mockito.anyString());

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        ArrayList<User> resultUserArrayList = gson.fromJson(result.getResponse().getContentAsString(), ArrayList.class);
        assertEquals(users.size(), resultUserArrayList.size());
    }

    @Test
    public void getUserByID() throws Exception {
        Mockito.when(userService.getUserById(Mockito.anyString())).thenReturn(user1);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/user/get/id/6781b7fb-b758-43cb-ad80-f707a588df8f");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        User user = gson.fromJson(result.getResponse().getContentAsString(), User.class);
        assertEquals(user1, user);
    }

    @Test
    public void createUser() throws Exception {
        Mockito.when(userService.createUser(
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.any(LocalDate.class),
                Mockito.anyString()
        )).thenReturn(user1);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/create")
                .param("title", "")
                .param("first-name", "")
                .param("surname", "")
                .param("date-of-birth", "1996-10-22")
                .param("job-title", "");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        User user = gson.fromJson(result.getResponse().getContentAsString(), User.class);
        assertEquals(user1, user);
    }

    @Test
    public void updateUser() throws Exception {
        Mockito.when(userService.updateUser(Mockito.any(User.class))).thenReturn(user1);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user1.toString());

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        User user = gson.fromJson(result.getResponse().getContentAsString(), User.class);
        assertEquals(user1, user);
    }

    @Test
    public void updateUserTitle() throws Exception {
        Mockito.when(userService.updateUserTitle(Mockito.anyString(), Mockito.anyString())).thenReturn(user1);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/6781b7fb-b758-43cb-ad80-f707a588df8f/update/title")
                .param("title", "");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        User user = gson.fromJson(result.getResponse().getContentAsString(), User.class);
        assertEquals(user1, user);
    }

    @Test
    public void updateUserFirstName() throws Exception {
        Mockito.when(userService.updateUserFirstName(Mockito.anyString(), Mockito.anyString())).thenReturn(user1);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/6781b7fb-b758-43cb-ad80-f707a588df8f/update/first-name")
                .param("first-name", "");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        User user = gson.fromJson(result.getResponse().getContentAsString(), User.class);
        assertEquals(user1, user);
    }

    @Test
    public void updateUserSurname() throws Exception {
        Mockito.when(userService.updateUserSurname(Mockito.anyString(), Mockito.anyString())).thenReturn(user1);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/6781b7fb-b758-43cb-ad80-f707a588df8f/update/surname")
                .param("surname", "");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        User user = gson.fromJson(result.getResponse().getContentAsString(), User.class);
        assertEquals(user1, user);
    }

    @Test
    public void updateUserDateOfBirth() throws Exception {
        Mockito.when(userService.updateUserDateOfBirth(Mockito.anyString(), Mockito.any(LocalDate.class))).thenReturn(user1);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/6781b7fb-b758-43cb-ad80-f707a588df8f/update/date-of-birth")
                .param("date-of-birth", "1996-10-22");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        User user = gson.fromJson(result.getResponse().getContentAsString(), User.class);
        assertEquals(user1, user);
    }

    @Test
    public void updateUserJobTitle() throws Exception {
        Mockito.when(userService.updateUserJobTitle(Mockito.anyString(), Mockito.anyString())).thenReturn(user1);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/6781b7fb-b758-43cb-ad80-f707a588df8f/update/job-title")
                .param("job-title", "");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        User user = gson.fromJson(result.getResponse().getContentAsString(), User.class);
        assertEquals(user1, user);
    }

    @Test
    public void deleteUser() throws Exception {
        Mockito.when(userService.deleteUser(Mockito.anyString())).thenReturn(user1);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/user/delete/6781b7fb-b758-43cb-ad80-f707a588df8f");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        User user = gson.fromJson(result.getResponse().getContentAsString(), User.class);
        assertEquals(user1, user);
    }

}
