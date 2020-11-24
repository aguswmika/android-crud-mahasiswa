# ************************************************************
# Sequel Pro SQL dump
# Version 5446
#
# https://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.26)
# Database: mahasiswa-android
# Generation Time: 2020-11-24 20:26:54 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table mahasiswa
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mahasiswa`;

CREATE TABLE `mahasiswa` (
  `nim` char(10) NOT NULL DEFAULT '',
  `nama` varchar(255) NOT NULL DEFAULT '',
  `program_studi` varchar(255) NOT NULL DEFAULT '',
  `alamat` text NOT NULL,
  PRIMARY KEY (`nim`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `mahasiswa` WRITE;
/*!40000 ALTER TABLE `mahasiswa` DISABLE KEYS */;

INSERT INTO `mahasiswa` (`nim`, `nama`, `program_studi`, `alamat`)
VALUES
	('16181','agaha','Teknik Informatika','akalaka'),
	('1708561002','asasa','Teknik Informatika','asasa'),
	('1708561008','I Putu Agus Wahyu Widiatmika','Teknik Informatika','Dalung Permai'),
	('23232','shdushds','Teknik Informatika','234343'),
	('232323','Shsjds','Teknik Informatika','dsdsd'),
	('23343343','2783782hds','Teknik Informatika','asasdsds'),
	('454664','343','Teknik Informatika','dsdsds');

/*!40000 ALTER TABLE `mahasiswa` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table token
# ------------------------------------------------------------

DROP TABLE IF EXISTS `token`;

CREATE TABLE `token` (
  `token_key` varchar(255) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;

INSERT INTO `token` (`token_key`)
VALUES
	('fVMnQiXQRJ6ABVeTUUyidQ:APA91bFpV58DYFSux1caS37FQkBo2YixYCqLVBqed01ledtdP2OtjgmO5YY4LhNqQJCgNylt9-gLCD5_1S-NAfojINfjAshEHYd-iccOQ3NawrUfw-rJnJDwloH2LFxTuRycrRtjbF2e');

/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
