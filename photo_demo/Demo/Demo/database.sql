-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: edu-demo
-- ------------------------------------------------------
-- Server version	5.7.15

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
-- Table structure for table `Photo`
--

DROP TABLE IF EXISTS `Photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Photo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `catalog` varchar(10) DEFAULT NULL,
  `descs` varchar(200) DEFAULT NULL,
  `filePath` varchar(150) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `createDt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Photo`
--

LOCK TABLES `Photo` WRITE;
/*!40000 ALTER TABLE `Photo` DISABLE KEYS */;
INSERT INTO `Photo` VALUES (11,'绿色森林','A','','1/A/1512985523283.jpg',1,'2017-12-11 17:45:23'),(12,'海上风景','A','','1/A/1512987581717.jpg',1,'2017-12-11 18:19:42'),(23,'钢铁侠1','B','','1/B/1512997065841.jpeg',1,'2017-12-11 20:57:46'),(24,'钢铁侠2','B','','1/B/1512997079739.png',1,'2017-12-11 20:58:00'),(25,'金沙','C','','1/C/1512997102394.jpg',1,'2017-12-11 20:58:22'),(27,'金沙','C','','1/C/1512997140232.jpg',1,'2017-12-11 20:59:00'),(28,'日本','A','','1/A/1512997312184.jpg',1,'2017-12-11 21:01:52'),(29,'九寨沟','A','','1/A/1512997329224.jpeg',1,'2017-12-11 21:02:09'),(30,'日落','A','','1/A/1512997344914.jpg',1,'2017-12-11 21:02:25'),(31,'红海','A','','1/A/1512997786059.jpg',1,'2017-12-11 21:09:46'),(32,'灯塔','A','','1/A/1512998095408.jpg',1,'2017-12-11 21:14:55'),(33,'上海陆家嘴夜景','C','繁华的外滩夜景，每到节日人潮人海','1/C/1513001137890.jpg',1,'2017-12-11 22:05:38'),(34,'灯塔夜景','C','在海边一处静谧的灯塔拍摄','1/C/1513045086852.jpg',1,'2017-12-12 10:18:07');
/*!40000 ALTER TABLE `Photo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(45) DEFAULT NULL,
  `nickName` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `createDt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_loginName` (`loginName`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'sean','sean','user','sean220@gmail.com','M','123','2017-12-10 00:00:00'),(2,'admin','admin','admin','admin220@gmail.com','M','123','2017-12-10 00:00:00');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-12 11:04:45
