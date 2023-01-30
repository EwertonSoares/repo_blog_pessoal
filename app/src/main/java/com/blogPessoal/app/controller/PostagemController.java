package com.blogPessoal.app.controller;

import com.blogPessoal.app.model.Postagem;
import com.blogPessoal.app.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostagemController {
    @Autowired
    private PostagemRepository repository;

    @GetMapping("/buscar")
    public ResponseEntity<List<Postagem>> getAll() {

        return ResponseEntity.ok().body(repository.findAll());

    }
}
