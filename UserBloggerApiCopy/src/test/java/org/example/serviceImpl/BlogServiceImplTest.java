package org.example.serviceImpl;

import com.spring.services.model.Blog;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BlogServiceImplTest {

    @InjectMocks
    private BlogServiceImpl blogService;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private BlogRepository blogRepository;
    @Mock
    private UserRepository userRepository;
    private BlogEntity blog1;
    private BlogEntity blog2;
    private UserEntity user1;
    private UserEntity user2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user1 = new UserEntity(1, "Snehal", "Patil", "Sp@gmail.com", 702021, "Kolhapur", "N");
        user2 = new UserEntity(2, "Gaurav", "Patil", "Gp@gmail.com", 9090, "Pune", "N");

        blog1 = new BlogEntity(1, "Pavsala", "Sundar Pavsala", "Snehal Patil", "N", user1);
        blog2 = new BlogEntity(2, "Unhala", "Sundar Unhala", "Gaurav Patil", "N", user2);

    }


    @Test
    void getAllBlogTest() {
        List<BlogEntity> blogEntities = new ArrayList<BlogEntity>();
        blogEntities.add(blog1);
        blogEntities.add(blog2);
        when(blogRepository.findAll()).thenReturn(blogEntities);
        ResponseEntity<List<Blog>> blog = blogService.getAllBlog();
        assertNotNull(blogEntities);
        assertNotNull(blog);

    }

    @Test
    void getBlogByIdTest() {
        BlogEntity blog = new BlogEntity();
        blog = blog1;
        when(blogRepository.findById(1)).thenReturn(Optional.of(new BlogEntity(1, "Pavsala", "Sundar Pavsala", "Snehal Patil", "N", user1)));
        ResponseEntity<Blog> blogs = blogService.getBlogById(1);
        assertEquals("Pavsala", blogs.getBody().getBlogTitle());
        assertEquals("Sundar Pavsala", blogs.getBody().getBlogBody());
        assertEquals("Snehal Patil", blogs.getBody().getPublishBy());
        assertEquals(user1.getUserId(), blogs.getBody().getUserBlogId());
        assertEquals("N", blogs.getBody().getIsDeleted());
        //assertThrows(ErrorHandler.class,()->blogService.getBlogById(1));
    }

    @Test
    void deleteBlogByIdTest() {
        when(blogRepository.findById(1)).thenReturn(Optional.of(new BlogEntity(1, "Pavsala", "Sundar Pavsala", "Snehal Patil", "N", user1)));
        ResponseEntity<Void> Output = blogService.deleteBlogById(1);
        assertNotNull(Output);
    }

    @Test
    void postBlogTest() {
        Blog blog = new Blog();
        blog.setBlogId(blog1.getBlogId());
        blog.setBlogTitle(blog1.getBlogTitle());
        blog.setBlogBody(blog1.getBlogBody());
        blog.setUserBlogId(blog1.getUserEntity().getUserId());
        blog.setPublishBy(blog1.getPublishBy());
        blog.setIsDeleted(blog1.getIsDeleted());
        when(userRepository.findById(blog1.getUserEntity().getUserId())).thenReturn(Optional.of((new UserEntity(1, "Snehal", "Patil", "Sp@gmail.com", 702021, "Kolhapur", "N"))));
        //when(userRepository.findById(blog1.getBlogId())).thenThrow(new ErrorHandler());
        ResponseEntity<Void> output = blogService.postBlog(blog);
        assertNotNull(output);
    }

    @Test
    void postBlogOfAbsentUser() {
        Blog blog = new Blog();
        blog.setBlogId(blog1.getBlogId());
        blog.setBlogTitle(blog1.getBlogTitle());
        blog.setBlogBody(blog1.getBlogBody());
        blog.setUserBlogId(blog1.getUserEntity().getUserId());
        blog.setPublishBy(blog1.getPublishBy());
        blog.setIsDeleted(blog1.getIsDeleted());
        when(userRepository.findById(1)).thenThrow(new ErrorHandler());
//        ResponseEntity<Void> Output = blogService.postBlog(blog);
//        assertNotNull(Output);
        assertThrows(ErrorHandler.class,()->blogService.postBlog(blog));
    }

    @Test
    void deleteNotExistBlogById() {
        when(blogRepository.findById(1)).thenThrow(new ErrorHandler());
//        ResponseEntity<Void> Output = blogService.deleteBlogById(1);
//        assertNotNull(Output);
        assertThrows(ErrorHandler.class,()->blogService.deleteBlogById(10));
    }

    @Test
    void getNotExistBlogById() {
        when(blogRepository.findById(10)).thenThrow(new ErrorHandler());
//        ResponseEntity<Blog> Output = blogService.getBlogById(1);
//        assertNotNull(Output);
        assertThrows(ErrorHandler.class,()->blogService.getBlogById(10));
    }

//    @Test
//    void getNotExistingBlogById(){
//        when(blogRepository.findById(10)).thenThrow(new ErrorHandler(400,""));
//        try{
//
//        }catch(Exception e){
//
//        }
//    }

}