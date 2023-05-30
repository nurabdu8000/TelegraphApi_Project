package uz.pdp.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@MappedSuperclass
@Getter
@Setter
public class UserCreateDto {
    private String name;
    private String username;
    private String password;
}
