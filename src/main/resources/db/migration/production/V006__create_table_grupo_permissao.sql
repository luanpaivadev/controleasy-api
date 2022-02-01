CREATE TABLE grupo_permissao (
    grupo_id BIGINT NOT NULL,
    permissao_id BIGINT NOT NULL,
    PRIMARY KEY (grupo_id, permissao_id)
);

ALTER TABLE grupo_permissao ADD CONSTRAINT FK_grupo_permissao_grupo FOREIGN KEY (grupo_id) REFERENCES grupo (id);
ALTER TABLE grupo_permissao ADD CONSTRAINT FK_grupo_permissao_permissao FOREIGN KEY (permissao_id) REFERENCES permissao (id);