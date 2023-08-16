package com.dictality.front;

import com.dictality.model.User;
import com.dictality.repository.UserRepository;
import com.dictality.session.UserSession;

import javax.swing.*;
import java.util.NoSuchElementException;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class LoginFront implements FrontBase {

    private final UserRepository userRepository;

    public LoginFront(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void start() throws Exception {

        String username = JOptionPane.showInputDialog("Username:");
        String password = JOptionPane.showInputDialog("Password:");

        User user;
        try {
            user = userRepository.findAll()
                    .stream()
                    .filter(u -> u.isSameUser(username, password))
                    .findFirst()
                    .orElseThrow();
            UserSession.INSTANCE.setUser(user);

        } catch (NoSuchElementException exception) {
            JOptionPane.showMessageDialog(null, "User not found.\nTry again.", "Error", ERROR_MESSAGE);
            start();
        }

    }
}
