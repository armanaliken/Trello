package bitlab.example.trello.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TASKS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @Column(name = "STATUS")
    private Integer status;

    @ManyToOne
    private Folder folder;
}
