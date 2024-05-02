drop database CFY;
create database CFY;
use CFY;

create  table Usuario(
    id_usuario int primary key auto_increment,
    nombre varchar(50),
    comensales int,
    telefono int,
    tipo varchar(50),
    email varchar(50),
    password varchar(50)
);

create table Menu(
    id_menu int primary key auto_increment,
    nombre varchar(50),
    precio decimal
);

create table Producto(
    id_producto int primary key auto_increment,
    nombre_producto varchar(50),
    stock int
);

create table  Cocinero(
    id_usuario int,
    turno int,
    tipo VARCHAR(50),
    nombre varchar(50),
    especialidad varchar(50),
    telefono int,
    comensales int,
    email varchar(50),
    password varchar(50)
);

create table Mesa(
    id_mesa int primary key,
    id_usuario int,
    estado boolean,
    capacidad int,
    foreign key (id_usuario) references Usuario(id_usuario)
);

create table Camarero(
    id_usuario int primary key ,
    nombre varchar(50),
    turno varchar(50),
    id_mesa int,
    tipo VARCHAR(50),
    telefono int,
    comensales int,
    email varchar(50),
    password varchar(50),
    foreign key (id_mesa) references Mesa(id_mesa),
    foreign key (id_usuario) references Usuario(id_usuario)
);


create table Cliente(
    id_usuario int primary key ,
    nombre varchar(50),
    tipo VARCHAR(50),
    comensales int,
    email varchar(50),
    password varchar(50),
    alergia varchar(50),
    telefono int,
    foreign key (id_usuario) references Usuario(id_usuario)
);

create table Comanda(
    id_comanda int primary key auto_increment,
    id_mesa  int,
    hora_comanda datetime,
    coste_comanda decimal,
    turno_comanda varchar(50),
    foreign key (id_mesa) references Mesa(id_mesa)
);

CREATE TABLE Administrador(
   id_usuario int PRIMARY KEY,
   nombre varchar(50),
   tipo varchar(50),
   comensales varchar(50),
   turno varchar(50),
   id_comanda int,
   cobro_comanda decimal, 
   telefono int,
   email varchar(50),
   password varchar(50),
   FOREIGN KEY (id_comanda) REFERENCES Comanda(id_comanda)
);

create table Plato(
    id_plato int primary key auto_increment,
    id_usuario int,
    nombre_plato varchar(50),
    precio decimal,
    foreign key (id_usuario) references Usuario(id_usuario)
);

create table Comanda_Plato(
    id_comandaPlato int primary key auto_increment,
    id_comandas int,
    id_platos int,
    foreign key (id_comandas) references Comanda(id_comanda),
    foreign key (id_platos) references Plato(id_plato)
);

create table Producto_plato(
    id_productoPlato int primary key auto_increment,
    id_plato int,
    id_producto int,
    foreign key (id_plato) references Plato(id_plato),
    foreign key (id_producto) references Producto(id_producto)
);

create table Menu_Plato(
    id_menuPlato int primary key auto_increment,
    id_menu int,
    id_plato int,
    foreign key (id_menu) references Menu(id_menu),
    foreign key (id_plato) references Plato(id_plato)
);

create table Menu_Comanda(
    id_menuComanda int primary key auto_increment,
    id_menu int,
    id_comanda int,
    foreign key (id_menu) references Menu(id_menu),
    foreign key (id_comanda) references Comanda(id_comanda)
);
