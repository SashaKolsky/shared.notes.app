package com.dictality.repository;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileNotesRepository implements NotesRepository {

    private final Path pathToFile;

    public FileNotesRepository(Path pathToFile) {
        this.pathToFile = pathToFile;
    }

    @Override
    public void saveNote(String note, String username) throws Exception {
        if (!note.isEmpty() && !note.equalsIgnoreCase("null")) {
            Files.write(pathToFile, (username + "," + note + "\n").getBytes(), StandardOpenOption.APPEND);
        }
    }

    @Override
    public List<String> getNotesContent(String username) throws Exception {
        List<String[]> notes;
        try (InputStream inputStream = Files.newInputStream(pathToFile)) {
            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
            notes = reader.readAll();
        }
        return notes.stream()
                .filter(entry -> entry[0].equals(username))
                .map(entry -> entry[1])
                .toList();
    }
}
