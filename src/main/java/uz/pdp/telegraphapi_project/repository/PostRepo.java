package uz.pdp.telegraphapi_project.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.telegraphapi_project.entity.PostEntity;
import uz.pdp.telegraphapi_project.entity.UserEntity;


import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<PostEntity ,Long > {
    @Query("select p from posts p where p.ownerId = :ownerId")
    List<PostEntity> getPostEntitiesByUserId(Long ownerId , Pageable pageable);

    List<PostEntity> searchPostEntityByTitleContainsIgnoreCaseOrCreationDateContainsIgnoreCase(String title, LocalDateTime dateTime, Sort sort);

}
