package com.blogPessoal.app.repository;

import com.blogPessoal.app.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsuario(String userio);
    List<Usuario> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

}
