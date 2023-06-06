package uz.pdp.telegraphapi_project.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.telegraphapi_project.dto.UserCreateDto;
import uz.pdp.telegraphapi_project.entity.UserEntity;
import uz.pdp.telegraphapi_project.exceptions.UserInfNotTrueException;
import uz.pdp.telegraphapi_project.repository.UserRepo;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;


    public UserEntity add(UserCreateDto userDto){
        UserEntity user = modelMapper.map(userDto ,UserEntity.class);
        return userRepo.save(user);
    }

    public UserEntity signIn(String username ,String password){
        try {
            UserEntity byUsername = userRepo.getByUsername(username);
            if (Objects.equals(byUsername.getUsername(),password)){
                return byUsername;
            }
        }catch (UserInfNotTrueException e){
            throw new UserInfNotTrueException();
        }
        return null;
    }
}
