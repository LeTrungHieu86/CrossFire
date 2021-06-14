-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: crossfire_db
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tblproduct`
--

DROP TABLE IF EXISTS `tblproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblproduct` (
  `PRODUCT_CODE` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `PRODUCT_TITLE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `PRODUCT_VIP_INGAME_LEVEL` int unsigned DEFAULT NULL,
  `PRODUCT_VIP_NUMBER` int unsigned DEFAULT NULL,
  `PRODUCT_IMAGE_ID` int unsigned NOT NULL,
  `PRODUCT_INFO` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `PRODUCT_VIP_INGAME_IMAGE` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PRODUCT_IMAGE` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PRODUCT_PRICE` int unsigned DEFAULT NULL,
  `PRODUCT_USER_ADD` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PRODUCT_USER_UPDATE` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PRODUCT_CREATE_DATE` datetime DEFAULT NULL,
  `PRODUCT_UPDATE_DATE` datetime DEFAULT NULL,
  `PRODUCT_DELETE_FLAG` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`PRODUCT_CODE`,`PRODUCT_IMAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblproduct`
--

LOCK TABLES `tblproduct` WRITE;
/*!40000 ALTER TABLE `tblproduct` DISABLE KEYS */;
INSERT INTO `tblproduct` VALUES ('MT001','chuyên C5',5,2,3,'Thông tin tài khoản trắng nhé','','repository-open-graph-template.png',500678,'admin','admin','2021-06-02 16:38:43','2021-06-02 17:58:01',0),('MT001','chuyên C5',5,2,6,'Thông tin tài khoản trắng nhé','','Air-Bonsai-02.jpg',500678,'admin','admin','2021-06-02 16:38:43','2021-06-02 17:58:01',0);
/*!40000 ALTER TABLE `tblproduct` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-15  0:08:08
