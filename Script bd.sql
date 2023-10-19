-- Creación de la base de datos
CREATE DATABASE IF NOT EXISTS DBTPPrueba;

-- Selección de la base de datos
USE DBTPPrueba;

-- Creación de la tabla TIPOSCUENTA
CREATE TABLE TIPOSCUENTA (
    idTipoCuenta INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(50)
);

-- Creación de la tabla TIPOMOVIMIENTO
CREATE TABLE TIPOMOVIMIENTO (
    idTipoMovimiento INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(50)
);

-- Creación de la tabla DATOSCLIENTE
CREATE TABLE DATOSCLIENTE (
    dni INT PRIMARY KEY,
    cuil VARCHAR(20),
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    sexo VARCHAR(10),
    nacionalidad VARCHAR(50),
    fecha_nacimiento DATE,
    direccion VARCHAR(100),
    localidad VARCHAR(50),
    provincia VARCHAR(50),
    correo VARCHAR(100),
    telefono VARCHAR(20)
);

-- Creación de la tabla CLIENTE
CREATE TABLE CLIENTE (
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50),
    contrasena VARCHAR(50),
    dni INT,
    activo BOOLEAN,
    fecha DATE,
    idTipo INT,
    FOREIGN KEY (dni) REFERENCES DATOSCLIENTE(dni)
);

-- Creación de la tabla CUENTA
CREATE TABLE CUENTA (
    idCuenta INT AUTO_INCREMENT PRIMARY KEY,
    idCliente INT,
    numero VARCHAR(20),
    CBU VARCHAR(50) UNIQUE,
    saldo DECIMAL(10, 2),
    fecha DATE,
    activo INT,
    idTipoCuenta INT,
    FOREIGN KEY (idCliente) REFERENCES CLIENTE(idCliente),
    FOREIGN KEY (idTipoCuenta) REFERENCES TIPOSCUENTA(idTipoCuenta)
);

-- Creación de la tabla PRESTAMO
CREATE TABLE PRESTAMO (
    idPrestamo INT AUTO_INCREMENT PRIMARY KEY,
    importe DECIMAL(10, 2),
    importexmes DECIMAL(10, 2),
    cuotas INT,
    fecha DATE,
    plazo_pago_meses INT,
    activo INT,
    estado INT,
    CBU VARCHAR(50)
);

-- Creación de la tabla MOVIMIENTO
CREATE TABLE MOVIMIENTO (
    idMovimiento INT AUTO_INCREMENT PRIMARY KEY,
    detalle VARCHAR(100),
    importe DECIMAL(10, 2),
    idTipoMovimiento INT,
    fecha DATE,
    CBU VARCHAR(50),
    FOREIGN KEY (idTipoMovimiento) REFERENCES TIPOMOVIMIENTO(idTipoMovimiento),
    FOREIGN KEY (CBU) REFERENCES CUENTA(CBU)
);

-- Creación de la tabla TRANSFERENCIA
CREATE TABLE TRANSFERENCIA (
    idTransferencia INT AUTO_INCREMENT PRIMARY KEY,
    CBUOrigen VARCHAR(50),
    CBUDestino VARCHAR(50),
    monto DECIMAL(10, 2),
    fecha DATE
);

-- Creación de la tabla CUOTAPRESTAMO
CREATE TABLE CUOTAPRESTAMO (
    idCuota INT AUTO_INCREMENT,
    idPrestamo INT,
    importe DECIMAL(10, 2),
    n_de_cuota INT,
    fecha DATE,
    PRIMARY KEY (idCuota, idPrestamo),
    FOREIGN KEY (idPrestamo) REFERENCES PRESTAMO(idPrestamo)
);

