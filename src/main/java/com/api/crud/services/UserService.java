package com.api.crud.services

;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.api.crud.models.UserModel;
import com.api.crud.repositories.IUserRepository;

@Service
public class UserService {
    @Autowired
    IUserRepository userRepository;

    /**
     * @return
     */
    public ArrayList<UserModel> getUsers() {

        return (ArrayList<UserModel>) userRepository.findAll();

    }

    public Optional<UserModel> getById(Long id) {
        if (!userRepository.existsById(id)) {
            System.out.println("No se encontró un usuario con ID " + id + ". No se realizó ninguna acción.");
            return Optional.empty(); // Devuelve un Optional vacío si el usuario no existe.
        }

        return userRepository.findById(id);
    }

    public UserModel saveUser(UserModel user) {

        return userRepository.save(user);
    }

    public UserModel updateUser(UserModel updatedUser) {
        // Verificar si el usuario existe antes de intentar actualizarlo
        long userId = updatedUser.getId();
        if (userRepository.existsById(userId)) {
            return userRepository.save(updatedUser);
        } else {
            // Manejar el caso en el que el usuario no existe
            return null;
        }
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            System.out.println("Usuario con ID " + userId + " eliminado correctamente.");
            return true; // Indica que la eliminación fue exitosa.
        } else {
            System.out.println("No se encontró un usuario con ID " + userId + ". No se realizó ninguna eliminación.");
            return false; // Indica que no se realizó la eliminación porque el usuario no existe.
        }
    }

}
