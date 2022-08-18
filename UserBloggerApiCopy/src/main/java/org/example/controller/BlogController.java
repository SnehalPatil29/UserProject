package org.example.controller;

import com.spring.services.api.BlogsApi;
import com.spring.services.model.Blog;
import org.example.exception.ErrorHandler;
import org.example.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class BlogController implements BlogsApi {

    @Autowired
    BlogService blogService;

    @Override
    public ResponseEntity<Void> blogsAddBlogPost(Blog blog){
        return blogService.postBlog(blog);
    }

    @Override
    public ResponseEntity<Void> blogsDeleteBlogDelete(Integer blogId) {
        try {
            return blogService.deleteBlogById(blogId);
        }catch(Exception e){
            throw new ErrorHandler();
        }
    }

    @Override
    public ResponseEntity<List<Blog>> blogsGetAllBlogsGet(){
        return blogService.getAllBlog();
    }

    @Override
    public ResponseEntity<Blog> blogsGetBlogDataByIdGet(Integer blogId){
        try{
            return blogService.getBlogById(blogId);}
        catch (Exception e){
            throw new ErrorHandler();
        }
    }

    @Override
    public ResponseEntity<List<Blog>> blogsGetAllBlogByUserIdGet(Integer userId){
        try {
            return blogService.getAllBlogByUserId(userId);
        }catch(Exception e){
            throw new ErrorHandler();
        }
    }

    @Override
    public ResponseEntity<Void> blogsUpdateBlogPost(Blog blog){
        return blogService.updateBlog(blog);
    }
}

