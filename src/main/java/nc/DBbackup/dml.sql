-- Tabla vendedor
INSERT INTO vendedor (nombre, direccion, coordenada) VALUES 
('Carlos Fernandez', 'Av. Siempre Viva 123', POINT(40.7128, -74.0060)),
('Laura Gomez', 'Calle Falsa 742', POINT(34.0522, -118.2437)),
('Pedro Perez', 'Av. Los Robles 456', POINT(51.5074, -0.1278)),
('Ana Lopez', 'Calle Nueva 789', POINT(48.8566, 2.3522)),
('Miguel Ortega', 'Paseo Colon 101', POINT(40.4168, -3.7038)),
('Lucia Rodriguez', 'Calle Real 555', POINT(52.5200, 13.4050)),
('Jose Ramirez', 'Calle Principal 333', POINT(35.6895, 139.6917)),
('Sofia Martinez', 'Av. Del Sol 202', POINT(41.9028, 12.4964)),
('Tomas Gomez', 'Av. Libertad 11', POINT(55.7558, 37.6173)),
('Elena Ruiz', 'Calle Estrella 999', POINT(37.7749, -122.4194));

-- Tabla cliente
INSERT INTO cliente (cuit, nombre, email, direccion, coordenada) VALUES 
(12345678901, 'Juan Perez', 'juan.perez@mail.com', 'Av. Central 1', POINT(40.7128, -74.0060)),
(23456789012, 'Marta Garcia', 'marta.garcia@mail.com', 'Calle Sur 2', POINT(34.0522, -118.2437)),
(34567890123, 'Luis Gomez', 'luis.gomez@mail.com', 'Calle Norte 3', POINT(51.5074, -0.1278)),
(45678901234, 'Carlos Ruiz', 'carlos.ruiz@mail.com', 'Calle Este 4', POINT(48.8566, 2.3522)),
(56789012345, 'Maria Lopez', 'maria.lopez@mail.com', 'Calle Oeste 5', POINT(40.4168, -3.7038)),
(67890123456, 'Daniela Fernandez', 'daniela.fernandez@mail.com', 'Calle Luz 6', POINT(52.5200, 13.4050)),
(78901234567, 'Andres Ramirez', 'andres.ramirez@mail.com', 'Calle Mar 7', POINT(35.6895, 139.6917)),
(89012345678, 'Carla Diaz', 'carla.diaz@mail.com', 'Calle Sol 8', POINT(41.9028, 12.4964)),
(90123456789, 'Raul Castillo', 'raul.castillo@mail.com', 'Av. De Las Flores 9', POINT(55.7558, 37.6173)),
(12345678091, 'Sara Martinez', 'sara.martinez@mail.com', 'Av. De La Paz 10', POINT(37.7749, -122.4194));

-- Tabla categoria
INSERT INTO categoria (nombre) VALUES 
('Italiana'), ('Rapida'), ('Vegana'), ('Postre'), ('Cafe'), 
('Sandwich'), ('Mexicana'), ('Mariscos'), ('China'), ('Tradicional');

-- Tabla item_menu
INSERT INTO item_menu (nombre, descripcion, precio, id_vendedor, es_bebida) VALUES 
('Pizza Margherita', 'Pizza con tomate, mozzarella y albahaca', 15.00, 1, FALSE),
('Hamburguesa', 'Hamburguesa de carne con queso y lechuga', 10.00, 2, FALSE),
('Tacos', 'Tacos mexicanos de carne y salsa', 8.50, 3, FALSE),
('Ensalada Vegana', 'Ensalada de vegetales frescos', 7.00, 4, FALSE),
('Capuccino', 'Cafe con leche y espuma', 4.50, 5, TRUE),
('Sandwich Vegetariano', 'Pan con vegetales frescos y queso', 6.00, 6, FALSE),
('Pasta Alfredo', 'Pasta con salsa Alfredo y pollo', 12.00, 7, FALSE),
('Sushi', 'Rollo de sushi con pescado y arroz', 13.00, 8, FALSE),
('Paella', 'Arroz con mariscos', 20.00, 9, FALSE),
('Té Verde', 'Infusion de te verde', 3.00, 10, TRUE);

