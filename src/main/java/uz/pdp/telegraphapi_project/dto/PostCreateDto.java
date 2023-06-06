package uz.pdp.telegraphapi_project.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class PostCreateDto {
    private String title;
    private String author;
    private String content;
}
