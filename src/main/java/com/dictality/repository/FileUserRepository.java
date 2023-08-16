package com.dictality.repository;

import com.dictality.model.User;
import com.dictality.utils.CommonUtils;
import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileUserRepository implements UserRepository{

    private final Path pathToFile;

    public FileUserRepository(Path pathToFile) {
        this.pathToFile = pathToFile;
    }

    @Override
    public List<User> findAll() throws Exception {
        List<String[]> users;
        try (InputStream inputStream = Files.newInputStream(pathToFile)) {
            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
            users = reader.readAll();
        }
        return users.stream()
                .skip(1)
                .map(record -> new User(record[0], record[1]))
                .toList();
    }
}