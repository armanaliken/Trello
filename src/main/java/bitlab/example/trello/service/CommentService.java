package bitlab.example.trello.service;

import bitlab.example.trello.entity.Comment;
import bitlab.example.trello.repository.CommentRepository;
import bitlab.example.trello.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private TaskRepository taskRepository;

    public List<Comment> getCommentsByTask(Task task) {
        return commentRepository.findByTask(task);
    }

    public void deleteCommentsByTaskId(Long Id) {
        List<Comment> comments = commentRepository.findByTaskId(Id);
        commentRepository.deleteAll(comments);
    }

    public void addComment(Long taskId, String commentText) {
        bitlab.example.trello.entity.Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task id: " + taskId));

        Comment comment = new Comment();
        comment.setTask(task);
        comment.setComment(commentText);
        commentRepository.save(comment);
    }
}
