package org.example.serviceImpl;

import com.spring.services.model.Blog;
import org.example.entity.BlogEntity;
import org.example.entity.UserEntity;
import org.example.exception.ErrorHandler;
import org.example.repository.BlogRepository;
import org.example.repository.UserRepository;
import org.example.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public ResponseEntity<List<Blog>> getAllBlog() {
        List<BlogEntity> blogEntity = blogRepository.findAll();
        List<Blog> blogs = new ArrayList<>();
        blogEntity.stream().filter(e -> e.getIsDeleted().equalsIgnoreCase("N")).forEach(e -> {
            Blog blog = new Blog();
            blog.setBlogId(e.getBlogId());
            blog.setUserBlogId((e.getUserEntity()).getUserId());
            blog.setBlogTitle(e.getBlogTitle());
            blog.setBlogBody(e.getBlogBody());
            blog.setPublishBy(e.getPublishBy());
            blog.setIsDeleted(e.getIsDeleted());
            blog.setCreatedAt(String.valueOf(e.getBlogCreatedOn()));
            blog.setBlogDeletedOn(String.valueOf(e.getBlogDeletedOn()));
            blogs.add(blog);
        });
        return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Blog> getBlogById(Integer blogId) throws ErrorHandler {
        try {
            BlogEntity blogEntity = blogRepository.findById(blogId).get();
            if (blogEntity.getIsDeleted() == "N") {
                Blog blog = new Blog();
                blog.setBlogId(blogEntity.getBlogId());
                blog.setUserBlogId((blogEntity.getUserEntity()).getUserId());
                blog.setBlogTitle(blogEntity.getBlogTitle());
                blog.setBlogBody(blogEntity.getBlogBody());
                blog.setPublishBy(blogEntity.getPublishBy());
                blog.setIsDeleted(blogEntity.getIsDeleted());
                blog.setCreatedAt(String.valueOf(blogEntity.getBlogCreatedOn()));
                blog.setBlogDeletedOn(String.valueOf(blogEntity.getBlogDeletedOn()));
                return new ResponseEntity<Blog>(blog, HttpStatus.OK);
            } else throw new ErrorHandler();
        } catch (Exception e) {
            throw new ErrorHandler(400,"Blog not Present");
        }
    }

    @Override
    public ResponseEntity<Void> deleteBlogById(Integer blogId) {
        try {
            BlogEntity blog = blogRepository.findById(blogId).get();
            blog.setIsDeleted("Y");
            blog.setBlogDeletedOn(new Timestamp(System.currentTimeMillis()));
            blogRepository.save(blog);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new ErrorHandler(603,"you are deleting blog which is not exist");
        }
    }

    @Override
    public ResponseEntity<Void> postBlog(Blog blog) {
        Optional<UserEntity> user1 = userRepository.findById(blog.getUserBlogId());
        if (user1.isPresent()) {
            BlogEntity blogEntity = new BlogEntity(blog.getBlogTitle(), blog.getBlogBody(), "N", new Timestamp(System.currentTimeMillis()));
            Optional<UserEntity> user = userRepository.findById(blog.getUserBlogId());
            blogEntity.setPublishBy(user.get().getFirstName() + user.get().getLastname());
            blogEntity.setUserEntity(user.get());
            blogRepository.save(blogEntity);
            return new ResponseEntity<>(HttpStatus.OK);
        } else throw new ErrorHandler(400,"Data not found");
    }

    @Override
    public ResponseEntity<List<Blog>> getAllBlogByUserId(Integer userId) {
        try {
            List<BlogEntity> blogEntities = blogRepository.findAll();
            List<Blog> blogs = new ArrayList<>();
            blogEntities.stream().filter(e -> e.getUserEntity().getUserId().equals(userId)).filter(e->e.getIsDeleted().equalsIgnoreCase("N")).forEach(e -> {
                Blog blog = new Blog();
                blog.setBlogId(e.getBlogId());
                blog.setUserBlogId((e.getUserEntity()).getUserId());
                blog.setBlogTitle(e.getBlogTitle());
                blog.setBlogBody(e.getBlogBody());
                blog.setPublishBy(e.getPublishBy());
                blog.setIsDeleted(e.getIsDeleted());
                blog.setCreatedAt(String.valueOf(e.getBlogCreatedOn()));
                blog.setBlogDeletedOn(String.valueOf(e.getBlogDeletedOn()));
                blogs.add(blog);
            });
            return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
        }catch(Exception e){
            throw new ErrorHandler();
        }
    }

    public ResponseEntity<Void> updateBlog(Blog blog){
        Optional<BlogEntity> blogEntity = blogRepository.findById(blog.getBlogId());
        if(blogEntity.isPresent()){
            BlogEntity blogEntity1 = blogEntity.get();
            blogEntity1.setBlogId(blogEntity1.getBlogId());
            blogEntity1.setBlogTitle(blog.getBlogTitle());
            blogEntity1.setBlogBody(blog.getBlogBody());
            UserEntity user= userRepository.findById(blogEntity1.getUserEntity().getUserId()).get();
            blogEntity1.setPublishBy(user.getFirstName()+user.getLastname());
            blogRepository.save(blogEntity1);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else throw new ErrorHandler();
    }
}


