package uz.pdp.telegraphapi_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.telegraphapi_project.dto.PostCreateDto;
import uz.pdp.telegraphapi_project.entity.PostEntity;
import uz.pdp.telegraphapi_project.service.PostService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @PostMapping("/add")
    public PostEntity add(
            @RequestBody PostCreateDto postCreateDto
            ){
        return postService.add(postCreateDto);
    }

    @PutMapping("/update{id}")
    public String postUpdate(
            @PathVariable Long id,
            @RequestBody PostCreateDto update
    ){
        postService.update(id, update);
        return "Successfully updated";
    }

    @GetMapping("/getAll{ownerId}")
    public List<PostEntity> getAll(
            @PathVariable Long ownerId,
            @RequestParam(required = false) int page,
            @RequestParam(required = false) int size
    ){
        return postService.getPostEntitiesByUserId(ownerId,page,size);
    }

    @DeleteMapping("/delete{id}")
    public String delete(
            @PathVariable Long id
    ){
        postService.deleteById(id);
        return "Successfully deleted";
    }


    @GetMapping("/search")
    public List<PostEntity> search(
            @RequestParam (required = false)String title,
            @RequestParam (required = false)LocalDateTime creationDate,
            @RequestParam(required = false) boolean sortByName
            ){
        return postService.searchByTitleOrDate(title, creationDate, sortByName);
    }







}
