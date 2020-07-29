package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class HomeService {
    private final FileMapper fileMapper;
    private final NoteMapper noteMapper;
    private final CredentialsMapper credentialsMapper;
    private  final UserService userService;
    private final EncryptionService encryptionService;

public HomeService(FileMapper fileMapper, NoteMapper noteMapper, CredentialsMapper credentialsMapper, UserService userService, EncryptionService encryptionService) {
        this.fileMapper = fileMapper;
        this.noteMapper = noteMapper;
        this.credentialsMapper = credentialsMapper;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    public Model reloadModelWithData(final Model model){
            Integer userId = userService.getUser().getUserId();
            List<File> userFiles = fileMapper.loadFilesForUser(userId);
            model.addAttribute("userFiles",userFiles);

            model.addAttribute("noteForm",new Note());
            List<Note> userNotes = noteMapper.loadAllNotes(userId);
            model.addAttribute("userNotes",userNotes);

            model.addAttribute("credentialsForm",new Credentials());
            List<Credentials> userCredentials = credentialsMapper.loadAllCredentials(userId);
            for (Credentials credential:userCredentials) {

                credential.setDecryptedPassword(encryptionService.decryptValue(credential.getPassword(),credential.getKey()));
            }
            model.addAttribute("credentials",userCredentials);
            return model;
    }
}
