package uz.pdp.telegraphapi_project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public void authenticate(Claims claims , HttpServletRequest request) throws JsonProcessingException{
        String username = claims.getSubject();

        UsernamePasswordAuthenticationToken authentication=
        new UsernamePasswordAuthenticationToken(
                username,
                null
        );
        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
