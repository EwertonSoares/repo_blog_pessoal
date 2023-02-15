package com.blogPessoal.app.controller;

import com.blogPessoal.app.model.Usuario;
import com.blogPessoal.app.model.UsuarioLogin;
import com.blogPessoal.app.repository.UsuarioRepository;
import com.blogPessoal.app.service.UsuarioService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UsuarioController controller;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void start() {
            usuarioRepository.deleteAll();
            usuarioRepository.save(new Usuario(1L, "João da Silva", "joao@email.com.br", "13465278", "https://i.imgur/FETvc20.jpg"));
            usuarioRepository.save(new Usuario(2L, "Manuela da Silva", "manuela@email.com.br", "13465278", "https://i.imgur/FETvc20.jpg"));
            usuarioRepository.save(new Usuario(3L, "Adriana da Silva", "adriano@email.com.br", "13465278", "https://i.imgur/FETvc20.jpg"));
            usuarioRepository.save(new Usuario(4L, "Paulo da Silva", "paulo@email.com.br", "13465278", "https://i.imgur/FETvc20.jpg"));
    }
    @Test
    @DisplayName("Retorna todos Usuários")
    void getAll() {
        ResponseEntity<List<Usuario>> response = controller.getAll();

        assertNotNull(response.getBody());
        assertNotEquals(response.getBody().size(), 0);
        assertEquals(response.getBody().size(), 4);
        assertEquals(response.getStatusCode().value(), 200);
    }

//    @Test
//    @DisplayName("Retorna um Usuário por id")
//    void getById() {
//        HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(5L, "Ewerton da Silva",
//                "ewerton@email.com.br", "13465278", "https://i.imgur/FETvc20.jpg"));
//
//        ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuarios/{id}", HttpMethod.GET, requisicao, Usuario.class);
//
//        assertNull(resposta);
//        assertEquals(resposta.getStatusCode().value(), 401);
//    }

    @Test
    @DisplayName("Retorna status 401")
    void login() {
        UsuarioLogin usuarioLogin = new UsuarioLogin("João da Silva", "joao@email.com.br",
                "13465278", "https://i.imgur/FETvc20.jpg");

        ResponseEntity<UsuarioLogin> response = controller.login(Optional.of(usuarioLogin));

        assertNull(response.getBody());
        assertEquals(response.getStatusCode().value(), 401);
    }

    @Test
    @DisplayName("Cadastra um novo usuário")
    void postUsuario() {
       HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(5L, "Ewerton da Silva",
               "ewerton@email.com.br", "13465278", "https://i.imgur/FETvc20.jpg"));

        ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, requisicao, Usuario.class);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals(requisicao.getBody().getNome(), resposta.getBody().getNome());
        assertEquals(requisicao.getBody().getUsuario(), resposta.getBody().getUsuario());

    }

    @Test
    @DisplayName("Edita um usuário")
    void putUsuario() {
        HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(1L, "João da Silva",
                "joao1@email.com.br", "13465278", "https://i.imgur/FETvc20.jpg"));

        ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuarios/atualizar", HttpMethod.PUT, requisicao, Usuario.class);

        assertEquals(HttpStatus.UNAUTHORIZED, resposta.getStatusCode());
    }
    @AfterAll
    public void end(){
        usuarioRepository.deleteAll();
    }
}