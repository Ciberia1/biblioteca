INSERT INTO DERBYUSER.AUTOR(APELLIDO, NOMBRE, ID, BIOGRAFIA) 
VALUES ('García Márquez', 'Gabriel', 1000001, 'Escritor colombiano, ganador del Premio Nobel, conocido por su obra "Cien años de soledad"'),
       ('Lorca', 'Federico García', 1000002, 'Poeta, dramaturgo y prosista español, también conocido por su destreza en muchas otras artes.'),
       ('Zafón', 'Carlos', 1000003, 'Escritor español conocido su serie de novelas "El cementerio de los libros olvidados"'),
       ('Almudena', 'Grandes', 1000004, 'Autora española famosa por sus obras "Las edades de Lulú" y "Los aires difíciles"'),
       ('Mendoza', 'Eduardo', 1000005,'Escritor y novelista español, famoso por su trilogía "El rey recibe"'),
       ('Matute', 'Ana María', 1000006,'Autora española reconocida por "Los Abel" y "Olvidado Rey Gudú"'),
       ('Cela','Camilo José' ,1000007,'Escritor español, premio nobel de literatura,famoso por "La colmena"' ),
       ('Eslava','Fernando' ,1000008,'Autor de novelas obras teatro poesia espanol' ),
       ('Aguilera','Beatriz' ,1000009,'Famosa escritora ciencia ficcion Espana' ),
      ('Esquivel','Laura' ,1000010,'Autora mexicana famosa novela Como agua chocolate');

INSERT INTO DERBYUSER.USUARIO(DNI, Fecha_Fin_Pen, Rol, Nombre, Apellidos, Cupo, Contrasena)
VALUES ('12345678Z', '2022-12-31', 'Usuario', 'José', 'Pérez', 5, '1234'),
       ('87654321Y', '2022-12-31', 'Usuario', 'María', 'Gómez', 6, '1234'),
       ('23456789A', '2022-12-31', 'Usuario', 'Antonio', 'López', 6, '1234'),
       ('98765432B', '2022-12-31', 'Usuario', 'Carmen', 'Sánchez', 6, '1234'),
       ('34567890C', '2022-12-31', 'Usuario', 'Juan', 'Rodríguez', 4, '1234'),
       ('76543210D', '2022-12-31', 'Usuario', 'Lucia', 'Ramírez', 3, '1234'),
       ('56789012E', '2023-01-31', 'Usuario', 'Carlos', 'Martinez', 3, '1234'),
       ('01234567F', '2022-11-30', 'Usuario', 'Ana', 'Lopez', 3, '1234'),
       ('89012345G', '2022-12-15', 'Usuario', 'Francisca', 'Gutiérrez', 6, '1234'),
       ('23456789H', '2023-02-28', 'Usuario', 'Diana', 'Rios', 1, '1234');

