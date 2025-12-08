-- MySQL dump 10.13  Distrib 5.7.35, for Linux (x86_64)
--
-- Host: localhost    Database: Reconnect
-- ------------------------------------------------------
-- Server version	5.7.35-0ubuntu0.18.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Habitacion`
--

DROP TABLE IF EXISTS `Habitacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Habitacion` (
  `id_habitacion` int(11) NOT NULL AUTO_INCREMENT,
  `num_habitacion` tinyint(4) NOT NULL,
  `fk_numPasillo` int(11) DEFAULT NULL,
  `fk_nombreEmocion` varchar(50) DEFAULT NULL,
  `abierta` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id_habitacion`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Habitacion`
--

LOCK TABLES `Habitacion` WRITE;
/*!40000 ALTER TABLE `Habitacion` DISABLE KEYS */;
INSERT INTO `Habitacion` VALUES (1,1,1,'Felicidad',0),(2,12,2,'ALEGRIA',1),(3,12,2,'ALEGRIAS',1);
/*!40000 ALTER TABLE `Habitacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Localizacion`
--

DROP TABLE IF EXISTS `Localizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Localizacion` (
  `fk_objeto` int(11) NOT NULL,
  `fk_habitacion` int(11) NOT NULL,
  `posicion_x` tinyint(4) DEFAULT NULL,
  `posicion_y` tinyint(4) DEFAULT NULL,
  `interactuable` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`fk_objeto`,`fk_habitacion`),
  KEY `fk_habitacion` (`fk_habitacion`),
  CONSTRAINT `Localizacion_ibfk_1` FOREIGN KEY (`fk_objeto`) REFERENCES `Objeto` (`id_objeto`) ON DELETE CASCADE,
  CONSTRAINT `Localizacion_ibfk_2` FOREIGN KEY (`fk_habitacion`) REFERENCES `Habitacion` (`id_habitacion`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Localizacion`
--

LOCK TABLES `Localizacion` WRITE;
/*!40000 ALTER TABLE `Localizacion` DISABLE KEYS */;
INSERT INTO `Localizacion` VALUES (3,2,4,1,1),(17,2,4,2,1),(17,3,4,2,1);
/*!40000 ALTER TABLE `Localizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Objeto`
--

DROP TABLE IF EXISTS `Objeto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Objeto` (
  `id_objeto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_objeto` varchar(255) NOT NULL,
  `archivo_objeto` text,
  `descripcion` text,
  `alto` tinyint(4) DEFAULT '1',
  `ancho` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id_objeto`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Objeto`
--

LOCK TABLES `Objeto` WRITE;
/*!40000 ALTER TABLE `Objeto` DISABLE KEYS */;
INSERT INTO `Objeto` VALUES (3,'MESA','','DESCRIPCION DESCRIPTIVA',1,3),(7,'MESA','','DESCRIPCION DESCRIPTIVA',1,3),(17,'Aina','asdf','THE BEST',1,1);
/*!40000 ALTER TABLE `Objeto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-07 13:49:44
