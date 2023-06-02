package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity ,Long>{

    UserEntity getByUsername(String username);

}
