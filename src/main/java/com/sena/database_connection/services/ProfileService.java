package com.sena.database_connection.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sena.database_connection.entities.Profile;
import com.sena.database_connection.repositories.ProfileRepository;

@Service
public class ProfileService {

    private final ProfileRepository repository;

    public ProfileService(ProfileRepository repository) {
        this.repository = repository;
    }

    public List<Profile> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<Profile> porId(Long id) {
        return repository.findById(id);
    }

    public Profile crear(Profile profile) {
        return repository.save(profile);
    }

    public Profile actualizar(Profile profile) {

        Optional<Profile> found = porId(profile.getId());

        if (found.isEmpty()) {
            return null;
        }

        return repository.save(profile);
    }

    public Profile eliminar(Long id) {

        Optional<Profile> found = porId(id);

        if (found.isEmpty()) {
            return null;
        }

        repository.delete(found.get());
        return found.get();
    }
}