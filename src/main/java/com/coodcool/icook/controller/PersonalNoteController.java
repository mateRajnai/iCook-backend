package com.coodcool.icook.controller;

import com.coodcool.icook.model.PersonalNote;
import com.coodcool.icook.service.PersonalNoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recipe/{id}/personal-note")
public class PersonalNoteController {

    private final PersonalNoteService personalNoteService;

    @GetMapping("")
    public ResponseEntity<List<PersonalNote>> getPersonalNotes(@PathVariable("id") String id, HttpServletRequest request) {
        return ResponseEntity.ok(this.personalNoteService.getPersonalNoteByRecipeId(id, request));
    }

    @PostMapping("")
    public ResponseEntity<PersonalNote> createPersonalNote(@RequestBody PersonalNote personalNote, HttpServletRequest request) {
        return ResponseEntity.ok(this.personalNoteService.addPersonalNote(personalNote, request));
    }

}