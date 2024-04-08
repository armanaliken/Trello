package bitlab.example.trello.controller;

import bitlab.example.trello.service.CommentService;
import bitlab.example.trello.service.FolderService;
import bitlab.example.trello.service.TaskCategoryService;
import bitlab.example.trello.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private FolderService folderService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskCategoryService taskCategoryService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("folders", folderService.getFolders());
        return "home";
    }

    @PostMapping("/folder/add")
    public String addFolder(@RequestParam String name){
        folderService.addFolder(name);
        return "redirect:/";
    }

    @GetMapping("/folder/details/{id}")
    public String detailsFolder(@PathVariable Long id, Model model){
        model.addAttribute("detailsFolder", folderService.getFolderById(id));
        model.addAttribute("categories", taskCategoryService.getTaskCategories());
        model.addAttribute("tasks", taskService.getTasksByFolderId(id));
        return "detailsFolder";
    }

    @PostMapping("/task/add")
    public String addTask(@RequestParam("title") String title,
                          @RequestParam("description") String description,
                          @RequestParam("folder_id") Long folderId) {
        taskService.addTask(title, description, folderId);
        return "redirect:/folder/details/" + folderId;
    }

    @PostMapping("/category/add")
    public String addCategory(@RequestParam("folder_id") Long folderId,
                              @RequestParam("category_id") Long categoryId){
        taskCategoryService.addCategory(folderId, categoryId);
        return "redirect:/folder/details/" + folderId;
    }

    @GetMapping("/folder/details/{folderId}/task/details/{taskId}")
    public String getTaskDetails(@PathVariable Long folderId,
                                 @PathVariable Long taskId, Model model) {

        model.addAttribute("task", taskService.getTaskById(taskId));
        model.addAttribute("detailsFolder", folderService.getFolderById(folderId));
//        model.addAttribute("comments", commentService.getCommentsByTask(task));
        return "detailsTask";
    }

    @PostMapping("/task/update")
    public String updateTask(@RequestParam Long taskId,
                             @RequestParam Long folderId,
                             @RequestParam String title,
                             @RequestParam String description,
                             @RequestParam int status) {
        taskService.updateTask(taskId, folderId, title, description, status);
        return "redirect:/folder/details/" + folderId;
    }

    @PostMapping("/task/delete")
    public String deleteTask(@RequestParam Long taskId,
                             @RequestParam Long folderId) {
        commentService.deleteCommentsByTaskId(taskId);
        taskService.deleteTaskById(taskId);
        return "redirect:/folder/details/" + folderId;
    }

    @PostMapping("/comment/add")
    public String addComment(@RequestParam Long taskId,
                             @RequestParam Long folderId,
                             @RequestParam String commentText) {
        commentService.addComment(taskId, commentText);
        return "redirect:/folder/details/" + folderId + "/task/details/" + taskId;
    }
}
