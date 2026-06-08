package com.sena.database_connection.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.database_connection.entities.Profile;
import com.sena.database_connection.services.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService service;

    public ProfileController(ProfileService service) {
        this.service = service;
    }

    @GetMapping
    public List<Profile> get() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getById(@PathVariable Long id) {

        Optional<Profile> profile = service.porId(id);

        if (profile.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.ok(profile.get());
    }

    @PostMapping
    public Profile create(@RequestBody Profile body) {

        Profile profile = new Profile();
        profile.setUsername(body.getUsername());
        profile.setDescription(body.getDescription());
        profile.setUser(body.getUser());

        return service.crear(profile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profile> update(@PathVariable Long id, @RequestBody Profile body) {

        Profile profile = new Profile();
        profile.setId(id);
        profile.setUsername(body.getUsername());
        profile.setDescription(body.getDescription());
        profile.setUser(body.getUser());

        Profile updated = service.actualizar(profile);

        if (updated == null) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Profile> delete(@PathVariable Long id) {

        Profile deleted = service.eliminar(id);

        if (deleted == null) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.ok(deleted);
    }
}