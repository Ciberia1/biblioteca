
INSERT INTO DERBYUSER.USUARIO (DNI, FECHA_FIN_PEN, ROL, NOMBRE, APELLIDOS, DOMICILIO, CONTRASENA)
VALUES
('1234A', '2022-12-31', 'Bibliotecario', 'José', 'Pérez', 'Calle Falsa, Madrid', '1234'),
('87654321Y', '2022-12-31', 'Administrador', 'María', 'Gómez', 'Avenida Verdadera, Barcelona', '1234'),
('23456789A', '2022-12-31', 'Usuario', 'Antonio', 'López', 'Calle Mayor, Valencia', '1234'),
('98765432B', '2022-12-31', 'Bibliotecario', 'Carmen', 'Sánchez', 'Paseo de las Delicias, Sevilla', '1234'),
('34567890C', '2022-12-31', 'Administrador', 'Juan', 'Rodríguez', 'Calle de la Esperanza, Málaga', '1234'),
('76543210D', '2022-12-31', 'Usuario', 'Lucia', 'Ramírez', 'Plaza del Sol, Granada', '1234'),
('56789012E', '2023-01-31', 'Bibliotecario', 'Carlos', 'Martinez', 'Avenida del Mar, Cádiz', '1234'),
('01234567F', '2022-11-30', 'Administrador', 'Ana', 'Lopez', 'Calle del Bosque, Sevilla', '1234'),
('89012345G', '2022-12-15', 'Usuario', 'Francisca', 'Gutiérrez', 'Calle del Rio, Zaragoza', '1234'),
('23456789H', '2023-02-28', 'Bibliotecario', 'Diana', 'Rios', 'Plaza de la Luz, Madrid', '1234');

INSERT INTO DERBYUSER.OBRA(CLASE_OBRA, ID, FECHA_PUBLICACION, GENERO, NRO_PAGINAS, TITULO, EDICION, EDITORIAL, ENCUADERNACION, ISBN, EDITOR, ISSN, PERIODICIDAD, TIPO) 
VALUES 
('Libro', 01, '1967-06-05', 'Realismo mágico', 471, 'Cien años de soledad', 'Primera', 'Plaza & Janés', 'Tapa blanda', '978-84-01-60544-5', null, null, null, null),
('Libro', 02, '1933-01-01', 'Tragedia', 115, 'Bodas de Sangre', 'Tercera', 'Biblioteca Nueva', 'Tapa blanda', '978-84-9742-147-6', null, null, null, null),
('Libro', 03, '2001-01-01', 'Misterio', 576, 'La Sombra del Viento', 'Primera', 'Planeta', 'Tapa blanda', '978-84-08-04163-4', null, null, null, null),
('Libro', 04, '1989-01-01', 'Ficción', 344, 'Las edades de Lulú', 'Segunda', 'Tusquets', 'Tapa dura', '978-84-8383-079-5', null, null, null, null),
('Libro', 05, '2018-01-01', 'Ficción', 352, 'El rey recibe', 'Primera', 'Seix Barral', 'Tapa blanda', '978-84-322-3366-7', null, null, null, null),
('Libro', 06, '1948-01-01', 'Romántica', 240, 'Los Abel', 'Primera', 'Seix Barral', 'Tapa dura', '978-84-322-5719-2', null, null, null, null),
('Libro', 07, '1945-09-24', 'Literatura experimental', 264, 'La colmena', 'Segunda', 'Ediciones Destino', 'Tapa blanda', '978-84-233-0303-1', null, null, null, null),
('Libro', 08, '2018-04-05', 'Ficción', 424, 'El doble', 'Quinta', 'Ediciones Mundo', 'Libro de bolsillo', '978-33-432-1102-9', null, null, null, null),
('Libro', 09, '2022-01-01', 'Narrativa', 500, 'La sombra del almirante', 'Novena', 'RBA', 'Tapa blanda', '978-99-223-1103-1', null, null, null, null),
('Libro', 10, '1989-01-01', 'Ficción', 320, 'Como agua para chocolate', 'Cuarta', 'Penguin Clasicos', 'Libro de bolsillo', '978-84-376-1783-2', null, null, null, null),
('Seriada', 11, '2023-01-01', 'Actualidad', 150, 'ABC', null, null, null, null, 'Vocento', '2345-6789', 'Diario', 'Periódico'),
('Seriada', 12, '2023-01-01', 'Corazón', 100, 'HOLA', null, null, null, null, 'Grupo HOLA', '9876-5432', 'Semanal', 'Revista');