package uz.pdp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostEntity extends BaseEntity{
    private String title;
    private String author;
    private String content;
    @ManyToOne
    @JoinColumn(name = "ownerId" ,referencedColumnName = "id")
    private UserEntity ownerId;
}
