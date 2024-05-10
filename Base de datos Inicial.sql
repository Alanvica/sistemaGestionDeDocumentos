CREATE DATABASE BD_SIS_GESTION_DOCUMENTOS;
CREATE TABLE  DOCUMENTO(
	id_documento int primary key,
	id_detalle int
	constraint detalle_documento foreign key (id_detalle) references detalle(id_detalle)
)
CREATE TABLE DETALLE_DOCUMENTO(
	id_detalle int primay key,
	id_usuario int,
	nombre varchar(100),
	fecha date,
	archivo BYTE,
	id_categoria int,
	descripcion varchar(100)
	constraint usuario foreign key(id_usuario) references usuario(id_usuario)
	constraint categoria foreign key(id_categoria) references categoria(id_categoria)
	
)
CREATE TABLE USUARIO (
	id usuario int primary key,
	nombres varchar(100),
	apellidos varchar(50),
	telefono number,
	email varchar(50),
	direccion varchar (100),
	usuario varchar(50),
	contraseña varchar(50)
)
CREATE TABLE CATEGORIA(
	id_categoria int primary key,
	id_usuario int,
	nombre varhcar(50),
	descripcion varchar (100)
 	constraint usuario foreign key(id_usuario) references usuario(id_usuario)
)
