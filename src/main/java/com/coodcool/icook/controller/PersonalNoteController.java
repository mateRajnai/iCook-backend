package com.coodcool.icook.controller;

import com.coodcool.icook.dao.repository.PersonalNoteRepository;
import com.coodcool.icook.model.FavoriteRecipe;
import com.coodcool.icook.model.PersonalNote;
import com.coodcool.icook.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personal-note")
@CrossOrigin(origins = "http://localhost:3000")
public class PersonalNoteController {

    private PersonalNoteRepository personalNoteRepository;

    private User dummyUser = User.builder()
            .userName("John")
            .firstName("Denem")
            .email("john.denem@gmail.com")
            .password("johndenem123")
            .id((long)1)
            .build();

    public PersonalNoteController(PersonalNoteRepository personalNoteRepository) {
        this.personalNoteRepository = personalNoteRepository;
    }


    @GetMapping("")
    public List<PersonalNote> getPersonalNotes() {
        return personalNoteRepository.getPersonalNotesByUser_Id(dummyUser.getId());
    }

    @PostMapping("/save")
    public void createPersonalNote(@RequestBody PersonalNote personalNote ) {
        personalNote.setUser(dummyUser);
        personalNoteRepository.save(personalNote);
    }

}