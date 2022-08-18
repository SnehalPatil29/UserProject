package org.example.service;

import com.spring.services.model.Blog;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BlogService {

    public ResponseEntity<List<Blog>> getAllBlog();

    public ResponseEntity<Blog> getBlogById(Integer blogId);

    public ResponseEntity<Void> deleteBlogById(Integer blogId);

    public ResponseEntity<Void> postBlog(Blog blog);

    public ResponseEntity<List<Blog>> getAllBlogByUserId(Integer userId);

    public ResponseEntity<Void> updateBlog(Blog blog);

}

