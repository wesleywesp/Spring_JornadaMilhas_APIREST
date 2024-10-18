create table depoimentos(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    foto varchar(500) not null,
    depoimento varchar(255) not null,
    ativo tinyint not null default 1,

    primary key(id)
 ) charset=utf8mb4;;