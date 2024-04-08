package bitlab.example.trello.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TASK_CATEGORIES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TaskCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;
}
