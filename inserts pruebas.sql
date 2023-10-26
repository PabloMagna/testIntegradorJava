-- Insertar un cliente
INSERT INTO CLIENTE (usuario, contraseña, activo, fechaCreacion, idTipo, dni, cuil, nombre, apellido, sexo, nacionalidad, fechaNacimiento, direccion, idLocalidad, idProvincia, correo)
VALUES ('1', '1', 1, '2023-10-24', 0, '12345679', '203456789012346', 'Ejemplo', 'Cliente', 0, 'Argentina', '1990-01-01', '123 Calle Principal', 1, 1, 'cliente@example.com');

DELETE FROM CLIENTE WHERE idCliente = 1;



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

use dbintegrador

select * from cuenta

-- Insertar una nueva cuenta con CBU numérico y una fecha específica (por ejemplo, '2023-01-15')
INSERT INTO cuenta ( idCliente, CBU, saldo, fecha, activo, idTipoCuenta) 
VALUES (1, '1234567890123456789012', 1000.00, '2023-01-15', 1, 1);


update cuenta set activo = 1 where numero = 1;

INSERT INTO prestamo (numeroCuenta, idCliente, importePedido, importexmes, cuotas, fechaPedido, estado) 
VALUES (1, 1, 1000.00, 250.00, 4, '2023-10-25', 0);

delete from cuenta where 


