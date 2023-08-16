package com.dictality.repository;

import java.util.List;

public interface NotesRepository {

    void saveNote(String note, String username) throws Exception;

    List<String> getNotesContent(String username) throws Exception;
}
