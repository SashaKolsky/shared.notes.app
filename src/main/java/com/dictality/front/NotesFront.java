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

        List<String> notesContent = notesRepository.getNotesContent(UserSession.INSTANCE.getUser().username());
        JOptionPane.showMessageDialog(null,
                formattedNotesContent(notesContent),
                "Old Notes:",
                JOptionPane.INFORMATION_MESSAGE);

        int groupId = UserSession.INSTANCE.getUser().groupId();
        displayGroupRecords(groupId);

        String note = JOptionPane.showInputDialog("Your note:");
        notesRepository.saveNote(note, UserSession.INSTANCE.getUser().username());
    }

    private void displayGroupRecords(int groupId) throws Exception {
        if (groupId == -1) return;

        FileUserRepository fileUserRepository = new FileUserRepository(Path.of("src/users.csv"));
        List<User> inGroupNotes = fileUserRepository.findAllByGroupId(groupId);

        List<String> inGroupUsernames = inGroupNotes.stream()
                .filter(user -> {
                    String username = UserSession.INSTANCE.getUser().username();
                    String password = UserSession.INSTANCE.getUser().password();
                    return !user.isSameUser(username, password);
                })
                .map(User::username)
                .toList();

        StringBuilder inGroupNotesString = new StringBuilder();
        for (String username : inGroupUsernames) {
            List<String> notesContent = notesRepository.getNotesContent(username);

            inGroupNotesString.append("\nFrom ").append(username).append(":\n");
            inGroupNotesString.append(formattedNotesContent(notesContent));
        }
        JOptionPane.showMessageDialog(null,
                inGroupNotesString.toString(),
                "Notes from Group Members:",
                JOptionPane.INFORMATION_MESSAGE);
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
