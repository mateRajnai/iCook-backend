package com.coodcool.icook;

import com.coodcool.icook.controller.PersonalNoteController;
import com.coodcool.icook.dao.repository.PersonalNoteRepository;
import com.coodcool.icook.model.PersonalNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class IcookApplication {

    public static void main(String[] args) {
        SpringApplication.run(IcookApplication.class, args);
    }
}
