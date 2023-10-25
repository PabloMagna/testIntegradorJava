drop database dbintegrador
-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS dbintegrador;

-- Usar la base de datos recién creada
USE dbintegrador;

-- Crear la tabla TIPOSMOVIMIENTO
CREATE TABLE IF NOT EXISTS TIPOSMOVIMIENTO (
    idTipoMovimiento INT PRIMARY KEY,
    descripcion VARCHAR(50)
);

-- Crear la tabla PROVINCIAS
CREATE TABLE IF NOT EXISTS PROVINCIAS (
    ID INT PRIMARY KEY,
    Nombre VARCHAR(30)
);

-- Crear la tabla LOCALIDADES
CREATE TABLE IF NOT EXISTS LOCALIDADES (
    ID INT PRIMARY KEY,
    IDProvincia INT,
    Nombre VARCHAR(30),
    FOREIGN KEY (IDProvincia) REFERENCES PROVINCIAS(ID)
);

-- Crear la tabla CLIENTE
CREATE TABLE IF NOT EXISTS CLIENTE (
    idCliente INT PRIMARY KEY AUTO_INCREMENT,
    usuario VARCHAR(50),
    contraseña VARCHAR(50),
    activo INT,
    fechaCreacion DATE,
    idTipo INT,
    dni VARCHAR(12) UNIQUE,
    cuil VARCHAR(15) UNIQUE,
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    sexo INT,
    nacionalidad VARCHAR(50),
    fechaNacimiento DATE,
    direccion VARCHAR(30),
    idLocalidad INT,
    idProvincia INT,
    correo VARCHAR(50),
    FOREIGN KEY (idLocalidad) REFERENCES LOCALIDADES(ID),
    FOREIGN KEY (idProvincia) REFERENCES PROVINCIAS(ID)
);

-- Crear la tabla TELEFONOS
CREATE TABLE IF NOT EXISTS TELEFONOS (
    idTelefono INT PRIMARY KEY AUTO_INCREMENT,
    idCliente INT,
    numero VARCHAR(30),
    activo INT,
    FOREIGN KEY (idCliente) REFERENCES CLIENTE(idCliente)
);



-- Crear la tabla TIPOSCUENTA
CREATE TABLE IF NOT EXISTS TIPOSCUENTA (
    idTipoCuenta INT PRIMARY KEY,
    descripcion VARCHAR(50)
);

-- Crear la tabla CUENTA
CREATE TABLE IF NOT EXISTS CUENTA (
    numero INT PRIMARY KEY,
    idCliente INT,
    CBU VARCHAR(22) UNIQUE,
    saldo DECIMAL(10,2) CHECK (saldo >= 0),
    fecha DATE,
    activo INT,
    idTipoCuenta INT,
    FOREIGN KEY (idCliente) REFERENCES CLIENTE(idCliente),
    FOREIGN KEY (idTipoCuenta) REFERENCES TIPOSCUENTA(idTipoCuenta)
);

-- Crear la tabla MOVIMIENTO
CREATE TABLE IF NOT EXISTS MOVIMIENTO (
    idMovimiento INT PRIMARY KEY,
    numeroCuenta INT,
    detalle VARCHAR(50),
    importe DECIMAL(10,2),
    idTipoMovimiento INT,
    fecha DATE,
    FOREIGN KEY (numeroCuenta) REFERENCES CUENTA(numero),
    FOREIGN KEY (idTipoMovimiento) REFERENCES TIPOSMOVIMIENTO(idTipoMovimiento)
);

-- Crear la tabla PRESTAMO
CREATE TABLE IF NOT EXISTS PRESTAMO (
    idPrestamo INT PRIMARY KEY AUTO_INCREMENT,
    numeroCuenta INT,
    idCliente INT,
    importePedido DECIMAL(10,2),
    importexmes DECIMAL(10,2),
    cuotas INT,
    fechaPedido DATE,
    estado INT,
    FOREIGN KEY (numeroCuenta) REFERENCES CUENTA(numero),
    FOREIGN KEY (idCliente) REFERENCES CLIENTE(idCliente)
);

-- Crear la tabla CUOTA
CREATE TABLE IF NOT EXISTS CUOTA (
    nCuota INT,
    idPrestamo INT,
    importe DECIMAL(10,2),
    fechaPago DATE,
    estado INT,
    PRIMARY KEY (nCuota, idPrestamo),
    FOREIGN KEY (idPrestamo) REFERENCES PRESTAMO(idPrestamo)
);
