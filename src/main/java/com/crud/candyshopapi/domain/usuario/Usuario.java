package com.crud.candyshopapi.domain.usuario;


import com.crud.candyshopapi.domain.produto.RequestProduto;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Table(name = "usuarios")
@Entity(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String login;
    private String senha;
    private Integer ativo;

    public Usuario(RequestUsuario requestUsuario) {
        this.nome = requestUsuario.nome();
        this.login = requestUsuario.login();
        this.senha = requestUsuario.senha();
        this.ativo = 1;
    }
}
