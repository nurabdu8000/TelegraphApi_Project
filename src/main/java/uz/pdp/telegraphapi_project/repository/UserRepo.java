package uz.pdp.telegraphapi_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.telegraphapi_project.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity ,Long>{

    Optional<UserEntity> findUserEntityByUsername(String username);

}
