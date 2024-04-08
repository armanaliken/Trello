package bitlab.example.trello.service;

import bitlab.example.trello.entity.Folder;
import bitlab.example.trello.entity.Task;
import bitlab.example.trello.repository.FolderRepository;
import bitlab.example.trello.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private FolderRepository folderRepository;

    public List<Task> getTasksByFolderId(Long folderId) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getFolder().getId().equals(folderId))
                .collect(Collectors.toList());
    }

    public void addTask(String title, String description, Long folderId) {
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid folder id: " + folderId));

        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(0);
        task.setFolder(folder);

        taskRepository.save(task);
    }

    public Task getTaskById(Long Id) {
        return taskRepository.findById(Id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task id: " + Id));
    }

    public void updateTask(Long taskId, Long folderId, String title, String description, int status) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task id: " + taskId));
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid folder id: " + folderId));

        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);
        task.setFolder(folder);

        taskRepository.save(task);
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }
}
