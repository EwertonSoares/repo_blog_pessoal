package com.blogPessoal.app.repository;

import com.blogPessoal.app.model.Usuario;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void start() {
        usuarioRepository.deleteAll();
        usuarioRepository.save(new Usuario(0L, "João da Silva", "joao@email.com.br", "13465278", "https://i.imgur/FETvc20.jpg"));
        usuarioRepository.save(new Usuario(0L, "Manuela da Silva", "manuela@email.com.br", "13465278", "https://i.imgur/FETvc20.jpg"));
        usuarioRepository.save(new Usuario(0L, "Adriana da Silva", "adriano@email.com.br", "13465278", "https://i.imgur/FETvc20.jpg"));
        usuarioRepository.save(new Usuario(0L, "Paulo da Silva", "paulo@email.com.br", "13465278", "https://i.imgur/FETvc20.jpg"));
        }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Retorna um Usuário")
    void findByUsuario() {

        Optional<Usuario> usuario = usuarioRepository.findByUsuario("joao@email.com.br");

        Assertions.assertEquals(usuario.get().getUsuario(), "joao@email.com.br");
    }

    @Test
    @DisplayName("Retorna todos os usários")
    void findAllByNomeContainingIgnoreCase() {

        List<Usuario> usuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");

        Assertions.assertEquals(usuarios.get(0).getNome(), "João da Silva");
        Assertions.assertEquals(usuarios.get(1).getNome(), "Manuela da Silva");
        Assertions.assertEquals(usuarios.get(2).getNome(), "Adriana da Silva");
        Assertions.assertEquals(usuarios.get(3).getNome(), "Paulo da Silva");
    }

    @AfterAll
    public void end(){
        usuarioRepository.deleteAll();
    }
}