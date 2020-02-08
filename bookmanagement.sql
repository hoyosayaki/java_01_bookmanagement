-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: BOOKMGR
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `book_list`
--

DROP TABLE IF EXISTS `book_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_list` (
  `pid` varchar(100) NOT NULL,
  `TITLE` varchar(100) DEFAULT NULL,
  `PUBLISHER` varchar(100) DEFAULT NULL,
  `AUTHOR` varchar(100) DEFAULT NULL,
  `GENRE` varchar(100) DEFAULT NULL,
  `PRICE` int(11) DEFAULT NULL,
  `STATE` char(1) DEFAULT NULL,
  `STATE2` char(1) DEFAULT NULL,
  `AVE_EVA` double DEFAULT NULL,
  `area` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_list`
--

LOCK TABLES `book_list` WRITE;
/*!40000 ALTER TABLE `book_list` DISABLE KEYS */;
INSERT INTO `book_list` VALUES ('0001','リーダブルコード','オライリージャパン','Dustin Boswell','コード',2640,'1','0',3.5,'A'),('0002','スッキリわかるJava入門','インプレスジャパン','中山清喬','Java',2860,'0','0',5,'A'),('0003','Ruby入門','インプレスジャパン','中山清喬','Ruby',3060,'0','2',2,'B'),('0004','Python入門','インプレスジャパン','中山清喬','Python',4060,'0','0',1,'C'),('0005','Pythonから学ぶDeepLearning','インプレスジャパン','中山清喬','Python',4060,'0','0',3,'C'),('0006','AWS入門','AWSジャパン','中山清喬','AWS',3060,'0','0',4,'E'),('0007','Vagrant入門','インプレスジャパン','田中有記','Vagrant',3060,'0','0',3.5,'F'),('0008','CSS入門','CSSジャパン','田中有記','CSS',3060,'0','0',5,'G'),('0009','JavaScript入門','インプレスジャパン','中山清喬','JavaScript',3060,'1','2',0,'H'),('0010','PHP入門','インプレスジャパン','中山清喬','PHP',3060,'0','0',3,'F'),('0011','HTML入門','HTMLジャパン','田中有記','HTML',3060,'0','0',0,'G'),('0012','Angular.js入門','Angularジャパン','田中有記','Angular.js',3060,'1','0',0,'G'),('0013','Git入門','インプレスジャパン','中山清喬','Git',3060,'0','0',0,'F');
/*!40000 ALTER TABLE `book_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lend_list`
--

DROP TABLE IF EXISTS `lend_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lend_list` (
  `NUM` int(11) DEFAULT NULL,
  `pid` varchar(100) DEFAULT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `OUT_DATE` varchar(20) DEFAULT NULL,
  `SCHE_DATE` varchar(20) DEFAULT NULL,
  `IN_DATE` varchar(20) DEFAULT NULL,
  `EVALUATION` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lend_list`
--

LOCK TABLES `lend_list` WRITE;
/*!40000 ALTER TABLE `lend_list` DISABLE KEYS */;
INSERT INTO `lend_list` VALUES (1,'0003','ディーン藤岡','2020-01-31','2020-02-12','2020-01-31',2),(3,'0001','新垣結衣','2020-01-31','2020-02-07','2020-01-31',4),(4,'0005','新垣結衣','2020-01-31','2020-02-07','2020-01-31',3),(5,'0007','ディーン藤岡','2020-01-31','2020-02-02','2020-01-31',3),(6,'0010','本田翼','2020-01-31','2020-02-07','2020-01-31',3),(7,'0004','千葉雄大','2020-01-31','2020-02-07','2020-01-31',1),(8,'0007','田中みな実','2020-01-31','2020-02-07','2020-01-31',4),(9,'0009','粗品','2020-01-31','2020-02-03',NULL,0),(10,'0009','せいや','2020-02-05','2020-02-07',NULL,0),(11,'0008','藤田ニコル','2020-01-31','2020-02-01','2020-01-31',5),(12,'0006','せいや','2020-01-25','2020-01-30','2020-01-31',4),(13,'0001','田中みな実','2020-01-31','2020-02-07','2020-01-31',3),(15,'0012','せいや','2020-01-25','2020-02-02',NULL,0),(16,'0001','せいや','2020-01-31','2020-02-07',NULL,0),(17,'0003','粗品','2020-02-03','2020-02-07',NULL,0);
/*!40000 ALTER TABLE `lend_list` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-08 18:54:56
