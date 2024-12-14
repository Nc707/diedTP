-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 16, 2024 at 08:53 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `diedtp`
--

-- --------------------------------------------------------

--
-- Table structure for table `bebida`
--

CREATE TABLE `bebida` (
  `id` int NOT NULL,
  `graduacion_alcoholica` float DEFAULT NULL,
  `tamaño` float NOT NULL,
  `peso` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `bebida`
--

INSERT INTO `bebida` (`id`, `graduacion_alcoholica`, `tamaño`, `peso`) VALUES
(5, 0, 250, 250),
(10, 0, 200, 200),
(13, 5.5, 0.5, 0.55),
(17, NULL, 0.4, 0.4);

-- --------------------------------------------------------

--
-- Table structure for table `categoria`
--

CREATE TABLE `categoria` (
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `categoria`
--

INSERT INTO `categoria` (`nombre`) VALUES
('Aperitivo'),
('Bebida'),
('Cafe'),
('China'),
('Italiana'),
('Mariscos'),
('Mexicana'),
('Postre'),
('Principal'),
('Rapida'),
('Sandwich'),
('Tradicional'),
('Vegana');

-- --------------------------------------------------------

--
-- Table structure for table `cliente`
--

CREATE TABLE `cliente` (
  `id` int NOT NULL,
  `cuit` bigint NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `email` varchar(80) NOT NULL,
  `direccion` varchar(80) NOT NULL,
  `cx` double NOT NULL,
  `cy` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `cliente`
--

INSERT INTO `cliente` (`id`, `cuit`, `nombre`, `email`, `direccion`, `cx`, `cy`) VALUES
(1, 12345678901, 'Juan Perez', 'juan.perez@mail.com', 'Av. Central 1', 40.7128, -74.006),
(2, 23456789012, 'Marta Garcia', 'marta.garcia@mail.com', 'Calle Sur 2', 34.0522, -118.2437),
(3, 34567890123, 'Luis Gomez', 'luis.gomez@mail.com', 'Calle Norte 3', 51.5074, -0.1278),
(4, 45678901234, 'Carlos Ruiz', 'carlos.ruiz@mail.com', 'Calle Este 4', 48.8566, 2.3522),
(5, 56789012345, 'Maria Lopez', 'maria.lopez@mail.com', 'Calle Oeste 5', 40.4168, -3.7038),
(6, 67890123456, 'Daniela Fernandez', 'daniela.fernandez@mail.com', 'Calle Luz 6', 52.52, 13.405),
(7, 78901234567, 'Andres Ramirez', 'andres.ramirez@mail.com', 'Calle Mar 7', 35.6895, 139.6917),
(8, 89012345678, 'Carla Diaz', 'carla.diaz@mail.com', 'Calle Sol 8', 41.9028, 12.4964),
(9, 90123456789, 'Raul Castillo', 'raul.castillo@mail.com', 'Av. De Las Flores 9', 55.7558, 37.6173),
(10, 12345678091, 'Sara Martinez', 'sara.martinez@mail.com', 'Av. De La Paz 10', 37.7749, -122.4194),
(11, 20987654321, 'Sofia Rodriguez', 'sofia.rodriguez@example.com', 'Salta 2200', -31.6267, -60.6965),
(12, 30214587965, 'Joaquin Perez', 'joaquin.perez@example.com', 'Juan de Garay 1500', -31.6258, -60.7012),
(13, 27123456789, 'Mariana Lopez', 'mariana.lopez@example.com', 'Ituzaingo 1700', -31.6284, -60.704),
(14, 20456789012, 'Carlos Gonzalez', 'carlos.gonzalez@example.com', 'San Jeronimo 1800', -31.6305, -60.7031),
(15, 64634, 'dsgdsa', 'dsgds', 'dsfgdgs', 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `item_menu`
--

CREATE TABLE `item_menu` (
  `id` int NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `id_vendedor` int DEFAULT NULL,
  `es_bebida` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `item_menu`
--

INSERT INTO `item_menu` (`id`, `nombre`, `descripcion`, `precio`, `id_vendedor`, `es_bebida`) VALUES
(1, 'Pizza Margherita', 'Pizza con tomate, mozzarella y albahaca', 15.00, 1, 0),
(2, 'Hamburguesa', 'Hamburguesa de carne con queso y lechuga', 10.00, 2, 0),
(3, 'Tacos', 'Tacos mexicanos de carne y salsa', 8.50, 3, 0),
(4, 'Ensalada Vegana', 'Ensalada de vegetales frescos', 7.00, 4, 0),
(5, 'Capuccino', 'Cafe con leche y espuma', 4.50, 5, 1),
(6, 'Sandwich Vegetariano', 'Pan con vegetales frescos y queso', 6.00, 6, 0),
(7, 'Pasta Alfredo', 'Pasta con salsa Alfredo y pollo', 12.00, 7, 0),
(8, 'Sushi', 'Rollo de sushi con pescado y arroz', 13.00, 8, 0),
(9, 'Paella', 'Arroz con mariscos', 20.00, 9, 0),
(10, 'Té Verde', 'Infusion de te verde', 3.00, 10, 1),
(11, 'Milanesa Napolitana', 'Milanesa con salsa de tomate y queso', 8.50, 1, 0),
(12, 'Ravioles Caseros', 'Ravioles de ricota con salsa bolognesa', 12.00, 2, 0),
(13, 'Cerveza Artesanal', 'Cerveza artesanal rubia', 5.00, 3, 1),
(14, 'Helado de Chocolate', 'Helado artesanal de chocolate', 4.50, 4, 0),
(15, 'Pizza Margherita', 'Pizza de mozzarella y albahaca', 9.00, 1, 0),
(16, 'Empanada de Carne', 'Empanada de carne cortada a cuchillo', 1.50, 2, 0),
(17, 'Limonada', 'Refrescante limonada natural', 3.00, 3, 1),
(18, 'Ensalada Mixta', 'Ensalada con lechuga, tomate y cebolla', 6.00, 4, 0);

-- --------------------------------------------------------

--
-- Table structure for table `item_pedido`
--

CREATE TABLE `item_pedido` (
  `id` int NOT NULL,
  `cantidad` int NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `id_item_menu` int DEFAULT NULL,
  `id_pedido` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `item_pedido`
--

INSERT INTO `item_pedido` (`id`, `cantidad`, `precio`, `id_item_menu`, `id_pedido`) VALUES
(1, 2, 30.00, 1, 1),
(2, 1, 20.00, 2, 2),
(3, 3, 25.50, 3, 3),
(4, 1, 7.00, 4, 4),
(5, 2, 9.00, 5, 5),
(6, 1, 6.00, 6, 6),
(7, 1, 12.00, 7, 7),
(8, 2, 26.00, 8, 8),
(9, 1, 20.00, 9, 9),
(10, 3, 9.00, 10, 10),
(11, 2, 17.00, 1, 11),
(12, 1, 5.00, 3, 11),
(13, 2, 12.00, 2, 12),
(14, 3, 1.50, 6, 12),
(15, 1, 5.00, 3, 13),
(16, 1, 3.00, 7, 13),
(17, 2, 3.25, 8, 13),
(18, 1, 6.00, 8, 14),
(19, 1, 4.00, 4, 14);

-- --------------------------------------------------------

--
-- Table structure for table `pedido`
--

CREATE TABLE `pedido` (
  `id` int NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `id_cliente` int DEFAULT NULL,
  `id_vendedor` int DEFAULT NULL,
  `estado` enum('EN_CARRITO','RECIBIDO','EN_ENVIO','ENTREGADO') NOT NULL DEFAULT 'EN_CARRITO'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pedido`
--

INSERT INTO `pedido` (`id`, `precio`, `id_cliente`, `id_vendedor`, `estado`) VALUES
(1, 30.50, 1, 1, 'EN_CARRITO'),
(2, 45.00, 2, 2, 'RECIBIDO'),
(3, 20.00, 3, 3, 'EN_ENVIO'),
(4, 50.00, 4, 4, 'EN_ENVIO'),
(5, 15.00, 5, 5, 'RECIBIDO'),
(6, 60.00, 6, 6, 'EN_ENVIO'),
(7, 35.00, 7, 7, 'RECIBIDO'),
(8, 40.00, 8, 8, 'ENTREGADO'),
(9, 25.00, 9, 9, 'EN_CARRITO'),
(10, 55.00, 10, 10, 'ENTREGADO'),
(11, 22.00, 1, 1, 'RECIBIDO'),
(12, 18.50, 2, 2, 'EN_ENVIO'),
(13, 15.50, 3, 3, 'EN_CARRITO'),
(14, 10.00, 4, 4, 'ENTREGADO');

-- --------------------------------------------------------

--
-- Table structure for table `plato`
--

CREATE TABLE `plato` (
  `id` int NOT NULL,
  `calorias` float NOT NULL,
  `apto_celiaco` tinyint(1) NOT NULL,
  `apto_vegano` tinyint(1) NOT NULL,
  `peso` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `plato`
--

INSERT INTO `plato` (`id`, `calorias`, `apto_celiaco`, `apto_vegano`, `peso`) VALUES
(1, 800, 1, 0, 300),
(2, 700, 0, 0, 250),
(3, 600, 1, 0, 200),
(4, 400, 1, 1, 150),
(6, 500, 1, 1, 200),
(7, 900, 0, 0, 350),
(8, 300, 1, 1, 100),
(9, 1000, 0, 0, 400),
(11, 850, 0, 0, 0.4),
(12, 600, 1, 1, 0.3),
(14, 250, 1, 0, 0.2),
(15, 700, 0, 1, 0.5),
(16, 200, 1, 0, 0.1),
(18, 150, 1, 1, 0.25);

-- --------------------------------------------------------

--
-- Table structure for table `tiene_categoria`
--

CREATE TABLE `tiene_categoria` (
  `id_item_menu` int NOT NULL,
  `nombre_categoria` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tiene_categoria`
--

INSERT INTO `tiene_categoria` (`id_item_menu`, `nombre_categoria`) VALUES
(16, 'Aperitivo'),
(13, 'Bebida'),
(17, 'Bebida'),
(5, 'Cafe'),
(10, 'Cafe'),
(8, 'China'),
(1, 'Italiana'),
(7, 'Italiana'),
(9, 'Mariscos'),
(3, 'Mexicana'),
(14, 'Postre'),
(11, 'Principal'),
(12, 'Principal'),
(15, 'Principal'),
(18, 'Principal'),
(2, 'Rapida'),
(6, 'Sandwich'),
(1, 'Tradicional'),
(4, 'Vegana');

-- --------------------------------------------------------

--
-- Table structure for table `vendedor`
--

CREATE TABLE `vendedor` (
  `id` int NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `direccion` varchar(80) NOT NULL,
  `cx` double NOT NULL,
  `cy` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `vendedor`
--

INSERT INTO `vendedor` (`id`, `nombre`, `direccion`, `cx`, `cy`) VALUES
(1, 'Carlos Fernandez', 'Av. Siempre Viva 123', 40.7128, -74.006),
(2, 'Laura Gomez', 'Calle Falsa 742', 34.0522, -118.2437),
(3, 'Pedro Perez', 'Av. Los Robles 456', 51.5074, -0.1278),
(4, 'Ana Lopez', 'Calle Nueva 789', 48.8566, 2.3522),
(5, 'Miguel Ortega', 'Paseo Colon 101', 40.4168, -3.7038),
(6, 'Lucia Rodriguez', 'Calle Real 555', 52.52, 13.405),
(7, 'Jose Ramirez', 'Calle Principal 333', 35.6895, 139.6917),
(8, 'Sofia Martinez', 'Av. Del Sol 202', 41.9028, 12.4964),
(9, 'Tomas Gomez', 'Av. Libertad 11', 55.7558, 37.6173),
(10, 'Elena Ruiz', 'Calle Estrella 999', 37.7749, -122.4194),
(21, 'La Esquina de Juan', 'Av. Aristobulo del Valle 3500', -31.6333, -60.7),
(22, 'Delicias de Maria', 'Bv. Pellegrini 2900', -31.625, -60.69),
(23, 'Sabores de Italia', 'San Martin 2400', -31.6243, -60.693),
(24, 'Parrilla El Fogon', 'Av. Freyre 1900', -31.629, -60.7005),
(25, 'gsdfhdsfh', 'sfdhsdfh', 1, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bebida`
--
ALTER TABLE `bebida`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`nombre`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `item_menu`
--
ALTER TABLE `item_menu`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_vendedor` (`id_vendedor`);

--
-- Indexes for table `item_pedido`
--
ALTER TABLE `item_pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_item_menu` (`id_item_menu`),
  ADD KEY `id_pedido` (`id_pedido`);

--
-- Indexes for table `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_vendedor` (`id_vendedor`);

--
-- Indexes for table `plato`
--
ALTER TABLE `plato`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tiene_categoria`
--
ALTER TABLE `tiene_categoria`
  ADD PRIMARY KEY (`id_item_menu`,`nombre_categoria`),
  ADD KEY `nombre_categoria` (`nombre_categoria`);

--
-- Indexes for table `vendedor`
--
ALTER TABLE `vendedor`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `item_menu`
--
ALTER TABLE `item_menu`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `item_pedido`
--
ALTER TABLE `item_pedido`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `vendedor`
--
ALTER TABLE `vendedor`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bebida`
--
ALTER TABLE `bebida`
  ADD CONSTRAINT `bebida_ibfk_1` FOREIGN KEY (`id`) REFERENCES `item_menu` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `item_menu`
--
ALTER TABLE `item_menu`
  ADD CONSTRAINT `item_menu_ibfk_1` FOREIGN KEY (`id_vendedor`) REFERENCES `vendedor` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `item_pedido`
--
ALTER TABLE `item_pedido`
  ADD CONSTRAINT `item_pedido_ibfk_1` FOREIGN KEY (`id_item_menu`) REFERENCES `item_menu` (`id`) ON DELETE SET NULL,
  ADD CONSTRAINT `item_pedido_ibfk_2` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`) ON DELETE SET NULL,
  ADD CONSTRAINT `pedido_ibfk_2` FOREIGN KEY (`id_vendedor`) REFERENCES `vendedor` (`id`) ON DELETE SET NULL;

--
-- Constraints for table `plato`
--
ALTER TABLE `plato`
  ADD CONSTRAINT `plato_ibfk_1` FOREIGN KEY (`id`) REFERENCES `item_menu` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `tiene_categoria`
--
ALTER TABLE `tiene_categoria`
  ADD CONSTRAINT `tiene_categoria_ibfk_1` FOREIGN KEY (`id_item_menu`) REFERENCES `item_menu` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `tiene_categoria_ibfk_2` FOREIGN KEY (`nombre_categoria`) REFERENCES `categoria` (`nombre`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
