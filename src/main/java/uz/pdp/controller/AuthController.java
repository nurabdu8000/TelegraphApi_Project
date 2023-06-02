package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.dto.UserCreateDto;
import uz.pdp.entity.UserEntity;
import uz.pdp.exceptions.UserNotFoundException;
import uz.pdp.service.UserService;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;


    @PostMapping("/signUp")
    public UserEntity signUp(
            @ModelAttribute UserCreateDto newUser
            ){
        return userService.add(newUser);
    }

    @PostMapping("/signIn")
    public UserEntity signIn(
            @RequestParam String username,
            @RequestParam String password
    ){
        UserEntity userEntity = userService.signIn(username, password);
        if (userEntity == null){
            throw new UserNotFoundException();
        }
        return userEntity;
    }
}
