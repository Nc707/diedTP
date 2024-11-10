CREATE TABLE vendedor (
    id INTEGER auto_increment PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    direccion VARCHAR(80) NOT NULL,
    coordenada POINT
);

CREATE TABLE cliente (
    id INTEGER auto_increment PRIMARY KEY,
    cuit INTEGER NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    email VARCHAR(80) NOT NULL,
    direccion VARCHAR(80) NOT NULL,
    coordenada POINT
);

CREATE TABLE categoria (
    nombre VARCHAR(50) PRIMARY KEY
);

CREATE TABLE item_menu (
    id INTEGER auto_increment PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    id_vendedor INTEGER,
    es_bebida BOOLEAN NOT NULL,
    FOREIGN KEY (id_vendedor) REFERENCES vendedor(id) ON DELETE CASCADE
);

CREATE TABLE tiene_categoria (
    id_item_menu INTEGER,
    nombre_categoria VARCHAR(50),
    FOREIGN KEY (id_item_menu) REFERENCES item_menu(id) ON DELETE CASCADE,
    FOREIGN KEY (nombre_categoria) REFERENCES categoria(nombre) ON DELETE CASCADE, 
    PRIMARY KEY (id_item_menu, nombre_categoria)
);

CREATE TABLE pedido (
    id INTEGER auto_increment PRIMARY KEY,
    precio DECIMAL(10,2) NOT NULL,
    id_cliente INTEGER,
    id_vendedor INTEGER,
    estado ENUM('EN_CARRITO', 'RECIBIDO', 'EN_ENVIO', 'ENTREGADO') NOT NULL DEFAULT 'EN_CARRITO',
    FOREIGN KEY (id_cliente) REFERENCES cliente(id) ON DELETE SET NULL,
    FOREIGN KEY (id_vendedor) REFERENCES vendedor(id) ON DELETE SET NULL
);

CREATE TABLE item_pedido (
    id INTEGER auto_increment PRIMARY KEY,
    cantidad INTEGER NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    id_item_menu INTEGER,
    id_pedido INTEGER,
    FOREIGN KEY (id_item_menu) REFERENCES item_menu(id) ON DELETE SET NULL,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id) ON DELETE CASCADE
);
