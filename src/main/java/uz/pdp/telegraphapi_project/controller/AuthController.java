package uz.pdp.telegraphapi_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.telegraphapi_project.dto.UserCreateDto;
import uz.pdp.telegraphapi_project.entity.UserEntity;
import uz.pdp.telegraphapi_project.exceptions.UserNotFoundException;
import uz.pdp.telegraphapi_project.service.UserService;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;


    @PostMapping("/signUp")
    public UserEntity signUp(
            @RequestBody UserCreateDto newUser
            ){
        return userService.add(newUser);
    }

    @PostMapping("/signIn")
    public UserEntity signIn(
            @RequestBody String username,
            @RequestBody String password
    ){
        UserEntity userEntity = userService.signIn(username, password);
        if (userEntity == null){
            throw new UserNotFoundException();
        }
        return userEntity;
    }
}
