create table produto (
codigo integer primary key unique,
quantidade integer,
preco float,
nome varchar(20),
endimg varchar(40),
descricao varchar(200)
);

create table administrador (
usuario varchar(20) primary key,
senha varchar(20)
);

create table endereco (
codigo integer primary key unique,
estado varchar(20),
cidade varchar(20),
bairro varchar(20),
rua varchar(40),
complemento varchar(30),
numero integer
);

insert into produto values (4000, 20, 400.0, 'Monitor 14 Dell', '', 'Monitor');
insert into produto values (4001, 20, 600.0, 'Monitor 15 LG', '', 'Monitor');
insert into produto values (4002, 20, 8000.0, 'MacBook Pro', '', 'MacBook');

insert into endereco values (10, 'SC', 'Florianopolis', 'Pantanal', 'Rua Dep Antonio Edu Vieira', 'apt 100', 200);