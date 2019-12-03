package br.edu.ifrn.service;

import java.util.Optional;

import br.edu.ifrn.model.User;

public interface UserService {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User save(User user);
}
