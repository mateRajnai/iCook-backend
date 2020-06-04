package com.coodcool.icook.service;

import com.coodcool.icook.dao.repository.PersonalNoteRepository;
import com.coodcool.icook.dao.repository.UserRepository;
import com.coodcool.icook.model.PersonalNote;
import com.coodcool.icook.model.User;
import com.coodcool.icook.security.JwtTokenServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class PersonalNoteService {

    private final PersonalNoteRepository personalNotes;
    private final UserRepository users;
    private final JwtTokenServices jwtTokenServices;

    public List<PersonalNote> getPersonalNoteByRecipeId(String id, HttpServletRequest request) {
        String token = jwtTokenServices.getTokenFromRequest(request);
        String username = jwtTokenServices.getUsernameFromToken(token);
        User user = users.findByUserName(username)
                .orElseThrow(() -> new IllegalArgumentException("User not exists"));

        return this.personalNotes.getAllByRecipeIdAndUser(id, user);
    }

    public PersonalNote addPersonalNote(PersonalNote personalNote, HttpServletRequest request) {

        String token = jwtTokenServices.getTokenFromRequest(request);
        String username = jwtTokenServices.getUsernameFromToken(token);
        User user = users.findByUserName(username)
                .orElseThrow(() -> new IllegalArgumentException("User not exists"));

        personalNote.setSubmissionTime(LocalDateTime.now());
        personalNote.setUser(user);
        this.personalNotes.save(personalNote);
        user.setNotes(Set.of(personalNote));
        Long personalNoteId = personalNote.getId();

        return this.personalNotes.findPersonalNoteById(personalNoteId);

    }

}
