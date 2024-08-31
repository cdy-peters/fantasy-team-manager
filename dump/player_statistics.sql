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
-- Table structure for table `player_statistics`
--

DROP TABLE IF EXISTS `player_statistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player_statistics` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `player_name` VARCHAR(255),
    `nation` VARCHAR(255),
    `position` VARCHAR(50),
    `age` INT,
    `minutes_played` INT,
    `starts` INT,
    `minutes` INT,
    `90s`  DECIMAL(5,2),
    `goals` INT,
    `assists` INT,
    `g+a` INT,
    `g+pk` INT,
    `penalty_kicks` INT,
    `penalty_kick_attempts` INT,
    `yellow_cards` INT,
    `red_cards` INT,
    `expected_goals` DECIMAL(5,2),
    `non_penalty_expected_goals` DECIMAL(5,2),
    `expected_assists` DECIMAL(5,2),
    `npXg+XA` DECIMAL(5,2),
    `proggresive_carries` INT,
    `proggresive_passes` INT,
    `proggresive_runs` INT,
    `goals_90` DECIMAL(5,2),
    `assists_90` DECIMAL(5,2),
    `g+a_90` DECIMAL(5,2),
    `g+pk_90` DECIMAL(5,2),
    `g+a-pk_90` DECIMAL(5,2),
    `xG_90` DECIMAL(5,2),
    `xA_90` DECIMAL(5,2),
    `xG+xA_90` DECIMAL(5,2),
    `npxG_90` DECIMAL(5,2),
    `npxG+xA_90` DECIMAL(5,2),
    `team` VARCHAR(255),
    -- Add other fields as per the CSV file structure
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Dump completed on 2024-08-30 15:09:26
