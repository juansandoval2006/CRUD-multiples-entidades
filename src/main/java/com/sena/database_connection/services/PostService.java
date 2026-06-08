package com.sena.database_connection.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sena.database_connection.entities.Post;
import com.sena.database_connection.repositories.PostRepository;

@Service
public class PostService {

    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<Post> porId(Long id) {
        return repository.findById(id);
    }

    public Post crear(Post post) {
        return repository.save(post);
    }

    public Post actualizar(Post post) {

        Optional<Post> found = porId(post.getId());

        if (found.isEmpty()) {
            return null;
        }

        return repository.save(post);
    }

    public Post eliminar(Long id) {

        Optional<Post> found = porId(id);

        if (found.isEmpty()) {
            return null;
        }

        repository.delete(found.get());
        return found.get();
    }
}