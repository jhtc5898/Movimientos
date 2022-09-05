CREATE SCHEMA movimientos;

-- movimientos.mov_tclient definition

-- Drop table

-- DROP TABLE movimientos.mov_tclient;

CREATE TABLE movimientos.mov_tclient
(
    idperson             int8         NOT NULL,
    age                  int8 NULL,
    direction            varchar(200) NOT NULL,
    gender               varchar(1) NULL,
    identification_card  varchar(10)  NOT NULL,
    "name"               varchar(200) NOT NULL,
    phone                varchar(20) NULL,
    clientidentification varchar(200) NOT NULL,
    "password"           varchar(20)  NOT NULL,
    status               bool         NOT NULL,
    CONSTRAINT mov_tclient_pkey PRIMARY KEY (idperson),
    CONSTRAINT uk_e8m835pxwj6wwki52jf8nxt7p UNIQUE (clientidentification),
    CONSTRAINT uk_t6nk705adwab38pu2j54yv5m1 UNIQUE (identification_card)
);


-- movimientos.mov_taccount definition

-- Drop table

-- DROP TABLE movimientos.mov_taccount;

CREATE TABLE movimientos.mov_taccount
(
    idaccount        int8       NOT NULL,
    initialbalance   int8       NOT NULL,
    status           bool       NOT NULL,
    typeaccount      varchar(1) NOT NULL,
    client_id_person int8 NULL,
    CONSTRAINT mov_taccount_pkey PRIMARY KEY (idaccount),
    CONSTRAINT fk1vcfx6qtoovei488diqqpxp8f FOREIGN KEY (client_id_person) REFERENCES movimientos.mov_tclient (idperson)
);


-- movimientos.mov_ttransactions definition

-- Drop table

-- DROP TABLE movimientos.mov_ttransactions;

CREATE TABLE movimientos.mov_ttransactions
(
    idtransactions     int8       NOT NULL,
    availablebalance   int8       NOT NULL,
    "date"             date       NOT NULL,
    description        varchar(1) NOT NULL,
    movement           int8       NOT NULL,
    status             bool       NOT NULL,
    typeaccount        varchar(1) NOT NULL,
    account_id_account int8 NULL,
    CONSTRAINT mov_ttransactions_pkey PRIMARY KEY (idtransactions),
    CONSTRAINT fklyiikfcbhy1rne962pn575u9s FOREIGN KEY (account_id_account) REFERENCES movimientos.mov_taccount (idaccount)
);

INSERT INTO movimientos.mov_tclient (idperson, age, direction, gender, identification_card, "name", phone,
                                     clientidentification, "password", status)
VALUES (58, 23, 'Banos-Cuenca', 'M', '0105598254', 'John Tenesaca', '2892434', 'JohnTenesaca', '123456789', true),
       (59, 25, 'Yunguilla', 'F', '0105598255', 'Silvia Cartagena', '2892434', 'SilviaPatricia', '123456789', true);

INSERT INTO movimientos.mov_taccount (idaccount, initialbalance, status, typeaccount, client_id_person)
VALUES (16, 0, true, 'C', 59),
       (15, 1000, true, 'A', 59),
       (17, 1000, true, 'A', 58),
       (18, 1100, true, 'C', 58);

INSERT INTO movimientos.mov_ttransactions (idtransactions, availablebalance, "date", description, movement, status,
                                           typeaccount, account_id_account)
VALUES (60, 1000, '2022-09-04', '+', 1000, true, 'A', 15),
       (61, 1000, '2022-09-04', '+', 1000, true, 'A', 17),
       (62, 1100, '2022-09-04', '+', 1100, true, 'C', 18);
