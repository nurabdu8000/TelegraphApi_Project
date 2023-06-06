package uz.pdp.telegraphapi_project.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.telegraphapi_project.dto.LoginDto;
import uz.pdp.telegraphapi_project.dto.UserCreateDto;
import uz.pdp.telegraphapi_project.dto.response.JwtResponse;
import uz.pdp.telegraphapi_project.entity.UserEntity;
import uz.pdp.telegraphapi_project.exceptions.DataNotFoundException;
import uz.pdp.telegraphapi_project.exceptions.UserInfNotTrueException;
import uz.pdp.telegraphapi_project.repository.UserRepo;



@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public UserEntity save(UserCreateDto userDto){
       UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
       userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
       return userRepo.save(userEntity);
    }

    public JwtResponse signIn(LoginDto login){
        UserEntity userEntity = userRepo.findUserEntityByUsername(login.getUsername())
                .orElseThrow(
                        () -> new DataNotFoundException("user not found")
                );

        if (passwordEncoder.matches(login.getPassword(), userEntity.getPassword())){
            String accessToken = jwtService.generateAccessToken(userEntity);
            return JwtResponse.builder().accessToken(accessToken).build();
        }
        throw new DataNotFoundException("user not found");
    }

}
