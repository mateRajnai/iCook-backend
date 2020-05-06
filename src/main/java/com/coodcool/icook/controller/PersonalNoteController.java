package com.coodcool.icook.controller;

import com.coodcool.icook.dao.repository.PersonalNoteRepository;
import com.coodcool.icook.model.PersonalNote;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/personal-note")
@CrossOrigin(origins = "http://localhost:3000")
public class PersonalNoteController {

    private PersonalNoteRepository personalNoteRepository;

    public PersonalNoteController(PersonalNoteRepository personalNoteRepository) {
        this.personalNoteRepository = personalNoteRepository;
    }

    @GetMapping("")
    public List<PersonalNote> getPersonalNotes() {
        Long userId = (long) 1;
        return null;
    }

}
