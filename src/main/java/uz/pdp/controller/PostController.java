package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.PostCreateDto;
import uz.pdp.entity.PostEntity;
import uz.pdp.service.PostService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @PostMapping("/add")
    public PostEntity add(
            @RequestParam PostCreateDto postCreateDto
            ){
        return postService.add(postCreateDto);
    }

    @PutMapping("/update{id}")
    public String postUpdate(
            @PathVariable Long id,
            @RequestParam PostCreateDto update
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
        return postService.getAll(ownerId,page,size);
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
