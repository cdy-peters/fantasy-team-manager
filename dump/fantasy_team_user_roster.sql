CREATE DATABASE  IF NOT EXISTS `fantasy_team` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fantasy_team`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: fantasy_team
-- ------------------------------------------------------
-- Server version	9.0.1

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
-- Table structure for table `user_roster`
--

DROP TABLE IF EXISTS `user_roster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roster` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `position1_player_id` int DEFAULT NULL,
  `position2_player_id` int DEFAULT NULL,
  `position3_player_id` int DEFAULT NULL,
  `position4_player_id` int DEFAULT NULL,
  `position5_player_id` int DEFAULT NULL,
  `position6_player_id` int DEFAULT NULL,
  `position7_player_id` int DEFAULT NULL,
  `position8_player_id` int DEFAULT NULL,
  `position9_player_id` int DEFAULT NULL,
  `position10_player_id` int DEFAULT NULL,
  `position11_player_id` int DEFAULT NULL,
  `sub1_player_id` int DEFAULT NULL,
  `sub2_player_id` int DEFAULT NULL,
  `sub3_player_id` int DEFAULT NULL,
  `sub4_player_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_id` (`user_id`),
  KEY `fk_position1_player_id` (`position1_player_id`),
  KEY `fk_position2_player_id` (`position2_player_id`),
  KEY `fk_position3_player_id` (`position3_player_id`),
  KEY `fk_position4_player_id` (`position4_player_id`),
  KEY `fk_position5_player_id` (`position5_player_id`),
  KEY `fk_position6_player_id` (`position6_player_id`),
  KEY `fk_position7_player_id` (`position7_player_id`),
  KEY `fk_position8_player_id` (`position8_player_id`),
  KEY `fk_position9_player_id` (`position9_player_id`),
  KEY `fk_position10_player_id` (`position10_player_id`),
  KEY `fk_position11_player_id` (`position11_player_id`),
  KEY `fk_sub1_player_id` (`sub1_player_id`),
  KEY `fk_sub2_player_id` (`sub2_player_id`),
  KEY `fk_sub3_player_id` (`sub3_player_id`),
  KEY `fk_sub4_player_id` (`sub4_player_id`),
  CONSTRAINT `fk_sub1_player_id` FOREIGN KEY (`sub1_player_id`) REFERENCES `player_statistics` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_sub2_player_id` FOREIGN KEY (`sub2_player_id`) REFERENCES `player_statistics` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_sub3_player_id` FOREIGN KEY (`sub3_player_id`) REFERENCES `player_statistics` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_sub4_player_id` FOREIGN KEY (`sub4_player_id`) REFERENCES `player_statistics` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_position10_player_id` FOREIGN KEY (`position10_player_id`) REFERENCES `player_statistics` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_position11_player_id` FOREIGN KEY (`position11_player_id`) REFERENCES `player_statistics` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_position1_player_id` FOREIGN KEY (`position1_player_id`) REFERENCES `player_statistics` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_position2_player_id` FOREIGN KEY (`position2_player_id`) REFERENCES `player_statistics` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_position3_player_id` FOREIGN KEY (`position3_player_id`) REFERENCES `player_statistics` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_position4_player_id` FOREIGN KEY (`position4_player_id`) REFERENCES `player_statistics` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_position5_player_id` FOREIGN KEY (`position5_player_id`) REFERENCES `player_statistics` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_position6_player_id` FOREIGN KEY (`position6_player_id`) REFERENCES `player_statistics` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_position7_player_id` FOREIGN KEY (`position7_player_id`) REFERENCES `player_statistics` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_position8_player_id` FOREIGN KEY (`position8_player_id`) REFERENCES `player_statistics` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_position9_player_id` FOREIGN KEY (`position9_player_id`) REFERENCES `player_statistics` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roster`
--

LOCK TABLES `user_roster` WRITE;
/*!40000 ALTER TABLE `user_roster` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_roster` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-31 17:27:48
