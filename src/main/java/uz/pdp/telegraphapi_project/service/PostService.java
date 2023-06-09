package uz.pdp.telegraphapi_project.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.pdp.telegraphapi_project.dto.PostCreateDto;
import uz.pdp.telegraphapi_project.entity.BaseEntity;
import uz.pdp.telegraphapi_project.entity.PostEntity;
import uz.pdp.telegraphapi_project.exceptions.DataNotFoundException;
import uz.pdp.telegraphapi_project.repository.PostRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepo postRepo;
    private final ModelMapper modelMapper;

    public PostEntity save(PostCreateDto postDto){
        PostEntity post = modelMapper.map(postDto, PostEntity.class);
        return postRepo.save(post);
    }

//    public PostEntity update(Long id, PostCreateDto postDto){
//        BaseEntity post = postRepo.findById(id).orElseThrow(
//                () -> new DataNotFoundException("post not found"));
//        modelMapper.map(postDto,id);
//        return postRepo.save(post);
//    }

    public PostEntity getById(Long id){
        Optional<PostEntity> byId = postRepo.findById(id);
        return byId.orElse(null);
    }

    public List<PostEntity> getAll(Long id,int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return postRepo.getPostEntitiesByUserId(id,pageable);
    }

    public List<PostEntity> searchPostByTitleOrDate(String title, LocalDateTime creationDate,boolean sort){
        return postRepo.searchPostEntityByTitleContainsIgnoreCaseOrCreationDateContainsIgnoreCase(
                title ,creationDate, Sort.by(Sort.Order.asc("creationDate"))
        );
    }

    public void deleteById(Long ownerId){
        postRepo.deleteById(ownerId);
    }

}
