package uz.pdp.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.PostEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<PostEntity ,Long > {
    @Query("select p from posts p where p.ownerId = :ownerId")
    List<PostEntity> getPostEntitiesByUserId(Long ownerId);

    List<PostEntity> searchPostEntityByTitleContainsIgnoreCaseOrCreationDateContainsIgnoreCase(String title, LocalDateTime dateTime, Sort sort);
}
