package com.dictality.front;

import com.dictality.model.User;
import com.dictality.repository.FileUserRepository;
import com.dictality.repository.NotesRepository;
import com.dictality.session.UserSession;

import javax.swing.*;
import java.nio.file.Path;
import java.util.List;

public class NotesFront implements FrontBase {

    private final NotesRepository notesRepository;

    public NotesFront(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    @Override
    public void start() throws Exception {

        List<String> notesContent = notesRepository.getNotesContent(UserSession.INSTANCE.getUser().getUsername());
        JOptionPane.showMessageDialog(null,
                formattedNotesContent(notesContent),
                "Old Notes:",
                JOptionPane.INFORMATION_MESSAGE);

        String note = JOptionPane.showInputDialog("Your note:");
        notesRepository.saveNote(note, UserSession.INSTANCE.getUser().getUsername());
    }

    private String formattedNotesContent(List<String> notesContent) {
        int i = 1;
        StringBuilder result = new StringBuilder();

        for (String note : notesContent) {
            if (!note.isEmpty() && !note.trim().equalsIgnoreCase("null")) {
                result.append(i).append(": ").append(note).append("\n");
                i++;
            }
        }
        return result.toString();
    }
}
