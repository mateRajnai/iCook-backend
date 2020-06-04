package com.coodcool.icook.controller;

import com.coodcool.icook.model.PersonalNote;
import com.coodcool.icook.service.PersonalNoteHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recipe/{id}/personal-note")
public class PersonalNoteController {

    private final PersonalNoteHandler personalNoteHandler;

    @GetMapping("")
    public List<PersonalNote> getPersonalNotes(@PathVariable("id") String id) {
        return this.personalNoteHandler.getPersonalNoteByRecipeId(id);
    }

    @PostMapping("")
    public PersonalNote createPersonalNote(@RequestBody PersonalNote personalNote ) {
        return this.personalNoteHandler.addPersonalNote(personalNote);
    }

}