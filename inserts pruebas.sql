-- Insertar un cliente
INSERT INTO CLIENTE (usuario, contraseña, activo, fechaCreacion, idTipo, dni, cuil, nombre, apellido, sexo, nacionalidad, fechaNacimiento, direccion, idLocalidad, idProvincia, correo)
VALUES ('1', '1', 1, '2023-10-24', 0, '12345678', '203456789012345', 'Ejemplo', 'Cliente', 0, 'Argentina', '1990-01-01', '123 Calle Principal', 1, 1, 'cliente@example.com');

-- Insertar dos teléfonos para el cliente
INSERT INTO TELEFONOS (idCliente, numero, activo)
VALUES (1, '123-456-7890', 1);

INSERT INTO TELEFONOS (idCliente, numero, activo)
VALUES (1, '987-654-3210', 1);

-- Consulta para recuperar el cliente con sus teléfonos
SELECT c.*, l.ID AS localidadID, l.Nombre AS localidadNombre, p.ID AS provinciaID, p.Nombre AS provinciaNombre, t.idTelefono, t.numero
FROM CLIENTE c
JOIN LOCALIDADES l ON c.idLocalidad = l.ID
JOIN PROVINCIAS p ON c.idProvincia = p.ID
LEFT JOIN TELEFONOS t ON c.idCliente = t.idCliente
WHERE c.idCliente = 2;



SELECT
    c.idCliente AS idCliente,
    c.usuario AS usuario,
    c.contraseña AS contraseña,
    c.activo AS activo,
    c.fechaCreacion AS fechaCreacion,
    c.idTipo AS idTipo,
    c.dni AS dni,
    c.cuil AS cuil,
    c.nombre AS nombre,
    c.apellido AS apellido,
    c.sexo AS sexo,
    c.nacionalidad AS nacionalidad,
    c.fechaNacimiento AS fechaNacimiento,
    c.direccion AS direccion,
    c.correo AS correo,
    l.ID AS localidadID,
    l.Nombre AS localidadNombre,
    p.ID AS provinciaID,
    p.Nombre AS provinciaNombre,
    t.idTelefono AS idTelefono,
    t.numero AS numero,
    t.activo AS telefonoActivo
FROM
    CLIENTE c
JOIN
    LOCALIDADES l ON c.idLocalidad = l.ID
JOIN
    PROVINCIAS p ON c.idProvincia = p.ID
LEFT JOIN
    TELEFONOS t ON c.idCliente = t.idCliente
WHERE
    c.activo = 1
LIMIT 0, 1000;


