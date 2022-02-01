CREATE TABLE funcionario_grupo (
    funcionario_id BIGINT NOT NULL,
    grupo_id BIGINT NOT NULL,
    PRIMARY KEY (funcionario_id, grupo_id)
);

ALTER TABLE funcionario_grupo ADD CONSTRAINT FK_funcionario_grupo_funcionario FOREIGN KEY (funcionario_id) REFERENCES funcionario (id);
ALTER TABLE funcionario_grupo ADD CONSTRAINT FK_funcionario_grupo_grupo FOREIGN KEY (grupo_id) REFERENCES grupo (id);
