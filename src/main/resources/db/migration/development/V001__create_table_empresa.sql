CREATE TABLE empresa (
	id BIGINT AUTO_INCREMENT,
    razao_social VARCHAR(50) NOT NULL,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    logradouro VARCHAR(50) NOT NULL,
    complemento VARCHAR(50),
    numero VARCHAR(10) NOT NULL DEFAULT 's/n',
    bairro VARCHAR(50) NOT NULL,
    localidade VARCHAR(50) NOT NULL,
    uf CHAR(2) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    PRIMARY KEY (id)
);