package com.api.crud.controllers;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.crud.models.UserModel;
import com.api.crud.services.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ArrayList<UserModel> getUsers() {
        return this.userService.getUsers();
    }

    @PostMapping
    public UserModel createUser(@RequestBody UserModel newUser) {
        return this.userService.saveUser(newUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<HashMap<String, String>> getById(@PathVariable long userId) {
        Optional<UserModel> user = this.userService.getById(userId);

        HashMap<String, String> responseMap = new HashMap<>();

        if (user.isPresent()) {
            responseMap.put("response", "OK");
            responseMap.put("message", "Usuario con ID " + userId + " encontrado correctamente.");
            // Puedes agregar más información si es necesario
            return ResponseEntity.ok(responseMap);
        } else {
            responseMap.put("response", "Error");
            responseMap.put("message", "No se encontró usuario con ID " + userId + ". Intente nuevamente con otro ID.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserModel> updateUser(@PathVariable long userId, @RequestBody UserModel updatedUser) {
        updatedUser.setId(userId); // Asigna el ID del path al UserModel actualizado

        UserModel result = this.userService.updateUser(updatedUser);

        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * @param userId
     * @return
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<HashMap<String, String>> deleteUser(@PathVariable long userId) {
        boolean deleted = this.userService.deleteUser(userId);

        HashMap<String, String> responseMap = new HashMap<>();

        if (deleted) {
            responseMap.put("response", "OK");
            responseMap.put("message", "Usuario con ID " + userId + " eliminado correctamente.");
            return ResponseEntity.ok(responseMap);

        } else {
            responseMap.put("response", "Error");
            responseMap.put("message", "No se encontró usuario con ID " + userId + ". Intente nuevamente con otro ID.");
            return ResponseEntity.ok(responseMap);
        }
    }
}
