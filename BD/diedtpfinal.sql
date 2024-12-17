-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 17, 2024 at 04:52 PM
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
-- Database: `diedtpfinal`
--

-- --------------------------------------------------------

--
-- Table structure for table `bebida`
--

CREATE TABLE `bebida` (
  `graduacion_alcoholica` float DEFAULT NULL,
  `peso` float DEFAULT NULL,
  `tamaño` float DEFAULT NULL,
  `itemid` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `bebida`
--

INSERT INTO `bebida` (`graduacion_alcoholica`, `peso`, `tamaño`, `itemid`) VALUES
(7.77, 0.7, 2, 2),
(0, 432, 500, 6);

-- --------------------------------------------------------

--
-- Table structure for table `categoria`
--

CREATE TABLE `categoria` (
  `categoriaid` bigint NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `tipo` enum('BEBIDA','PLATO') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `categoria`
--

INSERT INTO `categoria` (`categoriaid`, `nombre`, `tipo`) VALUES
(1, 'plato', 'PLATO'),
(2, 'ensalada', 'PLATO'),
(3, 'mango', 'PLATO');

-- --------------------------------------------------------

--
-- Table structure for table `cliente`
--

CREATE TABLE `cliente` (
  `latitud` double DEFAULT NULL,
  `longitud` double DEFAULT NULL,
  `clienteid` bigint NOT NULL,
  `cuit` bigint NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `cliente`
--

INSERT INTO `cliente` (`latitud`, `longitud`, `clienteid`, `cuit`, `direccion`, `email`, `nombre`) VALUES
(12, 12, 1, 527, 'jh', 'gsdf', 'Joaquin'),
(0.7, 7.2, 2, 212, 'Guadalupe 2215', 'gervasioLibarona@Mimail.irg', 'Lucio'),
(99.99, 99.99, 3, 0, 'zzzzzz', 'Zzzzzz@zzz.zzz', 'Zapata'),
(0, 0, 4, 999999, 'AAAA 000', 'AAAAAA@AAA.AAA', 'Abrazo'),
(234.534, 534.65, 5, 31234553125, 'ABC 123', 'dfgsdfhsfhsd@mail.com', 'Nicolas');

-- --------------------------------------------------------

--
-- Table structure for table `estrategias_de_pago`
--

CREATE TABLE `estrategias_de_pago` (
  `metodopagoid` bigint NOT NULL,
  `tipo_pago` varchar(31) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `estrategias_de_pago`
--

INSERT INTO `estrategias_de_pago` (`metodopagoid`, `tipo_pago`) VALUES
(1, 'MERCADOPAGO'),
(2, 'MERCADOPAGO');

-- --------------------------------------------------------

--
-- Table structure for table `item_categoria`
--

CREATE TABLE `item_categoria` (
  `categoriaid` bigint NOT NULL,
  `itemid` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `item_categoria`
--

INSERT INTO `item_categoria` (`categoriaid`, `itemid`) VALUES
(1, 4),
(2, 4),
(1, 5),
(2, 5),
(3, 5);

-- --------------------------------------------------------

--
-- Table structure for table `item_menu`
--

CREATE TABLE `item_menu` (
  `es_bebida` bit(1) DEFAULT NULL,
  `precio` float NOT NULL,
  `itemid` bigint NOT NULL,
  `vendedorid` bigint DEFAULT NULL,
  `tipo_item` varchar(31) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `item_menu`
--

INSERT INTO `item_menu` (`es_bebida`, `precio`, `itemid`, `vendedorid`, `tipo_item`, `descripcion`, `nombre`) VALUES
(b'0', 8900, 1, 1, 'Plato', '', 'Solomillo a la pimienta'),
(b'1', 1500, 2, 1, 'Bebida', '', 'Coquita'),
(b'0', 700, 3, 1, 'Plato', 'Directo de Napolia', 'Pizza Napolitana'),
(b'0', 6600, 4, 2, 'Plato', '', 'Ensalada Primavera'),
(b'0', 2200, 5, 3, 'Plato', '', 'Mango a la peperina'),
(b'1', 500, 6, 2, 'Bebida', 'sprite de medio litre', 'Sprite'),
(b'0', 1200, 7, 2, 'Plato', 'rebozada con pan sin tacc', 'milanesa de soja'),
(b'0', 1450, 8, 2, 'Plato', 'porcion regular', 'Fideos con margarina y jardinera');

-- --------------------------------------------------------

--
-- Table structure for table `item_pedido`
--

CREATE TABLE `item_pedido` (
  `cantidad` int NOT NULL,
  `precio` float NOT NULL,
  `id` bigint NOT NULL,
  `itemid` bigint DEFAULT NULL,
  `pedidoid` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `item_pedido`
--

INSERT INTO `item_pedido` (`cantidad`, `precio`, `id`, `itemid`, `pedidoid`) VALUES
(1, 8900, 1, 1, 1),
(5, 44500, 2, 1, 2),
(1, 1500, 3, 2, 2),
(2, 1400, 4, 3, 2),
(2, 3000, 5, 2, 3),
(1, 8900, 6, 1, 3),
(1, 1500, 7, 2, 4),
(1, 700, 8, 3, 4);

-- --------------------------------------------------------

--
-- Table structure for table `pago`
--

CREATE TABLE `pago` (
  `monto` double DEFAULT NULL,
  `fecha_pago` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL,
  `pedido_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pago_mercado_pago`
--

CREATE TABLE `pago_mercado_pago` (
  `metodopagoid` bigint NOT NULL,
  `alias` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pago_mercado_pago`
--

INSERT INTO `pago_mercado_pago` (`metodopagoid`, `alias`) VALUES
(1, 'asdf'),
(2, 'nico.mp');

-- --------------------------------------------------------

--
-- Table structure for table `pago_transferencia`
--

CREATE TABLE `pago_transferencia` (
  `cuit` bigint NOT NULL,
  `metodopagoid` bigint NOT NULL,
  `cbu` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pedido`
--

CREATE TABLE `pedido` (
  `cantidad` int NOT NULL,
  `precio` double NOT NULL,
  `clienteid` bigint DEFAULT NULL,
  `metodopagoid` bigint DEFAULT NULL,
  `pedidoid` bigint NOT NULL,
  `vendedorid` bigint DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `estado` enum('ENTREGADO','EN_CARRITO','EN_ENVIO','RECIBIDO') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pedido`
--

INSERT INTO `pedido` (`cantidad`, `precio`, `clienteid`, `metodopagoid`, `pedidoid`, `vendedorid`, `descripcion`, `estado`) VALUES
(0, 8900, 1, 1, 1, 1, NULL, 'RECIBIDO'),
(0, 47400, 1, NULL, 2, 1, NULL, 'EN_CARRITO'),
(3, 11900, 5, 2, 3, 1, NULL, 'RECIBIDO'),
(2, 2200, 5, NULL, 4, 1, NULL, 'EN_CARRITO');

-- --------------------------------------------------------

--
-- Table structure for table `plato`
--

CREATE TABLE `plato` (
  `apto_celiaco` bit(1) DEFAULT NULL,
  `apto_vegano` bit(1) DEFAULT NULL,
  `calorias` float DEFAULT NULL,
  `peso` float DEFAULT NULL,
  `itemid` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `plato`
--

INSERT INTO `plato` (`apto_celiaco`, `apto_vegano`, `calorias`, `peso`, `itemid`) VALUES
(b'0', b'0', 0, 0.1, 1),
(b'0', b'0', 0, 0.55, 3),
(b'0', b'0', 0, 0.15, 4),
(b'0', b'0', 0, 0.66, 5),
(b'1', b'1', 3124, 45.2, 7),
(b'0', b'1', 600, 35, 8);

-- --------------------------------------------------------

--
-- Table structure for table `vendedor`
--

CREATE TABLE `vendedor` (
  `latitud` double DEFAULT NULL,
  `longitud` double DEFAULT NULL,
  `vendedorid` bigint NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `vendedor`
--

INSERT INTO `vendedor` (`latitud`, `longitud`, `vendedorid`, `direccion`, `nombre`) VALUES
(1, 1, 1, 'Crespo 2584', 'Lucas'),
(9.99, 9.99, 2, 'Zobarola 999', 'Zamantha'),
(0, 0, 3, 'Arcoiris 0.00', 'Armani'),
(312.432, 243.5, 4, 'direc', 'Juan'),
(23.32, -31.12, 5, 'Corrientes 223', 'Matias'),
(23.354, 634.2, 6, 'saavedra 2314', 'Sofia');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bebida`
--
ALTER TABLE `bebida`
  ADD PRIMARY KEY (`itemid`);

--
-- Indexes for table `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`categoriaid`),
  ADD UNIQUE KEY `UKit6g0527u6l3wydgmn294c1i0` (`nombre`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`clienteid`);

--
-- Indexes for table `estrategias_de_pago`
--
ALTER TABLE `estrategias_de_pago`
  ADD PRIMARY KEY (`metodopagoid`);

--
-- Indexes for table `item_categoria`
--
ALTER TABLE `item_categoria`
  ADD PRIMARY KEY (`categoriaid`,`itemid`),
  ADD KEY `FKun5kcqtw2s2sv586rboqcr46` (`itemid`);

--
-- Indexes for table `item_menu`
--
ALTER TABLE `item_menu`
  ADD PRIMARY KEY (`itemid`),
  ADD KEY `FK9451aq3mm43yfaohfobhojbqp` (`vendedorid`);

--
-- Indexes for table `item_pedido`
--
ALTER TABLE `item_pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8rgwu0chpjx111dkljxg327dt` (`itemid`),
  ADD KEY `FKe4qvfgo0fap0j8jmyd0qfy5o5` (`pedidoid`);

--
-- Indexes for table `pago`
--
ALTER TABLE `pago`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKfequlwg494wsub1nc7cqiwbk6` (`pedido_id`);

--
-- Indexes for table `pago_mercado_pago`
--
ALTER TABLE `pago_mercado_pago`
  ADD PRIMARY KEY (`metodopagoid`);

--
-- Indexes for table `pago_transferencia`
--
ALTER TABLE `pago_transferencia`
  ADD PRIMARY KEY (`metodopagoid`);

--
-- Indexes for table `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`pedidoid`),
  ADD KEY `FKh8d7bcjt8gprquawf6of075p6` (`clienteid`),
  ADD KEY `FK68e76trfl76ga5f9gc6lo212m` (`metodopagoid`),
  ADD KEY `FKk1yj2me8vn6ct9u358iv2d3ce` (`vendedorid`);

--
-- Indexes for table `plato`
--
ALTER TABLE `plato`
  ADD PRIMARY KEY (`itemid`);

--
-- Indexes for table `vendedor`
--
ALTER TABLE `vendedor`
  ADD PRIMARY KEY (`vendedorid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categoria`
--
ALTER TABLE `categoria`
  MODIFY `categoriaid` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `clienteid` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `estrategias_de_pago`
--
ALTER TABLE `estrategias_de_pago`
  MODIFY `metodopagoid` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `item_menu`
--
ALTER TABLE `item_menu`
  MODIFY `itemid` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `item_pedido`
--
ALTER TABLE `item_pedido`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `pago`
--
ALTER TABLE `pago`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pedido`
--
ALTER TABLE `pedido`
  MODIFY `pedidoid` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `vendedor`
--
ALTER TABLE `vendedor`
  MODIFY `vendedorid` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bebida`
--
ALTER TABLE `bebida`
  ADD CONSTRAINT `FKlp6abxkdtkd100dnpjoifdm9h` FOREIGN KEY (`itemid`) REFERENCES `item_menu` (`itemid`);

--
-- Constraints for table `item_categoria`
--
ALTER TABLE `item_categoria`
  ADD CONSTRAINT `FKltk3b83dckbv2w22svvlubgyi` FOREIGN KEY (`categoriaid`) REFERENCES `categoria` (`categoriaid`),
  ADD CONSTRAINT `FKun5kcqtw2s2sv586rboqcr46` FOREIGN KEY (`itemid`) REFERENCES `item_menu` (`itemid`);

--
-- Constraints for table `item_menu`
--
ALTER TABLE `item_menu`
  ADD CONSTRAINT `FK9451aq3mm43yfaohfobhojbqp` FOREIGN KEY (`vendedorid`) REFERENCES `vendedor` (`vendedorid`);

--
-- Constraints for table `item_pedido`
--
ALTER TABLE `item_pedido`
  ADD CONSTRAINT `FK8rgwu0chpjx111dkljxg327dt` FOREIGN KEY (`itemid`) REFERENCES `item_menu` (`itemid`) ON DELETE CASCADE,
  ADD CONSTRAINT `FKe4qvfgo0fap0j8jmyd0qfy5o5` FOREIGN KEY (`pedidoid`) REFERENCES `pedido` (`pedidoid`) ON DELETE CASCADE;

--
-- Constraints for table `pago`
--
ALTER TABLE `pago`
  ADD CONSTRAINT `FKfequlwg494wsub1nc7cqiwbk6` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`pedidoid`);

--
-- Constraints for table `pago_mercado_pago`
--
ALTER TABLE `pago_mercado_pago`
  ADD CONSTRAINT `FKmfcd5obl5w2ai67gw56y8lkut` FOREIGN KEY (`metodopagoid`) REFERENCES `estrategias_de_pago` (`metodopagoid`);

--
-- Constraints for table `pago_transferencia`
--
ALTER TABLE `pago_transferencia`
  ADD CONSTRAINT `FKi7h7lvm5s17jndslui3bxoo9k` FOREIGN KEY (`metodopagoid`) REFERENCES `estrategias_de_pago` (`metodopagoid`);

--
-- Constraints for table `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `FK68e76trfl76ga5f9gc6lo212m` FOREIGN KEY (`metodopagoid`) REFERENCES `estrategias_de_pago` (`metodopagoid`),
  ADD CONSTRAINT `FKh8d7bcjt8gprquawf6of075p6` FOREIGN KEY (`clienteid`) REFERENCES `cliente` (`clienteid`) ON DELETE CASCADE,
  ADD CONSTRAINT `FKk1yj2me8vn6ct9u358iv2d3ce` FOREIGN KEY (`vendedorid`) REFERENCES `vendedor` (`vendedorid`) ON DELETE CASCADE;

--
-- Constraints for table `plato`
--
ALTER TABLE `plato`
  ADD CONSTRAINT `FK34gww2hysui3tou8hjh30nt7a` FOREIGN KEY (`itemid`) REFERENCES `item_menu` (`itemid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
