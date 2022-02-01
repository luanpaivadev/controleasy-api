--  EMPRESA

INSERT INTO empresa (`id`, `razao_social`, `cnpj`, `logradouro`, `complemento`, `numero`, `bairro`, `localidade`, `uf`, `cep`) VALUES 
(1, '<RAZAO_SOCIAL_EMPRESA>', '<CNPJ_EMPRESA>', '<LOGRADOURO>', '<COMPLEMENTO>', '<NUMERO>', '<BAIRRO>', '<LOCALIDADE>', 'UF', '<CEP>');

-- FUNCIONARIO

INSERT INTO funcionario (`id`, `nome`, `cpf`, `pis`, `ctps`, `funcao`, `usuario`, `senha`, `admissao`, `entrada`, `inicio_intervalo`, `fim_intervalo`, `saida`, `carga_horaria`, `empresa_id`) VALUES 
(1, '<NOME1>', '123.456.789-10', '<PIS1>', '<CTPS1>', '<FUNCAO1>', '<USUARIO1>', '$2a$12$nKU8s9L3GKwe5bb7IbThVe.GhVXySzJHzLrYgtSHOspIXJ9DMZqpe', '2022-01-01', '00:00:00', '00:00:00', '00:00:00', '00:00:00', '00:00:00', 1),
(2, '<NOME2>', '123.456.789-11', '<PIS2>', '<CTPS2>', '<FUNCAO2>', '<USUARIO2>', '$2a$12$nKU8s9L3GKwe5bb7IbThVe.GhVXySzJHzLrYgtSHOspIXJ9DMZqpe', '2022-01-01', '00:00:00', '00:00:00', '00:00:00', '00:00:00', '00:00:00', 1);

-- FUNCIONARIO_GRUPO

INSERT INTO funcionario_grupo (`funcionario_id`, `grupo_id`) VALUES (1, 1), (2, 2);