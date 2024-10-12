-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: fantasy_team
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `username` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Alex','alex1@example.com','alex1','$2b$12$.LsiECFfrPVpbmO936LGGOiTa1JwSYymO3xRvw0zUau7H3jLWiCQS'),(2,'Jesse','jesse2@example.com','jesse2','$2b$12$TpztKRBVsZo8u2Van/whlu8Fug62sOwYH9OeV41CMBq935SrpIdcO'),(3,'Garrett','garrett3@example.com','garrett3','$2b$12$Omz4QfrABQhVrhwOSsBtDeMSC4u89ynGraaHTkt0z6Rb/4b6SBaQe'),(4,'Ann','ann4@example.com','ann4','$2b$12$w.NRT89v5w416TsDBejWieDtPQ.yW8x5YQ28G/fpiNvD.EIQk9yrG'),(5,'Donald','donald5@example.com','donald5','$2b$12$HN1iMk/aaRQaDhjAt5m.OulUVcVsALf0iQXIpjhvm1sIWo5IJKutO'),(6,'Leonard','leonard6@example.com','leonard6','$2b$12$jENtvwYduhLh7ay3qwWGoOFXO5iW1J0vjcPQsebUBa2kpzceHXVkG'),(7,'Douglas','douglas7@example.com','douglas7','$2b$12$a0dgSe6FImaNOUiwkpH67.Em0jf3UXgJBtQq0IOhPHYMSdEGC7qZ6'),(8,'Samantha','samantha8@example.com','samantha8','$2b$12$rNhmpvvY6eH5zGxK/u5t5eYc0NAU9Pe80IkeobosLvzArVif7Nc1O'),(9,'Carolyn','carolyn9@example.com','carolyn9','$2b$12$gOPUgJtQOok/iHaJ9D6Z0.q23Qx7oTE8PI5a/VKWDyAo61WgEvtoi'),(10,'Colin','colin10@example.com','colin10','$2b$12$rjSZczx6jADD9jHsmGaw5.KuZh2Ui9RCz9MCdxQqljE1zgFRgJlou'),(11,'Joseph','joseph11@example.com','joseph11','$2b$12$u0HrWGhOLrQIrq28dvsc2O0GdRTtwsEPqNIjbJxI08y.2jrM5T6.K'),(12,'Andrew','andrew12@example.com','andrew12','$2b$12$zuxxazzuTJwM.9SruFbzHeXS7kXleuKarsfe7.yTkVqL8Q9FnRq2.'),(13,'Richard','richard13@example.com','richard13','$2b$12$GHUSQoWlqKpZBQbqpa39V.RtK0tiEHT2OnRj.b7pfM4gL3A8P.rCO'),(14,'Lisa','lisa14@example.com','lisa14','$2b$12$1.UvI6q.UmYZXCkWUZN17edFyAeC2e0Cqgk2Fs3LYmebjLfcFO3A.'),(15,'Shelby','shelby15@example.com','shelby15','$2b$12$qZON1080ut54HVF3UXFjA.33ngizTeyvUEVVhmMzAk5fsUNgMhn9m'),(16,'Candace','candace16@example.com','candace16','$2b$12$nUx487H2/wZNE5FI6ANaN.ZYxVCN/YhhYXWOnZ06JkHDDfIPikjbC'),(17,'Travis','travis17@example.com','travis17','$2b$12$EkWadXHNbxqjeU6DyVEkwuseNJGtjRbD.PHNrcI7sy7td2OVJcl/S'),(18,'Anthony','anthony18@example.com','anthony18','$2b$12$D7OY4EYVyISW.cP2Nf0NluIoPD3.5EhAwJ5sm1bFRHntJNlPbcvyq'),(19,'Michael','michael19@example.com','michael19','$2b$12$BT.YozjESrl0RU2krrLQguiykMFdtGGmTkkH7lI.4LqwsChAF/M0a'),(20,'Jesse','jesse20@example.com','jesse20','$2b$12$Hwu9vvU9E3kKQH3MiTDDmO/.y3rjiaV40B6sXgX5FXifbsbeWtjAy'),(21,'Steven','steven21@example.com','steven21','$2b$12$saCj3VlofnDAX.GB1KNx2ugYgQHhhjg1TM/EuIkWG4fd43uRfhNMa'),(22,'Christopher','christopher22@example.com','christopher22','$2b$12$rWOpOVL2VkdQH7zOYJE/g.NpAWj6hdkJ3eNUF4ga0FVNsfpIBN9..'),(23,'Christina','christina23@example.com','christina23','$2b$12$5eR6ow3A9CN05SpBPNQrWOnzYya3uCPYa73HkURfDyNdH25zmfhjm'),(24,'Erica','erica24@example.com','erica24','$2b$12$3uCeRWY4DftQ8lnCf.6xKOwHFPW.8VOOWpUNoHGJHrp64ZsBtogTi'),(25,'Alejandro','alejandro25@example.com','alejandro25','$2b$12$T7g7OIzAOzQuLK2tzreyZurOurtXjje9hR2o4qyT0fYtgjmWcp4pm'),(26,'Angela','angela26@example.com','angela26','$2b$12$KrIdN1w3QKWbR/H2fGcr7OV83UOuDgbaVZg79nFegr6ROdrmKzRXe'),(27,'Charles','charles27@example.com','charles27','$2b$12$Mc/lORmCbnHMZTDvjtpZiOh0aljqsL5QCbwcIMUloHTJiLUw76lJ6'),(28,'James','james28@example.com','james28','$2b$12$bv3mQdF.Kn/kNxxUYwqPoujlNapKOU9oRF/iYCYDoM94y29jUJ.UK'),(29,'Shelly','shelly29@example.com','shelly29','$2b$12$1Y.mnnyijLRpvGs9WZluxObLlcEQXJzzKgQEQF6DdJqDVKJjIO1r2'),(30,'William','william30@example.com','william30','$2b$12$ZYulhjEu8MCVri9ggeqkwuxE5gD3usOTzwtHknnZBwORNAxSpbu8K'),(31,'Keith','keith31@example.com','keith31','$2b$12$ai6oLYvDuC.jwAjhKpsGq.X0DisFla8oxLwzmPQPj.CR3TnHWkpbe'),(32,'Kiara','kiara32@example.com','kiara32','$2b$12$S4bLD5V6rWqigeUH1hu6Au8EMktrNMGYK1vFKPn1.rIj9nH0Brinq'),(33,'Dalton','dalton33@example.com','dalton33','$2b$12$TNaGFKZyU3QowDVnm1K/fOmoI49Uv7bnr8pCr7qXuohWhdatgBn8O'),(34,'Hector','hector34@example.com','hector34','$2b$12$nXuzolq3zUlkTExBrr2ae.8BbkYKFHmVJmPUyNNWxfB9jUg7uiEgO'),(35,'Brent','brent35@example.com','brent35','$2b$12$p2a1Tdj5eoKkM9/..xFwWuAHAgM4aiLidzAM31ZhJWf80HH4SqFJO'),(36,'Andrew','andrew36@example.com','andrew36','$2b$12$XuzeYaDtgmuUaeJp5ZBiB.Y8YJDpgYES79h1BvpjbW4xEPDTlD60i'),(37,'Danielle','danielle37@example.com','danielle37','$2b$12$wlHtvELiWUUZcv7al5LL1e7eCeog6eI2/grpWRwvoEMlk8s.PsTRy'),(38,'Michelle','michelle38@example.com','michelle38','$2b$12$M8BV20YG3qwPjc7JC.tT8e5m851m8Q1G0DkClyVN0UtomHQ2Al1cW'),(39,'Beverly','beverly39@example.com','beverly39','$2b$12$ax2ef8Ivbh3rOE/0fS.6au9l4TTp2BDQ4no0tW2hzvcdvYbCdzPvK'),(40,'Ronald','ronald40@example.com','ronald40','$2b$12$7KtOvACWcn/ZgkVoUBns6uil/iuHEgxMu3thodIwL7qLxqoFV.fBy'),(41,'Casey','casey41@example.com','casey41','$2b$12$9TFiZjKvRqvgrqsMOS6MQ.va1loRgDsbQjjBmAS6azIkbuPbQz0e.'),(42,'David','david42@example.com','david42','$2b$12$Jw972744/OxXHTNHR9AHduK1v1i5NQKzQkqiYSvjQ9idyhluyJnCS'),(43,'Keith','keith43@example.com','keith43','$2b$12$.87XKakFjuRDiQahkEHogedy69w99dK0cKxNkaUqfSmqbvXr5eyjK'),(44,'Nicole','nicole44@example.com','nicole44','$2b$12$y7iLiH8mW8LlvBmheC9CCeAOq0mVbNuhSKKH/YlEKLBgVqD0A4MAi'),(45,'Michelle','michelle45@example.com','michelle45','$2b$12$WsSbAJs9QzokIIzivb1Ive6nHjROs6CRK0c85OA/GzBz2JWI0Kw1u'),(46,'Alyssa','alyssa46@example.com','alyssa46','$2b$12$tv9lyXE4mgG0quIpe7nksuhHqY7831JNjBFSEGhpadSM.CfnX.SUm'),(47,'Kimberly','kimberly47@example.com','kimberly47','$2b$12$dnKCo68bhA2RLTbQ6vhjP.lVc/p.RquDBq53pVmYQG.I6dur0MqFa'),(48,'Erin','erin48@example.com','erin48','$2b$12$gGltmFw/MdZU5kmgY/YnLuRv4IobhuMjUgF4vbPTY1ZKqf4ZV0t1q'),(49,'Lawrence','lawrence49@example.com','lawrence49','$2b$12$sjoS1RIK8Xk9aXHfOmZD6etLRxA/M3B.g7HKmXQKow9.R7s4iv.yi'),(50,'William','william50@example.com','william50','$2b$12$XgKNImrfEy3KMIce/8zLnuH.W2jD.x7uyUmhY3pk5abU83Prd5g9e'),(51,'Dana','dana51@example.com','dana51','$2b$12$BFyzxT6jI8FcOWefv4SfYO0LSLXGwQEDX38MMA01PrUzUG9YRgC16'),(52,'Philip','philip52@example.com','philip52','$2b$12$1rgKULDjecZZLhtCmEtV9esQ7l1iBbD2n3WtQtditId/IXECSHMy.'),(53,'Anthony','anthony53@example.com','anthony53','$2b$12$/J8pOI187ouslUB5rRtYNO6do83C3J/MB6/zywGRNBHAEoA6QFC82'),(54,'Debra','debra54@example.com','debra54','$2b$12$sBSemUnLXU2OuG2yZPf6reMIPg6Oaz3fjNtTJ3ACa3xIcJnHtMjN6'),(55,'Lori','lori55@example.com','lori55','$2b$12$wfhDe0b3tfHDWX/wI3sGTeDaSahulxpkvwWAUu9/vdnTas4xMSwDS'),(56,'Katherine','katherine56@example.com','katherine56','$2b$12$Ufba3r4C/djrF7k8ib8Y5.RnXDyCGsul4FtvLahubJdUBGLX2hQD6'),(57,'Joshua','joshua57@example.com','joshua57','$2b$12$yc/LKgKiW1WeH6DMHenB8eKUWpqREGQ1mtdJsoS45JlCWB1sF7.OO'),(58,'Bryan','bryan58@example.com','bryan58','$2b$12$.tMKzC01QMC6ztElEXfo5.B89w.xUE6GB5G14KFRNFKs12VrDPQ2C'),(59,'Deanna','deanna59@example.com','deanna59','$2b$12$Gd7V61qs5omgn4xpxqrBUeiufSIxkFBTnU.xUTvLYR9HKmSrZ6E5y'),(60,'Brittany','brittany60@example.com','brittany60','$2b$12$dPNc66JfIuuOJifcYLdIC.7j05pvpGQvxtWLS30byM.0W8vcV3OSK'),(61,'Terri','terri61@example.com','terri61','$2b$12$3aA3tyZh1V3MWdapPzzIVuwWtbc.AksMpQ1YF9IIJ3FXICw.nh0uG'),(62,'William','william62@example.com','william62','$2b$12$kzGIQ9PUeHc6TqKS2587vuDWDC/g7j760FhJm1wU7q9c3Z7nnPoX2'),(63,'Debra','debra63@example.com','debra63','$2b$12$9ZrYSjVMsa.5Lj/lUEsMzeE5S/L5Ln438uNowVlVtebbLwFmYawfO'),(64,'Larry','larry64@example.com','larry64','$2b$12$I8cczIvQS2sgxMObTkUyd.G.LGTiQ8CxTtF/.oK4180IUObv4TpdK'),(65,'Ellen','ellen65@example.com','ellen65','$2b$12$DCMreaXlXWTINAotxKwcaO0V/XvWOdKmGDZRvyeixLuw/7vBXY3Om'),(66,'Stephanie','stephanie66@example.com','stephanie66','$2b$12$XKnKaf1UTdaR80G7PyrIuuOsbaKwDYMO8mshKZxN2vxbmmutuKGS6'),(67,'Craig','craig67@example.com','craig67','$2b$12$TK3YekGUN7aGQJLFFSZ87utmGZqn4lhASAEhESdpnnTLoSQs7b7cy'),(68,'Christopher','christopher68@example.com','christopher68','$2b$12$a/Cu5Jp.miHm9eQ8FCCWeuZyBFYaL1qvvHLxysREpbzvLNqOm25ge'),(69,'Michael','michael69@example.com','michael69','$2b$12$wAvIXJQUQJGgB5cIr4L.1u7MWEddRaASZShbJdgKEzCAyBvGUgGUK'),(70,'Benjamin','benjamin70@example.com','benjamin70','$2b$12$VN6b4mOsPsoh2XcD5xMEYeRcIvoj4u5B5GzA4CwgkWuzqrpl17HXO'),(71,'Kathleen','kathleen71@example.com','kathleen71','$2b$12$IkGR3BdsMtlBqTwVcDgStONo/bBNr0pKG8p4jRhOGbrWQASgBbTeK'),(72,'Brandon','brandon72@example.com','brandon72','$2b$12$VL1Ju18IH7nDA0.eW0Ha0OgLi.dx672Yqsa2SJYSaOg4Ol3DPyrFS'),(73,'Brooke','brooke73@example.com','brooke73','$2b$12$8HXwKNpFnqIy5LjHULpRsePDgs1xqEOSgIrF/4GN2UKb1pQAX.6KO'),(74,'David','david74@example.com','david74','$2b$12$IKp1LHVJQ5y8fqBlOiVA6OoM0Qehj0RqIgjc50mjPTKLvjyzsVSF6'),(75,'Joseph','joseph75@example.com','joseph75','$2b$12$L5lorSJv7PIGqbyOFpjs6uvzY/oj27VWeOpNqDFM6lW8oK9FdvANW'),(76,'Christine','christine76@example.com','christine76','$2b$12$VmAHL/vfc9yZAv2HxGULS.JEZbdjfU6QlAdqAtosvpMsNDobbC/RK'),(77,'Phillip','phillip77@example.com','phillip77','$2b$12$Ed6ypbfKU0.JKSMNZRnbN.e9h5RXDDAOlUchO2BW245S4T0HQP/lq'),(78,'Richard','richard78@example.com','richard78','$2b$12$YmMkZ8SHm79jOKJBZGHjBOIEc.B88IXZloIjQNNX8vYJZ0CnHU1lC'),(79,'Angela','angela79@example.com','angela79','$2b$12$zss4UroNtOicepzvqBykXOugfNjoA/jM/We9pLLD.hjgX1SuH2jtu'),(80,'Colleen','colleen80@example.com','colleen80','$2b$12$Io.Y/JqEtVOhONOgyvwT..gy7pnCeuxidTPMA1GR35R6aP5rUK78m'),(81,'Robert','robert81@example.com','robert81','$2b$12$jtm72kAE08jCkap0e2nzF.xtQ96vUsPs0CxFkfPBPjC5OQuGXS52.'),(82,'Erica','erica82@example.com','erica82','$2b$12$5w4a5TY3npEobWDD6jg92e700u.JP08uPQFibj7rOql8NMePkR2PO'),(83,'Greg','greg83@example.com','greg83','$2b$12$Yh2IwJRIq3hSuMeawiYOh.v.FSNAES49xXpwseRNXoSjpIbi2VZEm'),(84,'Lindsey','lindsey84@example.com','lindsey84','$2b$12$PHNnqeo76N5J8z4z2A9Mc.q0EzG.5sziv6kMd5MCy9o.b.tQHIi6i'),(85,'Elizabeth','elizabeth85@example.com','elizabeth85','$2b$12$siZDUW6RBKMVd0YfQhZo7uGZ6YL7XV0C4GhJwXA.DhPYKsC9XwAca'),(86,'Jacob','jacob86@example.com','jacob86','$2b$12$7q4tYIWDIyzaYBw1jmTZeOoaGRbrzbSUYrRDytWjFX328NOmt/LbW'),(87,'Vanessa','vanessa87@example.com','vanessa87','$2b$12$9HcCVsJaP9/QjkBrLQpmfOqG11JDghIWviZ8OE.OoeH8oKpFDpyKS'),(88,'David','david88@example.com','david88','$2b$12$xu8vHXPPH/oSY9G6uhtt5.3Eq8mnpRnau4vTfzhaA/oLPxsvP1ujC'),(89,'Elizabeth','elizabeth89@example.com','elizabeth89','$2b$12$gSUEO1KdKESiXW9lR/NTcOfFUBfWsV.Ui.3V7qzuENpD5QhHyM7JW'),(90,'Spencer','spencer90@example.com','spencer90','$2b$12$gjTRg8N.ceQBMn68fZDV6eOxwbS9c/gbQWtYkp/hRCyVbiexEDnma'),(91,'Lauren','lauren91@example.com','lauren91','$2b$12$3ko0xTx1TA1EPR9dKdi1puoxQVX6BmG5SAAS5zHZIJzawRvwU0QvW'),(92,'Aaron','aaron92@example.com','aaron92','$2b$12$YlulAjY5a14O1lGfCPWsouXa3GEKXrvEdwzFvMzb06lfd6BnKbm2K'),(93,'Jennifer','jennifer93@example.com','jennifer93','$2b$12$ayd8Y0fq3sg52b9KDS1fNOsqBEhtQMpqerbjkr8kUccXJEvfBx47m'),(94,'Erica','erica94@example.com','erica94','$2b$12$lMX33l6DxHHhbHh7Ys46qOilVGtgdnvaDWwaDJehCu.PDr7YZWxqu'),(95,'Jacob','jacob95@example.com','jacob95','$2b$12$Ltq00yCIBh2tUMMaQTtpY.iE3NPT1/g/q2FA1Knhh2vrLkH06sebO'),(96,'Tammy','tammy96@example.com','tammy96','$2b$12$LBt10WY6MHaCaG8r61S6ceoCnQvPxTAMsOQ4mg5HU1wfL3WRnC7Ui'),(97,'Joshua','joshua97@example.com','joshua97','$2b$12$UJnaVz8Io9qR7heY.lyiXuWd9rXX5GmxPGICjP2EmaabZYx8e0eHO'),(98,'Jennifer','jennifer98@example.com','jennifer98','$2b$12$.hz/aEkTfSJG/nqvXAGW8e3O2sjBhU/ymaNB2eOha25hwEaemPsRO'),(99,'Susan','susan99@example.com','susan99','$2b$12$6VWpEUbgId0UqO7wwF/FAOX.fpmaHKFdRLnh.4FwZtZl9A5Y1RTGK'),(100,'Christina','christina100@example.com','christina100','$2b$12$L3X7/7SsbsGckQJBz/APdu8D83XQbOahcIolvO5O/sG9llsECjfgO');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-12 23:50:46