-- Tabla plato
INSERT INTO plato (id, calorias, apto_celiaco, apto_vegano, peso) VALUES 
(1, 800, TRUE, FALSE, 300),
(2, 700, FALSE, FALSE, 250),
(3, 600, TRUE, FALSE, 200),
(4, 400, TRUE, TRUE, 150),
(6, 500, TRUE, TRUE, 200),
(7, 900, FALSE, FALSE, 350),
(8, 300, TRUE, TRUE, 100),
(9, 1000, FALSE, FALSE, 400);

-- Tabla bebida
INSERT INTO bebida (id, graduacion_alcoholica, tamaño, peso) VALUES 
(5, 0.0, 250, 250),
(10, 0.0, 200, 200);

-- Tabla tiene_categoria
INSERT INTO tiene_categoria (id_item_menu, nombre_categoria) VALUES 
(1, 'Italiana'), (1, 'Tradicional'), (2, 'Rapida'), (3, 'Mexicana'), (4, 'Vegana'),
(5, 'Cafe'), (6, 'Sandwich'), (7, 'Italiana'), (8, 'China'), (9, 'Mariscos'), (10, 'Cafe');

-- Tabla pedido
INSERT INTO pedido (precio, id_cliente, id_vendedor, estado) VALUES 
(30.50, 1, 1, 'EN_CARRITO'),
(45.00, 2, 2, 'RECIBIDO'),
(20.00, 3, 3, 'EN_ENVIO'),
(50.00, 4, 4, 'EN_ENVIO'),
(15.00, 5, 5, 'RECIBIDO'),
(60.00, 6, 6, 'EN_ENVIO'),
(35.00, 7, 7, 'RECIBIDO'),
(40.00, 8, 8, 'ENTREGADO'),
(25.00, 9, 9, 'EN_CARRITO'),
(55.00, 10, 10, 'ENTREGADO');

-- Tabla item_pedido
INSERT INTO item_pedido (cantidad, precio, id_item_menu, id_pedido) VALUES 
-- precio deberia tener un metodo q haga los calculos unidad*costounitario
(2, 30.00, 1, 1), 
(1, 20.00, 2, 2), 
(3, 25.50, 3, 3), 
(1, 7.00, 4, 4),
(2, 9.00, 5, 5), 
(1, 6.00, 6, 6), 
(1, 12.00, 7, 7), 
(2, 26.00, 8, 8),
(1, 20.00, 9, 9), 
(3, 9.00, 10, 10);


-- mas datos para poblar

-- Poblar la tabla vendedor
INSERT INTO vendedor (nombre, direccion, coordenada) VALUES 
('La Esquina de Juan', 'Av. Aristobulo del Valle 3500', POINT(-31.6333, -60.7000)),
('Delicias de Maria', 'Bv. Pellegrini 2900', POINT(-31.6250, -60.6900)),
('Sabores de Italia', 'San Martin 2400', POINT(-31.6243, -60.6930)),
('Parrilla El Fogon', 'Av. Freyre 1900', POINT(-31.6290, -60.7005));

-- Poblar la tabla cliente
INSERT INTO cliente (cuit, nombre, email, direccion, coordenada) VALUES 
(20987654321, 'Sofia Rodriguez', 'sofia.rodriguez@example.com', 'Salta 2200', POINT(-31.6267, -60.6965)),
(30214587965, 'Joaquin Perez', 'joaquin.perez@example.com', 'Juan de Garay 1500', POINT(-31.6258, -60.7012)),
(27123456789, 'Mariana Lopez', 'mariana.lopez@example.com', 'Ituzaingo 1700', POINT(-31.6284, -60.7040)),
(20456789012, 'Carlos Gonzalez', 'carlos.gonzalez@example.com', 'San Jeronimo 1800', POINT(-31.6305, -60.7031));

