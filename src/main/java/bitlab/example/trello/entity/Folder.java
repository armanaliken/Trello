package bitlab.example.trello.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "FOLDERS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany
    private List<TaskCategory> categories;
}
