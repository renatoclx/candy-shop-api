package com.crud.candyshopapi.controllers;

import com.crud.candyshopapi.domain.usuario.RequestUsuario;
import com.crud.candyshopapi.domain.usuario.Usuario;
import com.crud.candyshopapi.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    public ResponseEntity getAllUsers() {
        var allUsers = repository.findAllByAtivoTrue();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Integer id) {
        Optional<Usuario> usuarioOpcional = repository.findById(id);

        if(usuarioOpcional.isPresent()) {
            Usuario usuario = usuarioOpcional.get();
            return ResponseEntity.ok(usuario);
        } else throw new EntityNotFoundException();
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody @Valid RequestUsuario data) {

        Usuario newUser = new Usuario(data);
        repository.save(newUser);

        return ResponseEntity.ok("Usuário inserido com Sucesso!");
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid RequestUsuario data) {
        Optional<Usuario> usuarioOpcional = repository.findById(id);

        if(usuarioOpcional.isPresent()) {
            Usuario usuario = usuarioOpcional.get();
            usuario.setNome(data.nome());
            usuario.setLogin(data.login());
            usuario.setSenha(data.senha());

            return ResponseEntity.ok(usuario);
        } else throw new EntityNotFoundException();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        Optional<Usuario> usuarioOpcional = repository.findById(id);

        if(usuarioOpcional.isPresent()) {
            Usuario usuario = usuarioOpcional.get();
            usuario.setAtivo(0);

            return ResponseEntity.noContent().build();
        } else throw new EntityNotFoundException();
    }
}
