package br.com.app.controleasy.domain.exception;

public class EmpresaNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public EmpresaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public EmpresaNaoEncontradaException(Long empresaId) {
        this(String.format("Não existe um cadastro de empresa com código %d", empresaId));
    }

}
