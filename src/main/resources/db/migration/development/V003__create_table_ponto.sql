CREATE TABLE ponto (
	id BIGINT AUTO_INCREMENT,
    data DATE NOT NULL,
    entrada TIME,
    inicio_intervalo TIME,
    fim_intervalo TIME,
    saida TIME,
    horas_trabalhadas TIME,
    horas_extras TIME,
    feriado TINYINT,
    folga TINYINT,
    funcionario_id BIGINT NOT NULL,
    PRIMARY KEY (id)
    );

ALTER TABLE ponto ADD CONSTRAINT FK_ponto_funcionario FOREIGN KEY (funcionario_id) REFERENCES funcionario (id);