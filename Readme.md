CREATE DATABASE gerencia_tarefas

CREATE TABLE usuarios (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE tarefas (
  id SERIAL PRIMARY KEY,
  usuario_id INTEGER REFERENCES usuarios(id),
  descricao TEXT NOT NULL,
  nome_setor VARCHAR(50) NOT NULL,
  prioridade VARCHAR(10) CHECK (prioridade IN ('baixa', 'média', 'alta')),
  data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  status VARCHAR(20) CHECK (status IN ('a fazer', 'fazendo', 'pronto')) DEFAULT 'a fazer'
);
# SAEP
