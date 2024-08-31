-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: test
-- ------------------------------------------------------
-- Server version 9.0.1

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
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_position1_player_id` FOREIGN KEY (`position1_player_id`) REFERENCES `player_statistics` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_position2_player_id` FOREIGN KEY (`position2_player_id`) REFERENCES `player_statistics` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_position3_player_id` FOREIGN KEY (`position3_player_id`) REFERENCES `player_statistics` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_position4_player_id` FOREIGN KEY (`position4_player_id`) REFERENCES `player_statistics` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_position5_player_id` FOREIGN KEY (`position5_player_id`) REFERENCES `player_statistics` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_position6_player_id` FOREIGN KEY (`position6_player_id`) REFERENCES `player_statistics` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_position7_player_id` FOREIGN KEY (`position7_player_id`) REFERENCES `player_statistics` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_position8_player_id` FOREIGN KEY (`position8_player_id`) REFERENCES `player_statistics` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_position9_player_id` FOREIGN KEY (`position9_player_id`) REFERENCES `player_statistics` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_position10_player_id` FOREIGN KEY (`position10_player_id`) REFERENCES `player_statistics` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_position11_player_id` FOREIGN KEY (`position11_player_id`) REFERENCES `player_statistics` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Dump completed on 2024-08-28 15:09:26