INSERT INTO DERBYUSER.OBRA(CLASE_OBRA, ID, FECHA_PUBLICACION, GENERO, NRO_PAGINAS, TITULO, EDICION, EDITORIAL, ENCUADERNACION, ISBN, EDITOR, ISSN, PERIODICIDAD, TIPO) 
VALUES ('Libro', 1000001, '1967-06-05 00:00:00.000', 'Realismo mágico', 471, 'Cien años de soledad', 'Primera', 'Plaza & Janés', 'Tapa blanda', '978-84-01-60544-5', null, null, null, null),
        ('Libro', 1000002, '1933-01-01 00:00:00.000', 'Tragedia', 115, 'Bodas de Sangre', 'Tercera', 'Biblioteca Nueva', 'Tapa blanda', '978-84-9742-147-6', null, null, null, null),
        ('Libro', 1000003, '2001-01-01 00:00:00.000', 'Misterio', 576, 'La Sombra del Viento', 'Primera', 'Planeta', 'Tapa blanda', '978-84-08-04163-4', null, null, null, null),
        ('Libro', 1000004, '1989-01-01 00:00:00.000', 'Ficción', 344, 'Las edades de Lulú', 'Segunda', 'Tusquets', 'Tapa dura', '978-84-8383-079-5', null, null, null, null),
        ('Libro', 1000005, '2018-01-01 00:00:00.000', 'Ficción', 352, 'El rey recibe', 'Primera', 'Seix Barral', 'Tapa blanda', '978-84-322-3366-7', null, null, null, null),
        ('Libro', 1000006, '1948-01-01 00:00:00.000', 'Romántica', 240, 'Los Abel', 'Primera', 'Seix Barral', 'Tapa dura', '978-84-322-5719-2', null, null, null, null),
        ('Libro', 1000007, '1945-09-24 00:00:00.000', 'Literatura experimental', 264, 'La colmena', 'Segunda', 'Ediciones Destino', 'Tapa blanda', '978-84-233-0303-1', null, null, null, null),
        ('Libro', 1000008, '2018-04-05 00:00:00.000', 'Ficción', 424, 'El doble', 'Quinta', 'Ediciones Mundo', 'Libro de bolsillo', '978-33-432-1102-9', null, null, null, null),
        ('Libro', 1000009, '2022-01-01 00:00:00.000', 'Narrativa', 500, 'La sombra del almirante', 'Novena', 'RBA', 'Tapa blanda', '978-99-223-1103-1', null, null, null, null),
        ('Libro', 1000010, '1989-01-01 00:00:00.000', 'Ficción', 320, 'Como agua para chocolate', 'Cuarta', 'Penguin Clasicos', 'Libro de bolsillo', '978-84-376-1783-2', null, null, null, null),
        ('Seriada', 1000011, '2023-01-01 00:00:00.000', 'Actualidad', 150, 'ABC', null, null, null, null, 'Vocento', '2345-6789', 'Diario', 'Periódico'),
        ('Seriada', 1000012, '2023-01-01 00:00:00.000', 'Corazón', 100, 'HOLA', null, null, null, null, 'Grupo HOLA', '9876-5432', 'Semanal', 'Revista');
INSERT INTO DERBYUSER.EJEMPLAR(ejemplarID, Estado, ID)
VALUES (1000001, 'Disponible', 1000001),
       (1000002, 'Disponible', 1000002),
       (1000003, 'Disponible', 1000003), 
       (1000004, 'Préstamo', 1000004), 
       (1000005, 'Disponible', 1000005),
       (1000006, 'Prestado', 1000006),
       (1000007, 'Disponible', 1000007),
       (1000008, 'Disponible', 1000008),
       (1000009, 'Prestado', 1000009),
       (1000010, 'Disponible', 1000010);

INSERT INTO DERBYUSER.ESCRIBE(IDAUTOR, IDLIBRO) 
VALUES (1000001, 1000001), 
       (1000002, 1000002), 
       (1000003, 1000003), 
       (1000004, 1000004),
       (1000005, 1000005), 
       (1000006, 1000006), 
       (1000007, 1000007),
       (1000008, 1000008), 
       (1000009, 1000009),
       (1000010, 1000010);

INSERT INTO DERBYUSER.RESERVA(Fecha, DNI, EJEMPLARID, ID)
VALUES ('2022-12-01', '87654321Y', 1000001, 1000001),
       ('2022-12-02', '12345678Z', 1000002, 1000002),
       ('2022-12-03', '98765432B', 1000003, 1000003),
       ('2022-12-04', '23456789A', 1000004, 1000004);
	
INSERT INTO DERBYUSER.PRESTAMO(Fecha_Inicio_Pres, Fecha_Fin_Pres, Activo, DNI, EJEMPLARID, ID)
VALUES ('2022-11-01', '2022-11-30', true, '12345678Z', 1000001, 1000001), 
       ('2022-11-02', '2022-11-30', true, '87654321Y', 1000002, 1000002), 
       ('2023-01-01', '2023-01-31', false, '23456789A', 1000003, 1000003), 
       ('2023-02-01', '2023-02-28', true, '98765432B', 1000004, 1000004);