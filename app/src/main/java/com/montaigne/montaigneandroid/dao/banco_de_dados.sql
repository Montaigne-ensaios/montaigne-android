# TABELA_PROJETO_SPT
CREATE TABLE IF NOT EXISTS spt (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL UNIQUE,
    cliente TEXT,
    empresa TEXT,
    telefone TEXT,
    tecnico_responsavel TEXT,
    endereco TEXT,
    numero_furos INTEGER,
    data_inicio INTEGER
);

# TABELA_SONDAGEM_SPT
CREATE TABLE IF NOT EXISTS sondagem (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_spt INTEGER NOT NULL,
    numero INTEGER,
    nivel_dagua DECIMAL(5, 2),
    nivel_furo DECIMAL(5, 2),
    nivel_referencia DECIMAL(5, 2),
    total_perfurado DECIMAL(5, 2),
    coordenada TEXT,
    data_inicio INTEGER,

    FOREIGN KEY(id_spt) references spt(id)
);

# TABELA_AMOSTRA_SPT
CREATE TABLE IF NOT EXISTS amostra (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_sondagem INTEGER,
    golpes1 INTEGER NOT NULL,
    golpes2 INTEGER,
    golpes3 INTEGER,
    nspt INTEGER NOT NULL

    FOREIGN KEY(id_sondagem) references sondagem(id)
);