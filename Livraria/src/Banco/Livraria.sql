drop database if exists livraria;

create database livraria;

use livraria;

create table livro(
	id int auto_increment primary key,
	titulo varchar(50) not null,
	autor varchar(50) not null,
	preco float(5,2) not null
);

create table endereco(
	id int auto_increment primary key,
	estado varchar(2) not null, 
	cidade varchar(50) not null,
	endereco varchar(100) not null,
	complemento varchar(100),
	bairro varchar(50) not null,
	cep varchar(9) not null
);

create table usuario(
	id int auto_increment primary key,
	nome varchar(100) not null,
	email varchar(100) not null,
	senha varchar(100) not null,
	cpf varchar(20) not null,
	celular varchar(20) not null,
	telefone varchar(20),
	idend int not null,
	autenticador int,
	foreign key (idend) references endereco (id)
);

create table carrinho (
	id int auto_increment primary key,
	idlivro int not null,
	idusuario int not null,
	qntd int not null,
	foreign key(idlivro) references livro(id),
	foreign key(idusuario) references usuario(id)
);

------------------------------------------

drop database if exists livraria;

create database livraria;

use livraria;

create table livro(
	id int auto_increment primary key,
	titulo varchar(50) not null,
	autor varchar(50) not null,
	preco float(5,2) not null,
	qntestk int not null
);

create table endereco(
	id int auto_increment primary key,
	endereco varchar(100) not null,
	complemento varchar(100),
	cep varchar(9) not null
);

create table usuario(
	id int auto_increment primary key,
	nome varchar(100) not null,
	email varchar(100) not null,
	senha varchar(100) not null,
	cpf varchar(11) not null,
	datanasc date not null,
	celular varchar(20) not null,
	telefone varchar(20),
	idend int not null,
	foreign key (idend) references endereco (id)
);
