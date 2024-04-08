package bitlab.example.trello.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "COMMENTS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "COMMENT")
    private String comment;

    @ManyToOne
    private Task task;
}
