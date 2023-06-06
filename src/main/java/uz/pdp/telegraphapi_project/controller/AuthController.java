package uz.pdp.telegraphapi_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.telegraphapi_project.dto.LoginDto;
import uz.pdp.telegraphapi_project.dto.UserCreateDto;
import uz.pdp.telegraphapi_project.dto.response.JwtResponse;
import uz.pdp.telegraphapi_project.entity.UserEntity;
import uz.pdp.telegraphapi_project.exceptions.UserNotFoundException;
import uz.pdp.telegraphapi_project.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;


    @PostMapping("/signUp")
    public ResponseEntity<UserEntity> signUp(
      @RequestBody UserCreateDto userCreateDto
            ){
        return ResponseEntity.ok(userService.save(userCreateDto));
    }

    @GetMapping("/signIn")
    public ResponseEntity<JwtResponse> signIn(
            @RequestBody LoginDto login
            ){
        return ResponseEntity.ok(userService.signIn(login));
    }
}