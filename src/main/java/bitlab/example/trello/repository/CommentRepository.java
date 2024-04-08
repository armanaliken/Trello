package bitlab.example.trello.repository;

import bitlab.example.trello.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTask(Task task);

    List<Comment> findByTaskId(Long id);
}
