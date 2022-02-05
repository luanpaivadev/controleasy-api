package br.com.app.controleasy.core.security.authorizationserver;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.controleasy.domain.model.Funcionario;
import br.com.app.controleasy.domain.repository.FuncionarioRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        Funcionario funcionario = funcionarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new UsernameNotFoundException("Funcionário não encontrado."));
        return new AuthUser(funcionario, getAuthorities(funcionario));
    }

    private Collection<GrantedAuthority> getAuthorities(Funcionario funcionario) {
        return funcionario.getGrupos().stream()
                .flatMap(grupo -> grupo.getPermissoes().stream()
                        .map(permissao -> new SimpleGrantedAuthority(permissao.getNome().toUpperCase())))
                .collect(Collectors.toSet());
    }

}
