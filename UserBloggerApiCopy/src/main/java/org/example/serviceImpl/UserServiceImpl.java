package org.example.serviceImpl;

import com.spring.services.model.User;
import org.example.entity.BlogEntity;
import org.example.entity.UserEntity;
import org.example.exception.ErrorHandler;
import org.example.repository.BlogRepository;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BlogRepository blogRepository;

    @Override
    public ResponseEntity<List<User>> getAllUser() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<User> userArrayList = new ArrayList<>();
        userEntities.stream().filter(e-> e.getIsDeleted().equalsIgnoreCase("N")).forEach(e->{
            User user = new User();
            user.setUserId(e.getUserId());
            user.setFirstName(e.getFirstName());
            user.setLastname(e.getLastname());
            user.setEmail(e.getEmail());
            user.setContact(e.getContact());
            user.setCity(e.getCity());
            user.setPassword(e.getPassword());
            user.setCreatedOn(String.valueOf(e.getCreatedOn()));
            user.setUpdatedOn(String.valueOf(e.getUpdatedOn()));
            userArrayList.add(user);
        });
        return new ResponseEntity<List<User>>(userArrayList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getElementById(Integer userId) {
        try {
            UserEntity userEntity = userRepository.findById(userId).orElse(null);
            if (userEntity.getIsDeleted().equalsIgnoreCase("N")) {
                User user = new User();
                user.setUserId(userEntity.getUserId());
                user.setFirstName(userEntity.getFirstName());
                user.setLastname(userEntity.getLastname());
                user.setEmail(userEntity.getEmail());
                user.setContact(userEntity.getContact());
                user.setCity(userEntity.getCity());
                user.setPassword(userEntity.getPassword());
                user.setCreatedOn(String.valueOf(userEntity.getCreatedOn()));
                user.setUpdatedOn(String.valueOf(userEntity.getUpdatedOn()));
                return new ResponseEntity<User>(user, HttpStatus.OK);
            }
            else{
                throw new ErrorHandler(400,"Data not found");
            }
        } catch (Exception e) {
            throw new ErrorHandler(400, "Data not found");
        }
    }

    @Override
    public ResponseEntity<Void> deleteUser(Integer userId) {
        //userRepository.deleteById(userId);
        try {
            UserEntity user = userRepository.findById(userId).get();
            Integer id = userId;
            List<BlogEntity> blogEntityList = blogRepository.findAll();
            blogEntityList.forEach(e->{
                BlogEntity blog = new BlogEntity();
                if(id==e.getUserEntity().getUserId()){
                    e.setIsDeleted("Y");
                    e.setBlogDeletedOn(new Timestamp(System.currentTimeMillis()));
                    blogRepository.save(e);
                }
            });
            user.setIsDeleted("Y");
            userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            throw new ErrorHandler(400,"Data not found");
        }
    }

    @Override
    public ResponseEntity<Void> updateUser(User user) {
        Optional<UserEntity> userEntity1 = userRepository.findById(user.getUserId());
        if(userEntity1.isPresent()) {
            UserEntity userEntity = userEntity1.get();
            userEntity.setFirstName(user.getFirstName());
            userEntity.setLastname(user.getLastname());
            userEntity.setEmail(user.getEmail());
            userEntity.setContact(user.getContact());
            userEntity.setCity(user.getCity());
            userEntity.setPassword(user.getPassword());
            userEntity.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
            userRepository.save(userEntity);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else throw new ErrorHandler();
    }

    @Override
    public ResponseEntity<Void> postUser(User user) {
        UserEntity userEntity = new UserEntity(user.getFirstName(), user.getLastname(), user.getEmail(), user.getContact(), user.getCity());
        userEntity.setCreatedOn(new Timestamp(System.currentTimeMillis()));
        userEntity.setPassword(user.getPassword());
        userEntity.setIsDeleted("N");
        userRepository.save(userEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        List<UserEntity> userEntities = userRepository.findAll();
        UserEntity user = new UserEntity();
        userEntities.forEach(e-> {
            if(e.getEmail().equalsIgnoreCase(email)) {
                user.setUserId(e.getUserId());
                user.setEmail(e.getEmail());
                user.setFirstName(e.getFirstName());
                user.setCity(e.getCity());
                user.setContact(e.getContact());
                user.setPassword(e.getPassword());
                user.setLastname(e.getLastname());
                user.setIsDeleted(e.getIsDeleted());
                user.setUpdatedOn(e.getUpdatedOn());
                user.setCreatedOn(e.getCreatedOn());
            }
        });
        return user;
    }
}