-- Poblar la tabla categoria
INSERT INTO categoria (nombre) VALUES 
('Principal'), 
('Postre'), 
('Bebida'), 
('Aperitivo');

-- Poblar la tabla item_menu
INSERT INTO item_menu (nombre, descripcion, precio, id_vendedor, es_bebida) VALUES 
('Milanesa Napolitana', 'Milanesa con salsa de tomate y queso', 8.50, 1, FALSE),
('Ravioles Caseros', 'Ravioles de ricota con salsa bolognesa', 12.00, 2, FALSE),
('Cerveza Artesanal', 'Cerveza artesanal rubia', 5.00, 3, TRUE),
('Helado de Chocolate', 'Helado artesanal de chocolate', 4.50, 4, FALSE),
('Pizza Margherita', 'Pizza de mozzarella y albahaca', 9.00, 1, FALSE),
('Empanada de Carne', 'Empanada de carne cortada a cuchillo', 1.50, 2, FALSE),
('Limonada', 'Refrescante limonada natural', 3.00, 3, TRUE),
('Ensalada Mixta', 'Ensalada con lechuga, tomate y cebolla', 6.00, 4, FALSE);

-- Poblar la tabla plato (solo para items que no son bebidas)
INSERT INTO plato (id, calorias, apto_celiaco, apto_vegano, peso) VALUES 
(11, 850, FALSE, FALSE, 0.4),
(12, 600, TRUE, TRUE, 0.3),
(14, 250, TRUE, FALSE, 0.2),
(15, 700, FALSE, TRUE, 0.5),
(16, 200, TRUE, FALSE, 0.1),
(18, 150, TRUE, TRUE, 0.25);

-- Poblar la tabla bebida (solo para items que son bebidas)
INSERT INTO bebida (id, graduacion_alcoholica, tamaño, peso) VALUES 
(13, 5.5, 0.5, 0.55),
(17, NULL, 0.4, 0.4);

-- Relacionar items de menu con categorias en la tabla tiene_categoria
INSERT INTO tiene_categoria (id_item_menu, nombre_categoria) VALUES 
(11, 'Principal'),
(12, 'Principal'),
(13, 'Bebida'),
(14, 'Postre'),
(15, 'Principal'),
(16, 'Aperitivo'),
(17, 'Bebida'),
(18, 'Principal');

-- Poblar la tabla pedido
INSERT INTO pedido (id_cliente, id_vendedor, precio, estado) VALUES 
(1, 1, 22.00, 'RECIBIDO'),
(2, 2, 18.50, 'EN_ENVIO'),
(3, 3, 15.50, 'EN_CARRITO'),
(4, 4, 10.00, 'ENTREGADO');

-- Poblar la tabla item_pedido
INSERT INTO item_pedido (cantidad, precio, id_item_menu, id_pedido) VALUES 
-- Pedido 1 (Cliente: Sofia Rodriguez - Vendedor: La Esquina de Juan)
(2, 17.00, 1, 11), -- 2 Milanesas Napolitanas a $8.50 cada una
(1, 5.00, 3, 11), -- 1 Cerveza Artesanal a $5.00

-- Pedido 2 (Cliente: Joaquin Perez - Vendedor: Delicias de Maria)
(2, 12.00, 2, 12), -- 2 Ravioles Caseros a $6.00 cada uno
(3, 1.50, 6, 12), -- 3 Empanadas de Carne a $1.50 cada una

-- Pedido 3 (Cliente: Mariana Lopez - Vendedor: Sabores de Italia)
(1, 5.00, 3, 13), -- 1 Cerveza Artesanal a $5.00
(1, 3.00, 7, 13), -- 1 Limonada a $3.00
(2, 3.25, 8, 13), -- 2 Ensaladas Mixtas a $3.25 cada una

-- Pedido 4 (Cliente: Carlos Gonzalez - Vendedor: Parrilla El Fogon)
(1, 6.00, 8, 14), -- 1 Ensalada Mixta a $6.00
(1, 4.00, 4, 14); -- 1 Helado de Chocolate a $4.00



