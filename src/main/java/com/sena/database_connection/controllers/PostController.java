package com.sena.database_connection.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.database_connection.entities.Post;
import com.sena.database_connection.services.PostService;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<Post> get() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getById(@PathVariable Long id) {
        Optional<Post> post = service.porId(id);

        if (post.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.ok(post.get());
    }

    @PostMapping
    public Post create(@RequestBody Post body) {
        Post post = new Post();
        post.setTitle(body.getTitle());
        post.setDescription(body.getDescription());
        post.setLikes(body.getLikes());
        post.setUser(body.getUser());

        return service.crear(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> update(@PathVariable Long id, @RequestBody Post body) {

        Post post = new Post();
        post.setId(id);
        post.setTitle(body.getTitle());
        post.setDescription(body.getDescription());
        post.setLikes(body.getLikes());
        post.setUser(body.getUser());

        Post updated = service.actualizar(post);

        if (updated == null) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> delete(@PathVariable Long id) {

        Post deleted = service.eliminar(id);

        if (deleted == null) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.ok(deleted);
    }
}