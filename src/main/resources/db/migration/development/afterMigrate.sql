set foreign_key_checks = 0;

DELETE FROM empresa;
DELETE FROM funcionario;
DELETE FROM funcionario_grupo;
DELETE FROM oauth_client_details;

set foreign_key_checks = 1;

ALTER TABLE empresa AUTO_INCREMENT = 1;
ALTER TABLE funcionario AUTO_INCREMENT = 1;
ALTER TABLE funcionario_grupo AUTO_INCREMENT = 1;
ALTER TABLE oauth_client_details AUTO_INCREMENT = 1;

--  EMPRESA

INSERT INTO empresa (`id`, `razao_social`, `cnpj`, `logradouro`, `complemento`, `numero`, `bairro`, `localidade`, `uf`, `cep`) VALUES 
(1, '<RAZAO_SOCIAL_EMPRESA>', '<CNPJ_EMPRESA>', '<LOGRADOURO>', '<COMPLEMENTO>', '<NUMERO>', '<BAIRRO>', '<LOCALIDADE>', 'UF', '<CEP>');

-- FUNCIONARIO

INSERT INTO funcionario (`id`, `nome`, `cpf`, `pis`, `ctps`, `funcao`, `usuario`, `senha`, `admissao`, `entrada`, `inicio_intervalo`, `fim_intervalo`, `saida`, `carga_horaria`, `empresa_id`) VALUES 
(1, '<NOME1>', '123.456.789-10', '<PIS1>', '<CTPS1>', '<FUNCAO1>', '<USUARIO1>', '$2a$12$nKU8s9L3GKwe5bb7IbThVe.GhVXySzJHzLrYgtSHOspIXJ9DMZqpe', '2022-01-01', '00:00:00', '00:00:00', '00:00:00', '00:00:00', '00:00:00', 1),
(2, '<NOME2>', '123.456.789-11', '<PIS2>', '<CTPS2>', '<FUNCAO2>', '<USUARIO2>', '$2a$12$nKU8s9L3GKwe5bb7IbThVe.GhVXySzJHzLrYgtSHOspIXJ9DMZqpe', '2022-01-01', '00:00:00', '00:00:00', '00:00:00', '00:00:00', '00:00:00', 1);

-- FUNCIONARIO_GRUPO

INSERT INTO funcionario_grupo (`funcionario_id`, `grupo_id`) VALUES (1, 1), (2, 2);

-- OAUTH_CLIENT_DETAILS

INSERT INTO oauth_client_details (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES 
('controleasy-app', null, '$2a$12$sQqnwXjBfvRRC2hLRaeW.OC9TwSU5M/./vnrV6QkG9W4LEq2ot9xq', 'READ,WRITE', 'password,refresh_token', null, null, '300', '600', null, null);

INSERT INTO oauth_client_details (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES 
('controleasy-analytics', null, '$2a$12$sQqnwXjBfvRRC2hLRaeW.OC9TwSU5M/./vnrV6QkG9W4LEq2ot9xq', 'READ,WRITE', 'authorization_code', 'http://www.controleasy-analytics.local:8082', null, '300', '600', null, null);

INSERT INTO oauth_client_details (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES 
('controleasy-backend', null, '$2a$12$sQqnwXjBfvRRC2hLRaeW.OC9TwSU5M/./vnrV6QkG9W4LEq2ot9xq', 'READ,WRITE', 'client_credentials', null, null, null, null, null, null);