package org.example.controller;
import com.spring.services.api.UserApi;
import com.spring.services.model.User;
import io.swagger.annotations.Api;
import org.example.entity.UserEntity;
import org.example.exception.ErrorHandler;
import org.example.exception.ServiceException;
import org.example.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@Api(value = "User", tags = "User")
@CrossOrigin(origins = "*")
public class UserController implements UserApi {

    @Autowired
    UserServiceImpl userService;
    @Override
    public ResponseEntity<Void> userAddUserPost(User user){
        return userService.postUser(user);
    }

    @Override
    public ResponseEntity<Void> userDeleteUserByIdDelete(Integer userId){
        try {
            return userService.deleteUser(userId);
        }
        catch(Exception e){
            throw new ErrorHandler();
        }
    }
    @Override
    public ResponseEntity<List<User>> userGetAllUsersGet(){
        return userService.getAllUser();
    }
    @Override
    public ResponseEntity<User> userGetUserByIdGet(Integer userId){
        try {
            return userService.getElementById(userId);
        }catch (Exception e){
            throw new ErrorHandler();
        }
    }
    @Override
    public ResponseEntity<Void> userUpdateUserPost(User user){
        return userService.updateUser(user);
    }

    @GetMapping("/getUser/{email}")
    public UserEntity getUser(@PathVariable String email){
        try {
            return userService.findUserByEmail(email);
        }catch (Exception e){
            throw new ErrorHandler();
        }
    }
}
