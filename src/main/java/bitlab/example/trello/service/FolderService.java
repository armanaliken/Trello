package bitlab.example.trello.service;

import bitlab.example.trello.entity.Folder;
import bitlab.example.trello.repository.FolderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderService {
    @Autowired
    private FolderRepository folderRepository;

    public List<Folder> getFolders(){
        return folderRepository.findAll();
    }

    public Folder getFolderById(Long Id) {
        return folderRepository.findById(Id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid folder id: " + Id));
    }
    @Transactional
    public void addFolder(String name) {
        Folder folder = new Folder();
        folder.setName(name);
        folderRepository.save(folder);
    }

}
