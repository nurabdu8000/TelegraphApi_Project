package uz.pdp.telegraphapi_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.telegraphapi_project.dto.PostCreateDto;
import uz.pdp.telegraphapi_project.entity.PostEntity;
import uz.pdp.telegraphapi_project.service.PostService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;


    @GetMapping("/{userId}getAll")
    public ResponseEntity<List<PostEntity>> getAll(
            @PathVariable Long userId,
            @RequestParam int page,
            @RequestParam int size
    ){
        return ResponseEntity.status(200).body(postService.getAll(userId, page, size));

    }

    @GetMapping("/search")
    public ResponseEntity<List<PostEntity>> searchPostByTitleOrDate(
            @RequestParam String title,
            @RequestParam LocalDateTime date,
            @RequestParam boolean sort
    ){
        return ResponseEntity.status(200).body(postService.searchPostByTitleOrDate(title,date,sort));
    }

    @PostMapping("/add")
    public ResponseEntity<PostEntity> addBook(
            @RequestBody PostCreateDto postCreateDto
    ){
        return ResponseEntity.ok(postService.save(postCreateDto));
    }

    @DeleteMapping("/{bookId}delete")
    public ResponseEntity deleteBook(@PathVariable Long bookId){
        postService.deleteById(bookId);
        return ResponseEntity.status(204).build();
    }





}
