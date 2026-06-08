package com.sena.database_connection.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sena.database_connection.entities.Role;
import com.sena.database_connection.repositories.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public List<Role> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<Role> porId(Long id) {
        return repository.findById(id);
    }

    public Role crear(Role role) {
        return repository.save(role);
    }

    public Role actualizar(Role role) {

        Optional<Role> found = porId(role.getId());

        if (found.isEmpty()) {
            return null;
        }

        return repository.save(role);
    }

    public Role eliminar(Long id) {

        Optional<Role> found = porId(id);

        if (found.isEmpty()) {
            return null;
        }

        repository.delete(found.get());
        return found.get();
    }
}