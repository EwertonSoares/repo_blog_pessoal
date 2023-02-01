package com.blogPessoal.app.repository;

import com.blogPessoal.app.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
    List<Postagem> findAllByTituloContainingIgnoreCase(@Param("Titulo") String titulo);
}
