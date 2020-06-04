package com.coodcool.icook.service;

import com.coodcool.icook.dao.repository.PersonalNoteRepository;
import com.coodcool.icook.model.PersonalNote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PersonalNoteHandler {

    private final PersonalNoteRepository personalNoteRepository;

    public List<PersonalNote> getPersonalNoteByRecipeId(String id) {
        return this.personalNoteRepository.getAllByRecipeId(id);
    }

    public PersonalNote addPersonalNote(PersonalNote personalNote) {
        personalNote.setSubmissionTime(LocalDateTime.now());
        this.personalNoteRepository.save(personalNote);
        Long personalNoteId = personalNote.getId();
        return this.personalNoteRepository.findPersonalNoteById(personalNoteId);

    }

}
