package br.com.app.controleasy.core.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

    public @interface Empresa {

        @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('ALLOWS_CONSULT_EMPRESA')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeConsultar {
        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('ALLOWS_REGISTER_EMPRESA')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeCadastrar {
        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('ALLOWS_UPDATE_EMPRESA')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeAtualizar {
        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('ALLOWS_DELETE_EMPRESA')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeDeletar {
        }

    }

    public @interface Funcionario {

        @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('ALLOWS_CONSULT_FUNCIONARIO')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeConsultar {
        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('ALLOWS_REGISTER_FUNCIONARIO')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeCadastrar {
        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('ALLOWS_UPDATE_FUNCIONARIO')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeAtualizar {
        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('ALLOWS_DELETE_FUNCIONARIO')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeDeletar {
        }

    }

    public @interface Ponto {

        @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('ALLOWS_CONSULT_PONTO')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeConsultar {
        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('ALLOWS_REGISTER_PONTO')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeCadastrar {
        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('ALLOWS_UPDATE_PONTO')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeAtualizar {
        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('ALLOWS_DELETE_PONTO')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeDeletar {
        }

    }

}
