package org.example.serviceImpl;

import com.spring.services.model.User;
import org.example.entity.BlogEntity;
import org.example.entity.UserEntity;
import org.example.exception.ErrorHandler;
import org.example.exception.UserBlogException;
import org.example.repository.BlogRepository;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BlogRepository blogRepository;



    private UserEntity user1;
    private UserEntity user2;

    private UserEntity user3;

    private BlogEntity blog1;
    private BlogEntity blog2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user1 = new UserEntity(1,"Snehal","Patil","Sp@gmail.com",702021,"Kolhapur","N");
        user2 = new UserEntity(2,"Gaurav","Patil","Gp@gmail.com",9090,"Pune","N");
        user3 = new UserEntity(3,"null","null","null",0,"null","n");
        blog1 = new BlogEntity(1,"Pavsala","Sundar Pavsala","Snehal Patil","N",user1);
        blog2 = new BlogEntity(2,"Unhala","Sundar Unhala","Gaurav Patil","N",user1);
    }

    @Test
    void getAllUserTest() {
        List<UserEntity> userList=new ArrayList<UserEntity>();
        userList.add(user1);
        userList.add(user2);
        when(userRepository.findAll()).thenReturn(userList);
        ResponseEntity<List<User>> user = userService.getAllUser();
        assertNotNull(user);
        assertNotNull(user.getBody().size());

    }

    @Test
    void getElementByIdTest() {
        UserEntity user = new UserEntity();
        user = user1;

        when(userRepository.findById(1)).thenReturn(Optional.of((new UserEntity(1, "Rohit", "Rajure", "RR@gmail.com", 702021, "Latur", "N"))));
        ResponseEntity<User> users = userService.getElementById(1);
        assertEquals("Rohit",users.getBody().getFirstName());
        assertEquals("Rajure",users.getBody().getLastname());
        assertEquals("RR@gmail.com",users.getBody().getEmail());
        assertEquals(702021,users.getBody().getContact());
    }
    @Test
    void deleteUserTest() {
        when(userRepository.findById(1)).thenReturn(Optional.of(new UserEntity(1, "Rohit", "Rajure", "RR@gmail.com", 702021, "Latur", "N")));
        List<BlogEntity> blogs = new ArrayList<>();
        blogs.add(blog1);
        blogs.add(blog2);
        when(blogRepository.findAll()).thenReturn(blogs);
        ResponseEntity<Void> Output = userService.deleteUser(1);
        assertNotNull(Output);
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setUserId(user1.getUserId());
        user.setFirstName("Snehal");
        user.setLastname("Patil");
        user.setEmail("Sp@gmail.com");
        user.setCity("pune");
        user.setContact(784180);
        when(userRepository.findById(1)).thenReturn(Optional.of((new UserEntity(1, "Snehal", "Patil", "Sp@gmail.com", 702021, "Kolhapur", "N"))));
        ResponseEntity<Void> Output=userService.updateUser(user);
        assertNotNull(Output);
        assertEquals( HttpStatus.OK,Output.getStatusCode());
    }

    @Test
    void postUserTest() {
        User user = new User();
        user.setUserId(user1.getUserId());
        user.setFirstName(user1.getFirstName());
        user.setLastname(user1.getLastname());
        user.setEmail(user1.getEmail());
        user.setCity(user1.getCity());
        user.setContact(user1.getContact());
        ResponseEntity<Void> User = userService.postUser(user);
        assertEquals(HttpStatus.OK,User.getStatusCode());

    }

    @Test
    void getElementByIdUserNotPresentTest(){
//        UserEntity user = new UserEntity();
//        user=user1;
        when(userRepository.findById(2)).thenThrow(new ErrorHandler());
//       ResponseEntity<User> Output = userService.getElementById(2);
//       assertNotNull(Output);
//       assertEquals(404,Output.getStatusCodeValue());
        assertThrows(ErrorHandler.class,()-> userService.getElementById(2));
    }

    @Test
    void getAllUserNullUserTest(){
        List<UserEntity> userList=new ArrayList<UserEntity>();
        userList.add(user1);
        userList.add(user3);
        when(userRepository.findAll()).thenReturn(userList);
        ResponseEntity<List<User>> user = userService.getAllUser();
        assertNotNull(user.getBody().size());
        assertNotNull(user);
        assertEquals("null",user.getBody().get(1).getFirstName());
        assertEquals(3,user.getBody().get(1).getUserId());

    }

    @Test
    void deleteUserNotPresent(){
        when(blogRepository.findAll()).thenThrow(ErrorHandler.class);
        assertThrows(ErrorHandler.class,()-> userService.deleteUser(10));
//        when(blogRepository.findAll()).thenThrow(ErrorHandler.class);
//        ResponseEntity<Void> Output = userService.deleteUser(2);
//        assertNotNull(Output);
//        assertEquals(HttpStatus.NOT_FOUND,Output);
    }

    @Test
    void updateNonExistingUser(){
        User user = new User();
        user.setUserId(user1.getUserId());
        user.setFirstName("Snehal");
        user.setLastname("Patil");
        user.setEmail("Sp@gmail.com");
        user.setCity("pune");
        user.setContact(784180);
        when(userRepository.findById(1)).thenThrow(ErrorHandler.class);
//        ResponseEntity<Void> Output=userService.updateUser(user);
//        assertNotNull(Output);
//        assertEquals(HttpStatus.NOT_FOUND,Output);
        assertThrows(ErrorHandler.class,()->userService.updateUser(user),"user is not present to update");

    }
}