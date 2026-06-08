package com.sena.database_connection.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.database_connection.entities.Role;
import com.sena.database_connection.services.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping
    public List<Role> get() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getById(@PathVariable Long id) {

        Optional<Role> role = service.porId(id);

        if (role.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.ok(role.get());
    }

    @PostMapping
    public Role create(@RequestBody Role body) {

        Role role = new Role();
        role.setName(body.getName());

        return service.crear(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> update(@PathVariable Long id, @RequestBody Role body) {

        Role role = new Role();
        role.setId(id);
        role.setName(body.getName());

        Role updated = service.actualizar(role);

        if (updated == null) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Role> delete(@PathVariable Long id) {

        Role deleted = service.eliminar(id);

        if (deleted == null) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.ok(deleted);
    }
}