package com.udacity.jwdnd.course1.cloudstorage.controllers;


import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.HomeService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NoteController {
    private final UserService userService;
    private final NoteMapper noteMapper;
    private final HomeService homeService;

    public NoteController(NoteMapper noteMapper, UserService userService, HomeService homeService){
        this.noteMapper = noteMapper;
        this.userService = userService;
        this.homeService = homeService;
    }

    @GetMapping("/notes")
    public String handleDisplayNote(Model model) {
        model.addAttribute("noteForm",new Note());
        return "home";
    }

    @PostMapping("/saveNote")
    public String handleSaveNote(@ModelAttribute("noteForm") Note noteForm ,Model model) {
        Integer userId = userService.getUser().getUserId();
        Note note = noteMapper.loadNoteById(noteForm.getNoteId());
        if(note == null) {
            note = new Note(noteForm.getNoteTitle(),noteForm.getNoteDescription(),userId);
            int result = noteMapper.createNote(note);
            if(result == 1){
                model.addAttribute("successMessage",
                        "Note successfully stored: " + noteForm.getNoteTitle() + "!");
            }else {
                model.addAttribute("errorMessage",
                        "Error during delete " + noteForm.getNoteTitle() + "!");
            }
        }else {
            note.setNoteTitle(noteForm.getNoteTitle());
            note.setNoteDescription(noteForm.getNoteDescription());
            noteMapper.updateNote(note);
        }

        model = homeService.reloadModelWithData(model);
        return "home";
    }


    @GetMapping("/deleteNote")
    public String handleDeleteNote(@RequestParam(value = "noteTitle", required = true) String noteTitle,Model model) {
        User user = userService.getUser();
        int result = noteMapper.deleteNote(user.getUserId(),noteTitle);
        if(result == 1){
            model.addAttribute("successMessage",
                    "Note successfully deleted " + noteTitle + "!");
        }else {
            model.addAttribute("errorMessage",
                    "Error during delete note with " + noteTitle + "!");
        }
        return "result";
    }

}
