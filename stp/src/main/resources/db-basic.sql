-- Endereco
INSERT INTO Endereco (rua, complemento, bairro, cep, cidade, estado) VALUES
('Rua A', 'Apto 101', 'Centro', '74000000', 'Goiânia', 'GO'),
('Rua B', 'Casa 2', 'Setor Oeste', '74100000', 'Goiânia', 'GO');

-- UnidadeHospitalar
INSERT INTO UnidadeHospitalar (telefone, email, tem_uti, dados_pessoal, latitude, longitude, disponibilidade_leitos, endereco_id) VALUES
('6232345678', 'hospital1@email.com', true, 'Hospital 1', -16.6799, -49.2550, 50, 1),
('6232345679', 'hospital2@email.com', false, 'Hospital 2', -16.7000, -49.2700, 30, 2);

-- Especialidade
INSERT INTO especialidade (nome, descricao) VALUES
('Cardiologia', 'Especialidade médica que se ocupa do diagnóstico e tratamento das doenças do coração e do sistema circulatório.'),
('Dermatologia', 'Ramo da medicina que trata das doenças da pele, cabelos, unhas e mucosas.'),
('Endocrinologia', 'Especialidade que trata dos distúrbios das glândulas endócrinas e dos hormônios, como diabetes e problemas de tireoide.'),
('Gastroenterologia', 'Área da medicina que se dedica ao estudo, diagnóstico e tratamento das doenças do sistema digestivo.'),
('Neurologia', 'Especialidade médica que trata dos distúrbios do sistema nervoso, incluindo o cérebro, medula espinhal e nervos periféricos.'),
('Oftalmologia', 'Ramo da medicina que se ocupa do estudo e tratamento das doenças dos olhos e da visão.'),
('Ortopedia', 'Especialidade médica que trata das doenças e deformidades dos ossos, músculos, ligamentos e articulações.'),
('Pediatria', 'Ramo da medicina que se dedica ao cuidado da saúde de bebês, crianças e adolescentes.'),
('Psiquiatria', 'Especialidade médica que se ocupa do diagnóstico, tratamento e prevenção dos transtornos mentais e emocionais.'),
('Urologia', 'Área da medicina que trata das doenças do sistema urinário de homens e mulheres e do sistema reprodutor masculino.');


-- Medico
INSERT INTO Medico (nome, crm, telefone, papel, unidade_hospitalar_id, especialidade_id) VALUES
('Dr. João', '12345GO', '62999999999', 'PLANTONISTA', 1, 1),
('Dra. Maria', '67890GO', '62988888888', 'REGULADOR', 2, 2);

-- Paciente
INSERT INTO Paciente (cpf, name, telefone, email, tipo_sanguineo, endereco_id) VALUES
('12345678901', 'José Silva', '62977777777', 'jose@email.com', 'A_POSITIVO', 1),
('98765432109', 'Ana Santos', '62966666666', 'ana@email.com', 'O_NEGATIVO', 2);

-- Prontuario
INSERT INTO Prontuario (classificacao) VALUES
('VERMELHO'),
('AMARELO');

-- Medicamento
INSERT INTO Medicamento (nome, principio_ativo, descricao) VALUES
('Dipirona', 'Metamizol', 'Analgésico e antitérmico'),
('Omeprazol', 'Omeprazol', 'Inibidor da bomba de prótons');

-- MedicamentoPrescrito
INSERT INTO MedicamentoPrescrito (posologia, dosagem, unidade_dosagem, via_administracao, medicamento_id) VALUES
('8 em 8 horas', 500.0, 'MG', 'ORAL', 1),
('12 em 12 horas', 20.0, 'MG', 'ORAL', 2);

-- DocumentoTransferencia
INSERT INTO DocumentoTransferencia (id) VALUES (1), (2);

-- Solicitacao
INSERT INTO Solicitacao (motivo, horario_solicitacao, medico_id, paciente_id, documento_id) VALUES
('Necessidade de UTI', CURRENT_TIMESTAMP, 1, 1, 1),
('Exame especializado', CURRENT_TIMESTAMP, 2, 2, 2);

-- Transferencia
INSERT INTO Transferencia (horario_saida, horario_previsto_chegada, distancia, meio_transporte, destino_id, origem_id, medico_destino_id, medico_origem_id, medico_regulador_id, documento_transferencia_id, paciente_id, solicitacao_id) VALUES
(CURRENT_TIMESTAMP, DATEADD(HOUR, 1, CURRENT_TIMESTAMP), 50.5, 'AMBULANCIA', 2, 1, 2, 1, 2, 1, 1, 1),
(CURRENT_TIMESTAMP, DATEADD(HOUR, 2, CURRENT_TIMESTAMP), 100.0, 'HELICOPTERO', 1, 2, 1, 2, 1, 2, 2, 2);
