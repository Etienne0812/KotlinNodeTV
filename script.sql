

create table libro
(
    ISBN         char(13)    not null
        primary key,
    titulo       varchar(50) null,
    nombreAutor  varchar(45) null,
    editorial    varchar(45) null,
    yearEdited   year        null,
    yearReleased year        null
);

create table socios
(
    codigo    int         not null
        primary key,
    nombre    varchar(45) null,
    apellidos varchar(45) null,
    dni       char(9)     not null,
    direccion varchar(45) null,
    telefono  char(12)    null,
    constraint socios_dni_uindex
        unique (dni)
);

create table volumen
(
    id          int      not null
        primary key,
    deteriorado tinyint  null,
    ISBN        char(13) not null,
    constraint volumen_libro_ISBN_fk
        foreign key (ISBN) references libro (ISBN)
);

create table prestamo
(
    fechaDevolucion date null,
    fechaPrestado   date null,
    fechaDevuelto   date null,
    idSocio         int  null,
    idVolumen       int  null,
    constraint prestamo_socios_codigo_fk
        foreign key (idSocio) references socios (codigo),
    constraint prestamo_volumen_id_fk
        foreign key (idVolumen) references volumen (id)
);


