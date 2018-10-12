-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: server_db
-- ------------------------------------------------------
-- Server version	5.7.22-log

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
-- Table structure for table `remote_contacts`
--

DROP TABLE IF EXISTS `remote_contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `remote_contacts` (
  `PK` int(11) NOT NULL AUTO_INCREMENT,
  `User_Id` char(11) NOT NULL,
  `Emergency_Name` varchar(45) DEFAULT NULL,
  `Emergency_Contact` char(11) NOT NULL,
  PRIMARY KEY (`PK`),
  UNIQUE KEY `PK_UNIQUE` (`PK`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remote_contacts`
--

LOCK TABLES `remote_contacts` WRITE;
/*!40000 ALTER TABLE `remote_contacts` DISABLE KEYS */;
/*!40000 ALTER TABLE `remote_contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `remote_records`
--

DROP TABLE IF EXISTS `remote_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `remote_records` (
  `PK` int(255) NOT NULL,
  `User_Id` varchar(255) NOT NULL,
  `Date_Time` datetime DEFAULT NULL,
  `Location` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PK`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remote_records`
--

LOCK TABLES `remote_records` WRITE;
/*!40000 ALTER TABLE `remote_records` DISABLE KEYS */;
INSERT INTO `remote_records` VALUES (0,'04 B8 4F B2 B0 57 80','2018-10-11 18:41:48','The Regency At Salcedo Gym, Tordesillas St., Makati, 1227, Philippines'),(1,'04 B8 4F B2 B0 57 80','2018-10-11 18:54:12','The Regency At Salcedo Gym, Tordesillas St., Makati, 1227, Philippines'),(2,'04 B8 4F B2 B0 57 80','2018-10-11 19:02:43','The Regency At Salcedo Gym, Tordesillas St., Makati, 1227, Philippines'),(3,'04 B8 4F B2 B0 57 80','2018-10-11 19:08:52','The Regency At Salcedo Gym, Tordesillas St., Makati, 1227, Philippines'),(4,'04 B8 4F B2 B0 57 80','2018-10-11 19:10:47','The Regency At Salcedo Gym, Tordesillas St., Makati, 1227, Philippines'),(5,'04 B8 4F B2 B0 57 80','2018-10-11 19:11:01','The Regency At Salcedo Gym, Tordesillas St., Makati, 1227, Philippines'),(6,'04 B8 4F B2 B0 57 80','2018-10-11 19:21:32','The Regency At Salcedo Gym, Tordesillas St., Makati, 1227, Philippines'),(7,'04 B8 4F B2 B0 57 80','2018-10-11 19:35:56','The Regency At Salcedo Gym, Tordesillas St., Makati, 1227, Philippines'),(8,'04 B8 4F B2 B0 57 80','2018-10-11 19:47:30','The Regency At Salcedo Gym, Tordesillas St., Makati, 1227, Philippines'),(9,'04 B8 4F B2 B0 57 80','2018-10-11 19:49:29','One Pacific Place Showroom, Makati, 1227, Philippines'),(10,'04 B8 4F B2 B0 57 80','2018-10-11 20:08:13','Townhouse Hostel, Bayview Drive, Paranaque, 1701, Philippines'),(11,'04 B8 4F B2 B0 57 80','2018-10-11 20:08:20','Townhouse Hostel, Bayview Drive, Paranaque, 1701, Philippines'),(12,'04 7E BF B2 B0 57 80','2018-10-11 20:11:13','Townhouse Hostel, Bayview Drive, Paranaque, 1701, Philippines'),(13,'04 B8 4F B2 B0 57 80','2018-10-11 20:14:36','Smith Butcher and Grill Room, Makati, 1227, Philippines'),(14,'04 B8 4F B2 B0 57 80','2018-10-11 20:17:39','Smith Butcher and Grill Room, Makati, 1227, Philippines'),(15,'04 B8 4F B2 B0 57 80','2018-10-11 20:26:59','Smith Butcher and Grill Room, Makati, 1227, Philippines'),(16,'04 7E BF B2 B0 57 80','2018-10-11 20:27:04','Townhouse Hostel, Bayview Drive, Paranaque, 1701, Philippines'),(17,'04 7E BF B2 B0 57 80','2018-10-11 20:27:05','Townhouse Hostel, Bayview Drive, Paranaque, 1701, Philippines');
/*!40000 ALTER TABLE `remote_records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `remote_users`
--

DROP TABLE IF EXISTS `remote_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `remote_users` (
  `User_Id` char(11) NOT NULL,
  `Last_Name` varchar(255) NOT NULL,
  `First_Name` varchar(255) NOT NULL,
  PRIMARY KEY (`User_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remote_users`
--

LOCK TABLES `remote_users` WRITE;
/*!40000 ALTER TABLE `remote_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `remote_users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-12 15:11:19
