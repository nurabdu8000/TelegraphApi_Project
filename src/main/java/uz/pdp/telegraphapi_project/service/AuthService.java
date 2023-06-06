package uz.pdp.telegraphapi_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.telegraphapi_project.exceptions.DataNotFoundException;
import uz.pdp.telegraphapi_project.repository.UserRepo;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findUserEntityByUsername(username)
                .orElseThrow(
                        () -> new DataNotFoundException("user not found")
                );
    }
}
