package uz.pdp.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.pdp.dto.PostCreateDto;
import uz.pdp.entity.PostEntity;
import uz.pdp.repository.PostRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepo postRepo;
    private final ModelMapper modelMapper;

    public PostEntity add(PostCreateDto postDto){
        PostEntity post = modelMapper.map(postDto, PostEntity.class);
        return postRepo.save(post);
    }

    public PostEntity update(Long id, PostCreateDto postDto){
        PostEntity post = getById(id);
        modelMapper.map(postDto,id);
        return postRepo.save(post);
    }

    public PostEntity getById(Long id){
        Optional<PostEntity> byId = postRepo.findById(id);
        return byId.orElse(null);
    }

    public List<PostEntity> getAll(Long id,int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return postRepo.getPostEntitiesByUserId(id,pageable);
    }

    public List<PostEntity> searchByTitleOrDate(String title, LocalDateTime creationDate,boolean sort){
        return postRepo.searchPostEntityByTitleContainsIgnoreCaseOrCreationDateContainsIgnoreCase(
                title ,creationDate, Sort.by(Sort.Order.asc("creationDate"))
        );
    }

    public void deleteById(Long id){
        postRepo.deleteById(id);
    }

}
