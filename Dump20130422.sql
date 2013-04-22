CREATE DATABASE  IF NOT EXISTS `users` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `users`;
-- MySQL dump 10.13  Distrib 5.6.11, for Win64 (x86_64)
--
-- Host: localhost    Database: users
-- ------------------------------------------------------
-- Server version	5.6.11

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
-- Table structure for table `userinfo`
--

DROP TABLE IF EXISTS `userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinfo`
--

LOCK TABLES `userinfo` WRITE;
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` VALUES (1,'2013-04-21 16:33:35','login 0','password0','I2M2Y1ZDg.jpg','offline'),(3,'2013-04-21 16:33:36','login 1','password1','I2M2Y1ZDg.jpg','offline'),(4,'2013-04-21 16:33:36','login 2','password2','I2M2Y1ZDg.jpg','offline'),(5,'2013-04-21 16:33:36','login 3','password3','I2M2Y1ZDg.jpg','offline'),(6,'2013-04-21 16:33:36','login 4','password4','I2M2Y1ZDg.jpg','offline'),(8,'2013-04-21 16:41:30','4v4et4vtet4vsv','4sybsybsbys7','I2M2Y1ZDg.jpg','online'),(9,'2013-04-21 16:42:59','sy5y5bs5sy','554y5sybs5by','I2M2Y1ZDg.jpg','online'),(11,'2013-04-21 16:44:07','4b46vs6','6666s4vs7s','I2M2Y1ZDg.jpg','online'),(14,'2013-04-21 16:45:26','se66bb6s5bs5b7','5bs7s57s7','I2M2Y1ZDg.jpg','online'),(15,'2013-04-21 16:47:24','4d5f6g7898jk','ed5rf6tg7yhuj','I2M2Y1ZDg.jpg','online'),(16,'2013-04-21 16:48:59','5r6t7y8hu9jlp','s5dr6ftgyuhio','I2M2Y1ZDg.jpg','online'),(18,'2013-04-21 16:50:04','sweygbuhnijmdrtfyuhijm','qwerdctrf','I2M2Y1ZDg.jpg','online'),(20,'2013-04-21 16:52:37','sydurytfgyhu;ji','sdftgyhuji','I2M2Y1ZDg.jpg','online'),(23,'2013-04-21 16:54:46','xdhzrhrgzrzh','rehrhrsehrseh','I2M2Y1ZDg.jpg','online'),(24,'2013-04-21 17:15:38','a3','6g','I2M2Y1ZDg.jpg','online'),(25,'2013-04-21 17:33:57','uftgyun;mk,l','edrftgyhujm,','I2M2Y1ZDg.jpg','online'),(26,'2013-04-21 17:34:58','d64d4ud6','ysdufigoyhp','I2M2Y1ZDg.jpg','online'),(27,'2013-04-21 17:37:35','tcytiyuppO','UYUU','I2M2Y1ZDg.jpg','online'),(28,'2013-04-21 17:48:12','trhs5yhsey','esyesy6r','I2M2Y1ZDg.jpg','online'),(29,'2013-04-21 17:54:52','ergsehsr','rsthsr5u','I2M2Y1ZDg.jpg','online'),(30,'2013-04-21 17:57:19','garehgeh','ehsehtrth','I2M2Y1ZDg.jpg','online'),(32,'2013-04-21 19:06:39','rehshsj6dsj','trhsrshsrjsrksr','I2M2Y1ZDg.jpg','online'),(34,'2013-04-21 19:10:55','frcvh;k;','cfcfgvlbhj\'l','I2M2Y1ZDg.jpg','online'),(36,'2013-04-21 19:13:05','uyb','','I2M2Y1ZDg.jpg','online'),(38,'2013-04-21 19:17:56','jjjj','kkkkk','I2M2Y1ZDg.jpg','online'),(40,'2013-04-21 19:20:38','irjjpij','ijijr[vns\'','I2M2Y1ZDg.jpg','online'),(42,'2013-04-21 19:26:18','rihfrsdoigh','ihoto;ibhdn;o','I2M2Y1ZDg.jpg','online'),(44,'2013-04-21 19:32:48','rjsion','noinvso','I2M2Y1ZDg.jpg','online'),(45,'2013-04-21 19:35:39','knlkn','\'lknkln','I2M2Y1ZDg.jpg','online'),(46,'2013-04-21 19:37:10','uhnlk','bjknm.l','I2M2Y1ZDg.jpg','online'),(48,'2013-04-21 19:40:41','trjfyq1``','sgerhskd','I2M2Y1ZDg.jpg','online'),(50,'2013-04-21 19:44:04','drfkgyh1','tfgyhuj','I2M2Y1ZDg.jpg','online'),(52,'2013-04-21 19:45:47','rdktgy.hkj','rftgyhujik','I2M2Y1ZDg.jpg','online'),(54,'2013-04-21 19:47:34','hhhh','hhhh','I2M2Y1ZDg.jpg','online'),(55,'2013-04-21 19:50:27','dnfigndori','niovrdlgnslnfors','I2M2Y1ZDg.jpg','online'),(56,'2013-04-21 19:57:18','jijfwupu','bpwifuefbwp','I2M2Y1ZDg.jpg','online'),(57,'2013-04-21 19:58:42','llllook','lllldddd[[]]','I2M2Y1ZDg.jpg','online'),(58,'2013-04-21 20:02:32','drtflgyb;kn','drfytguyhiuji','I2M2Y1ZDg.jpg','online'),(59,'2013-04-21 20:06:52','iokpm','jkf\' ','I2M2Y1ZDg.jpg','online'),(60,'2013-04-21 20:14:28','jdyjyrjtq11','rgdhstjuyk','I2M2Y1ZDg.jpg','online'),(61,'2013-04-21 20:17:10','vbnk','GYHUIJOK','I2M2Y1ZDg.jpg','online'),(65,'2013-04-21 20:30:47','s54ryctkgj,m','ehrthjfgjn,','I2M2Y1ZDg.jpg','online'),(66,'2013-04-21 20:32:07','fjyrtjr66u','4yty7if8g8i','I2M2Y1ZDg.jpg','online'),(67,'2013-04-21 20:33:06','eygnlkm;','fygihujiko','I2M2Y1ZDg.jpg','online'),(68,'2013-04-21 20:34:28','7i5ir4','tyfiuytre','I2M2Y1ZDg.jpg','online'),(69,'2013-04-21 20:35:21','uyghujik','yughuji','I2M2Y1ZDg.jpg','online'),(70,'2013-04-21 20:36:22','rftgyhujik','fygihujiko','I2M2Y1ZDg.jpg','online'),(71,'2013-04-21 20:38:41','rtgyhknmkl','ftgyhjk,.','I2M2Y1ZDg.jpg','online'),(72,'2013-04-21 20:40:27','drftgyjhkjlmk,l','edrftgyhujk,l','I2M2Y1ZDg.jpg','online'),(73,'2013-04-21 20:42:22','ftgyjkj','drtfgyh','I2M2Y1ZDg.jpg','online'),(74,'2013-04-21 20:43:27','fjyuiug','hdttyjtfk','I2M2Y1ZDg.jpg','online'),(75,'2013-04-21 20:44:48','drftgyhuji','ftyguhijok','I2M2Y1ZDg.jpg','online'),(76,'2013-04-21 20:46:11','eriuyrtr','kjhngbfvdstdyuyio','I2M2Y1ZDg.jpg','online'),(77,'2013-04-21 20:47:32','ftgyhuj','drtfyghuij','I2M2Y1ZDg.jpg','online'),(78,'2013-04-21 20:49:59','rfyguyhujik','ftugyihojik','I2M2Y1ZDg.jpg','online'),(79,'2013-04-21 20:51:14','tgbjhkjlk','fgyibunjm','I2M2Y1ZDg.jpg','online'),(80,'2013-04-21 20:53:18','drfygyhnjmk,l','wedrftgynjmk,l','I2M2Y1ZDg.jpg','online'),(81,'2013-04-21 21:08:38','hddrhd','tdhh','I2M2Y1ZDg.jpg','online'),(82,'2013-04-21 21:09:55','tjhgkthrhj','jhgghhjhvgt','I2M2Y1ZDg.jpg','online'),(83,'2013-04-21 21:11:22','rtdfytguyhuj','ftugyhu/ji','I2M2Y1ZDg.jpg','online'),(84,'2013-04-21 21:12:04','2`2','222','I2M2Y1ZDg.jpg','online'),(85,'2013-04-21 21:16:46','ftgyhujio','qwerftgyhujik','I2M2Y1ZDg.jpg','online'),(86,'2013-04-21 21:18:36','rftguyhujik','rftogyhuji','I2M2Y1ZDg.jpg','online'),(87,'2013-04-21 21:21:40','edrfyglyh;uikl','dftguyhuji','I2M2Y1ZDg.jpg','online'),(88,'2013-04-21 21:28:23','tdyrfutgyihuj','rftgyhuj','I2M2Y1ZDg.jpg','online'),(89,'2013-04-21 21:30:13','65t76yhuji','wedrfgtyhujiko','I2M2Y1ZDg.jpg','online'),(90,'2013-04-21 21:31:38','gvbhnjfyrvgb','rqtewsedtrfygkblhnj;mk\'','I2M2Y1ZDg.jpg','online'),(91,'2013-04-21 21:33:18','gtyhuuijm','wedrfgtuhiuj]\\','I2M2Y1ZDg.jpg','online'),(92,'2013-04-22 00:55:14','rdtvbnjkm','vbn','I2M2Y1ZDg.jpg','online'),(93,'2013-04-22 01:14:08','thrsbvs','sthrhsrthsr','I2M2Y1ZDg.jpg','online'),(94,'2013-04-22 01:16:06','1111','mPassword','I2M2Y1ZDg.jpg','online'),(95,'2013-04-22 01:21:35','resg','egwgrwgw','I2M2Y1ZDg.jpg','online'),(96,'2013-04-22 01:23:07','dryftgyl','rdiytfuvylbiu','I2M2Y1ZDg.jpg','online'),(97,'2013-04-22 01:24:14','tghjk','cfvbh.kjn','I2M2Y1ZDg.jpg','online'),(98,'2013-04-22 01:25:31','rfghkj','edrfktglyhuj','I2M2Y1ZDg.jpg','online'),(99,'2013-04-22 01:30:36','rrrr','eeee','I2M2Y1ZDg.jpg','online'),(108,'2013-04-22 02:02:28','gvhbjnkm','gvhbjnkml','I2M2Y1ZDg.jpg','online'),(112,'2013-04-22 02:14:18','1123','2234','I2M2Y1ZDg.jpg','online'),(113,'2013-04-22 02:20:43','gyhujk','tgyhu\'joik','I2M2Y1ZDg.jpg','online'),(114,'2013-04-22 02:25:27','cfvgkhlj','drftgh','I2M2Y1ZDg.jpg','online');
/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'users'
--

--
-- Dumping routines for database 'users'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-04-22  9:02:12
