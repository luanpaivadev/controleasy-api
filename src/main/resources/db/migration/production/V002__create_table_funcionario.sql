CREATE TABLE funcionario (
	id SERIAL,
    nome VARCHAR(50) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    pis VARCHAR(14) NOT NULL,
    ctps VARCHAR(17) NOT NULL,
    funcao VARCHAR(50) NOT NULL,
    usuario VARCHAR(50) NOT NULL,
    senha VARCHAR(60) NOT NULL,
    admissao DATE NOT NULL,
    entrada TIME NOT NULL,
    inicio_intervalo TIME NOT NULL,
    fim_intervalo TIME NOT NULL,
    saida TIME NOT NULL,
    carga_horaria TIME,
    empresa_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE funcionario ADD CONSTRAINT FK_funcionario_empresa FOREIGN KEY (empresa_id) REFERENCES empresa (id);
ALTER TABLE funcionario ADD CONSTRAINT UQ_usuario_empresa UNIQUE (usuario, empresa_id);