package com.coodcool.icook.controller;

import com.coodcool.icook.dao.repository.PersonalNoteRepository;
import com.coodcool.icook.model.PersonalNote;
import com.coodcool.icook.model.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/recipe/{id}/personal-note")
@CrossOrigin(origins = "http://localhost:3000")
public class PersonalNoteController {

    private PersonalNoteRepository personalNoteRepository;

    public PersonalNoteController(PersonalNoteRepository personalNoteRepository) {
        this.personalNoteRepository = personalNoteRepository;
    }


    @GetMapping("/list")
    public List<PersonalNote> getPersonalNotes(@PathVariable("id") String id) {
        return this.personalNoteRepository.getAllByRecipeId(id);
    }

    @PostMapping("/save")
    public PersonalNote createPersonalNote(@RequestBody PersonalNote personalNote ) {
        personalNote.setSubmissionTime(LocalDateTime.now());
        this.personalNoteRepository.save(personalNote);
        Long personalNoteId = personalNote.getId();

        return this.personalNoteRepository.findPersonalNoteById(personalNoteId);
    }

}