package br.com.luanpaiva.controleasy.core.security.authorizationserver;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.luanpaiva.controleasy.domain.model.Funcionario;
import lombok.Getter;

@Getter
public class AuthUser extends User {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;

    public AuthUser(Funcionario funcionario, Collection<? extends GrantedAuthority> authorities) {
        super(funcionario.getCpf(), funcionario.getSenha(), authorities);
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
    }
}
