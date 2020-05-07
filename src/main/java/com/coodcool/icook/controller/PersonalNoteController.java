package com.coodcool.icook.controller;

import com.coodcool.icook.dao.repository.PersonalNoteRepository;
import com.coodcool.icook.model.PersonalNote;
import com.coodcool.icook.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe/{id}/personal-note")
@CrossOrigin(origins = "http://localhost:3000")
public class PersonalNoteController {

    private PersonalNoteRepository personalNoteRepository;

    private User dummyUser = User.builder()
            .userName("John")
            .firstName("Denem")
            .email("john.denem@gmail.com")
            .password("johndenem123")
            .build();

    public PersonalNoteController(PersonalNoteRepository personalNoteRepository) {
        this.personalNoteRepository = personalNoteRepository;
    }


    @GetMapping("/list")
    public List<PersonalNote> getPersonalNotes(@PathVariable("id") String id) {
        return personalNoteRepository.getAllByRecipeId(id);
    }

    @PostMapping("/save")
    public PersonalNote createPersonalNote(@RequestBody PersonalNote personalNote ) {
        personalNote.setUser(dummyUser);
        personalNoteRepository.save(personalNote);
        Long personalNoteId = personalNote.getId();

        return personalNoteRepository.findPersonalNoteById(personalNoteId);
    }

}