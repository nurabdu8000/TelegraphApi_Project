package uz.pdp.telegraphapi_project.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity extends BaseEntity{
    private String name;
    private String username;
    private String password;
}
