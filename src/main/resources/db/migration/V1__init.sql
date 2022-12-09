CREATE TABLE users(
    id serial,
    account int NOT NULL ,

    CONSTRAINT users_idx UNIQUE (account),

    PRIMARY KEY (id)
);

INSERT INTO users (account) VALUES (1200000001),(1200000002);

CREATE TABLE limits(
    id serial,
    id_users bigint NOT NULL,
    set_limit_time timestamp with time zone NOT NULL,
    limit_sum double precision NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (id_users)
    REFERENCES users (id)

);

CREATE TABLE exchanges(
    id serial,
    symbol varchar(10) NOT NULL,
    name varchar(30) NOT NULL,
    exchange varchar(10) NOT NULL,
    datetime date NOT NULL,
    is_market_open boolean NOT NULL,
    closeRate double precision NOT NULL,
    previous_close double precision NOT NULL,

    PRIMARY KEY(id)

);

CREATE TABLE transactions(
    id serial,
    id_limits bigint NOT NULL,
    id_users bigint NOT NULL,
    remaining_limit double precision NOT NULL,
    date_of_the_transaction timestamp with time zone NOT NULL,
    transactional_sum double precision NOT NULL,
    limit_exceeded boolean NOT NULL,
    category varchar(10) NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (id_limits)
    REFERENCES limits (id),
    FOREIGN KEY (id_users)
    REFERENCES users (id)

);

