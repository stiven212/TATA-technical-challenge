CREATE TABLE clientes (
    cliente_id varchar(255) not null primary key,
    contrasenia varchar(255) NULL,
    direccion VARCHAR(255) NULL,
    edad INT NOT NULL,
    estado varchar(255) NULL,
    genero VARCHAR(255) NULL,
    identificacion VARCHAR(255) NULL,
    nombre VARCHAR(255) NULL,
    telefono VARCHAR(255) NULL
);


CREATE TABLE cuentas (
    cuenta_id bigint NOT null primary key,
    estado varchar(255) NULL,
    numero_cuenta varchar(255) NULL,
    saldo_inicial double NOT NULL,
    tipo_cuenta varchar(255) NULL,
    foreign key (cliente_id) VARCHAR(255) null references clientes(cliente_id)
);


CREATE TABLE information_schema.movimientos (
    movimiento_id bigint NOT null primary KEY,
    fecha datetime(6) NULL,
    saldo double NULL,
    tipo_movimiento varchar(255) NULL,
    valor double NULL,
    foreign key (cuenta_id) BIGINT NOT null references cuentas(cuenta_id)
);
