package com.dictality;

import com.dictality.front.Front;
import com.dictality.front.LoginFront;
import com.dictality.front.NotesFront;
import com.dictality.repository.FileNotesRepository;
import com.dictality.repository.FileUserRepository;

import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws Exception {
        new Front(
                new LoginFront(
                        new FileUserRepository(Path.of("src/users.csv"))
                ),
                new NotesFront(
                        new FileNotesRepository(Path.of("src/notes.csv"))
                )
        ).start();
    }
}
