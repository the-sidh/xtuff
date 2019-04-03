package br.com.equals.xtuff.domain.services;

import br.com.equals.xtuff.domain.entities.User;

public interface UserService {

    public User addUser(User user);
    public void updateUser (User user);
}
