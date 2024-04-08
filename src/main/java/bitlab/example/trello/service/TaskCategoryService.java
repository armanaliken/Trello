package bitlab.example.trello.service;

import bitlab.example.trello.entity.Folder;
import bitlab.example.trello.entity.TaskCategory;
import bitlab.example.trello.repository.FolderRepository;
import bitlab.example.trello.repository.TaskCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskCategoryService {
    @Autowired
    private TaskCategoryRepository taskCategoryRepository;
    @Autowired
    private FolderRepository folderRepository;

    public List<TaskCategory> getTaskCategories(){
        return taskCategoryRepository.findAll();
    }

    public void addCategory(Long folderId, Long categoryId){
        TaskCategory category = taskCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category id: " + categoryId));
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid folder id: " + folderId));

        List<TaskCategory> categories = folder.getCategories();
        if(categories == null){
            categories = new ArrayList<>();
        }

        if (!categories.contains(category)) {
            categories.add(category);
            folder.setCategories(categories);
            folderRepository.save(folder);
        }
    }

}
