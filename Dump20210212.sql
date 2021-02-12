-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: apoteke
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `akcija`
--

DROP TABLE IF EXISTS `akcija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `akcija` (
  `id` int(11) NOT NULL,
  `naziv` varchar(255) DEFAULT NULL,
  `period_vazenja` varchar(255) DEFAULT NULL,
  `pretplatnici` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `akcija`
--

LOCK TABLES `akcija` WRITE;
/*!40000 ALTER TABLE `akcija` DISABLE KEYS */;
INSERT INTO `akcija` VALUES (160,'akcija2','11 dana',NULL),(183,'akcija123','15 dana',NULL);
/*!40000 ALTER TABLE `akcija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apoteka`
--

DROP TABLE IF EXISTS `apoteka`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apoteka` (
  `id` int(11) NOT NULL,
  `adresa` varchar(255) DEFAULT NULL,
  `naziv` varchar(255) DEFAULT NULL,
  `opis` varchar(255) DEFAULT NULL,
  `administrator_apoteke_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdgvo8mx6d0g1pappdrd3q8clf` (`administrator_apoteke_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apoteka`
--

LOCK TABLES `apoteka` WRITE;
/*!40000 ALTER TABLE `apoteka` DISABLE KEYS */;
INSERT INTO `apoteka` VALUES (148,'adresa1','apoteka1','opis1',147);
/*!40000 ALTER TABLE `apoteka` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apoteka_akcije`
--

DROP TABLE IF EXISTS `apoteka_akcije`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apoteka_akcije` (
  `apoteka_id` int(11) NOT NULL,
  `akcije_id` int(11) NOT NULL,
  UNIQUE KEY `UK_ip8d7034moka5s6cdoccjteuw` (`akcije_id`),
  KEY `FKgwm4642tmio665o5b59obppf2` (`apoteka_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apoteka_akcije`
--

LOCK TABLES `apoteka_akcije` WRITE;
/*!40000 ALTER TABLE `apoteka_akcije` DISABLE KEYS */;
INSERT INTO `apoteka_akcije` VALUES (148,183),(148,160);
/*!40000 ALTER TABLE `apoteka_akcije` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apoteka_dermatolozi`
--

DROP TABLE IF EXISTS `apoteka_dermatolozi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apoteka_dermatolozi` (
  `apoteka_id` int(11) NOT NULL,
  `dermatolozi_id` int(11) NOT NULL,
  UNIQUE KEY `UK_bi7pqqlyt079nffjdglm2iuiu` (`dermatolozi_id`),
  KEY `FKcrdy95r1qqb9qc3c9yh12rw99` (`apoteka_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apoteka_dermatolozi`
--

LOCK TABLES `apoteka_dermatolozi` WRITE;
/*!40000 ALTER TABLE `apoteka_dermatolozi` DISABLE KEYS */;
INSERT INTO `apoteka_dermatolozi` VALUES (148,145);
/*!40000 ALTER TABLE `apoteka_dermatolozi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apoteka_farmaceuti`
--

DROP TABLE IF EXISTS `apoteka_farmaceuti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apoteka_farmaceuti` (
  `apoteka_id` int(11) NOT NULL,
  `farmaceuti_id` int(11) NOT NULL,
  UNIQUE KEY `UK_k6jle87utbo3fq9fh2i2hxtfa` (`farmaceuti_id`),
  KEY `FKsrwhqx5opvslbmq5w6r7dlcft` (`apoteka_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apoteka_farmaceuti`
--

LOCK TABLES `apoteka_farmaceuti` WRITE;
/*!40000 ALTER TABLE `apoteka_farmaceuti` DISABLE KEYS */;
INSERT INTO `apoteka_farmaceuti` VALUES (148,144);
/*!40000 ALTER TABLE `apoteka_farmaceuti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apoteka_lekovi`
--

DROP TABLE IF EXISTS `apoteka_lekovi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apoteka_lekovi` (
  `apoteka_id` int(11) NOT NULL,
  `lekovi_id` int(11) NOT NULL,
  UNIQUE KEY `UK_n49c9eg3wpg09acw729gneviu` (`lekovi_id`),
  KEY `FKb75dereun5fq3f8v8lmuuj5oa` (`apoteka_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apoteka_lekovi`
--

LOCK TABLES `apoteka_lekovi` WRITE;
/*!40000 ALTER TABLE `apoteka_lekovi` DISABLE KEYS */;
INSERT INTO `apoteka_lekovi` VALUES (148,158),(148,150),(148,149),(148,151),(148,178);
/*!40000 ALTER TABLE `apoteka_lekovi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apoteka_narudzbenice`
--

DROP TABLE IF EXISTS `apoteka_narudzbenice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apoteka_narudzbenice` (
  `apoteka_id` int(11) NOT NULL,
  `narudzbenice_id` int(11) NOT NULL,
  UNIQUE KEY `UK_8xr9nb390hk1p863in14re8hy` (`narudzbenice_id`),
  KEY `FKqrdgy4mu5fvpdsxvce1cfxuf9` (`apoteka_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apoteka_narudzbenice`
--

LOCK TABLES `apoteka_narudzbenice` WRITE;
/*!40000 ALTER TABLE `apoteka_narudzbenice` DISABLE KEYS */;
INSERT INTO `apoteka_narudzbenice` VALUES (148,188);
/*!40000 ALTER TABLE `apoteka_narudzbenice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apoteka_pregledi`
--

DROP TABLE IF EXISTS `apoteka_pregledi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apoteka_pregledi` (
  `apoteka_id` int(11) NOT NULL,
  `pregledi_id` int(11) NOT NULL,
  UNIQUE KEY `UK_r6m4bqwn583adihbdkjdn3bw0` (`pregledi_id`),
  KEY `FK44gse6nbhg0ycly8fom20pu0a` (`apoteka_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apoteka_pregledi`
--

LOCK TABLES `apoteka_pregledi` WRITE;
/*!40000 ALTER TABLE `apoteka_pregledi` DISABLE KEYS */;
INSERT INTO `apoteka_pregledi` VALUES (148,163),(148,152),(148,182);
/*!40000 ALTER TABLE `apoteka_pregledi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apoteka_savetovanja`
--

DROP TABLE IF EXISTS `apoteka_savetovanja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apoteka_savetovanja` (
  `apoteka_id` int(11) NOT NULL,
  `savetovanja_id` int(11) NOT NULL,
  UNIQUE KEY `UK_f6petu600td1gmmasatkr4445` (`savetovanja_id`),
  KEY `FKet78cgd5jufsgwxurxdxfvmql` (`apoteka_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apoteka_savetovanja`
--

LOCK TABLES `apoteka_savetovanja` WRITE;
/*!40000 ALTER TABLE `apoteka_savetovanja` DISABLE KEYS */;
INSERT INTO `apoteka_savetovanja` VALUES (148,189);
/*!40000 ALTER TABLE `apoteka_savetovanja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `godisnji_odmor`
--

DROP TABLE IF EXISTS `godisnji_odmor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `godisnji_odmor` (
  `id` int(11) NOT NULL,
  `korisnik` varchar(255) DEFAULT NULL,
  `odobren` bit(1) NOT NULL,
  `termin_do` datetime DEFAULT NULL,
  `termin_od` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `godisnji_odmor`
--

LOCK TABLES `godisnji_odmor` WRITE;
/*!40000 ALTER TABLE `godisnji_odmor` DISABLE KEYS */;
INSERT INTO `godisnji_odmor` VALUES (185,'stefan.lukic777@gmail.com',_binary '\0','2018-11-27 09:15:31','2018-11-22 09:15:31'),(187,'stefan.lukic777@gmail.com',_binary '\0','2020-11-27 09:15:31','2020-11-22 09:15:31');
/*!40000 ALTER TABLE `godisnji_odmor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (190),(190),(190),(190),(190),(190),(190);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik` (
  `id` int(11) NOT NULL,
  `confirmation_token` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `ime` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `password_changed` bit(1) DEFAULT NULL,
  `prezime` varchar(255) DEFAULT NULL,
  `role_enum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES (144,'a62aecfc-df46-4a29-8d42-ee13f853b264','stefan.lukic777@gmail.com',_binary '','StefanFARMACEUT','$2a$10$Ek2S76rMFpu7p6.FwHLZ1eCljqGYKonGkni0gRP0mYAKS6aeM55De',_binary '\0','LukicFARMACEUT',NULL),(14,'2c5859b6-dcdf-461d-bf3e-87c1861d9d45','stefan.lukic@yahoo.com',_binary '','StefanADMINISTRATOR','$2a$10$SWVHj2DZrbC10y41c2sjb.u/i7FA1kgB.e.Vzaa.fgKVbgQWjqMQy',_binary '\0','LukicADMINISTRATOR',NULL),(145,'9f352431-12ff-4640-ab53-8b656831d285','lukic.stefan666@gmail.com',_binary '','StefanDERMATOLOG','$2a$10$2Z8t3Gk4HVvgecklM1SuGevWLVht0wWNyNQz2EyRr9IUsDEVeEyqC',_binary '\0','LukicDERMATOLOG',NULL),(146,'287d88cc-c38a-4a9b-8334-c410a1cfe3af','stefan.lukic767@gmail.com',_binary '','StefanPACIJENT','$2a$10$w9weFgCbY1a0sd1eVIANdupGDPqgkaa0Nm1fJsVmX7aMeccVw8SVi',_binary '\0','LukicPACIJENT',NULL),(147,'08ef920c-81e8-4d72-84a4-3adb1c4d95f1','lukic.stefan767@gmail.com',_binary '','StefanADMINAPOTEKE','$2a$10$u2/ddwwBjQxRTgYOEIM3IuqRf4ZmfH7I8hLV1P/XiLAd1tbYDTRce',_binary '\0','LukicADMINAPOTEKE',NULL);
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnik_lek_alergija`
--

DROP TABLE IF EXISTS `korisnik_lek_alergija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik_lek_alergija` (
  `korisnik_id` int(11) NOT NULL,
  `lek_alergija_id` int(11) NOT NULL,
  UNIQUE KEY `UK_ql34vs6l7gaov6f2r8kk0ccvi` (`lek_alergija_id`),
  KEY `FKdu3krraxey470cgd9990175rb` (`korisnik_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik_lek_alergija`
--

LOCK TABLES `korisnik_lek_alergija` WRITE;
/*!40000 ALTER TABLE `korisnik_lek_alergija` DISABLE KEYS */;
/*!40000 ALTER TABLE `korisnik_lek_alergija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnik_rezervisani_lekovi`
--

DROP TABLE IF EXISTS `korisnik_rezervisani_lekovi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik_rezervisani_lekovi` (
  `korisnik_id` int(11) NOT NULL,
  `rezervisani_lekovi_id` int(11) NOT NULL,
  UNIQUE KEY `UK_839d6nqbpo4ikcuufb8lshs6k` (`rezervisani_lekovi_id`),
  KEY `FKk15h7sc2axobt1cq2yvh7r3gr` (`korisnik_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik_rezervisani_lekovi`
--

LOCK TABLES `korisnik_rezervisani_lekovi` WRITE;
/*!40000 ALTER TABLE `korisnik_rezervisani_lekovi` DISABLE KEYS */;
INSERT INTO `korisnik_rezervisani_lekovi` VALUES (146,150),(146,151),(146,158);
/*!40000 ALTER TABLE `korisnik_rezervisani_lekovi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnik_roles`
--

DROP TABLE IF EXISTS `korisnik_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik_roles` (
  `korisnik_id` int(11) NOT NULL,
  `roles` varchar(255) DEFAULT NULL,
  KEY `FK7k0roduxfd0x0nyrru2qd4bnh` (`korisnik_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik_roles`
--

LOCK TABLES `korisnik_roles` WRITE;
/*!40000 ALTER TABLE `korisnik_roles` DISABLE KEYS */;
INSERT INTO `korisnik_roles` VALUES (14,'ADMINISTRATOR'),(144,'FARMACEUT'),(145,'DERMATOLOG'),(146,'PACIJENT'),(147,'ADMINAPOTEKE');
/*!40000 ALTER TABLE `korisnik_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lek`
--

DROP TABLE IF EXISTS `lek`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lek` (
  `id` int(11) NOT NULL,
  `cena` int(11) NOT NULL,
  `naziv` varchar(255) DEFAULT NULL,
  `rezervisan` bit(1) NOT NULL,
  `apoteka_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc7dmw32x3br6m4euv5oh3vtdj` (`apoteka_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lek`
--

LOCK TABLES `lek` WRITE;
/*!40000 ALTER TABLE `lek` DISABLE KEYS */;
INSERT INTO `lek` VALUES (151,100,'lek3',_binary '',NULL),(150,200,'lek2',_binary '',NULL),(149,100,'lek1',_binary '\0',NULL),(158,111,'lekPROBA',_binary '',NULL),(178,400,'lek12345',_binary '\0',NULL);
/*!40000 ALTER TABLE `lek` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lek_rezervacija`
--

DROP TABLE IF EXISTS `lek_rezervacija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lek_rezervacija` (
  `id` int(11) NOT NULL,
  `datum_vreme` datetime DEFAULT NULL,
  `pacijent` varchar(255) DEFAULT NULL,
  `lek_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5rjp3ne6yidi5l5ocvtd8ygyw` (`lek_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lek_rezervacija`
--

LOCK TABLES `lek_rezervacija` WRITE;
/*!40000 ALTER TABLE `lek_rezervacija` DISABLE KEYS */;
INSERT INTO `lek_rezervacija` VALUES (82,NULL,'stefan.lukic@yahoo.com',75),(123,NULL,'stefan.lukic@yahoo.com',94),(124,NULL,'stefan.lukic@yahoo.com',92),(125,NULL,'stefan.lukic@yahoo.com',116),(154,NULL,'stefan.lukic767@gmail.com',151),(155,NULL,'stefan.lukic767@gmail.com',150),(180,NULL,'stefan.lukic767@gmail.com',158);
/*!40000 ALTER TABLE `lek_rezervacija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `narudzbenica`
--

DROP TABLE IF EXISTS `narudzbenica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `narudzbenica` (
  `id` int(11) NOT NULL,
  `datum_vreme` datetime DEFAULT NULL,
  `kolicina` int(11) NOT NULL,
  `prihvacena` bit(1) NOT NULL,
  `ukupna_cena` int(11) NOT NULL,
  `lek_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe115qf9dxeu0patsudiscdcxk` (`lek_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `narudzbenica`
--

LOCK TABLES `narudzbenica` WRITE;
/*!40000 ALTER TABLE `narudzbenica` DISABLE KEYS */;
INSERT INTO `narudzbenica` VALUES (171,'2018-11-22 09:15:31',5,_binary '\0',0,151),(174,'2018-11-22 09:15:31',3,_binary '\0',0,158);
/*!40000 ALTER TABLE `narudzbenica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `narudzbenica_spisak_lekova`
--

DROP TABLE IF EXISTS `narudzbenica_spisak_lekova`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `narudzbenica_spisak_lekova` (
  `narudzbenica_id` int(11) NOT NULL,
  `spisak_lekova_id` int(11) NOT NULL,
  UNIQUE KEY `UK_gbxsibxghhafd9jfdc9oquhy0` (`spisak_lekova_id`),
  KEY `FK5ajt8frj4ylry8tqig8oyvcfl` (`narudzbenica_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `narudzbenica_spisak_lekova`
--

LOCK TABLES `narudzbenica_spisak_lekova` WRITE;
/*!40000 ALTER TABLE `narudzbenica_spisak_lekova` DISABLE KEYS */;
INSERT INTO `narudzbenica_spisak_lekova` VALUES (170,158),(170,149),(170,150),(170,151);
/*!40000 ALTER TABLE `narudzbenica_spisak_lekova` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pregled`
--

DROP TABLE IF EXISTS `pregled`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pregled` (
  `id` int(11) NOT NULL,
  `cena` int(11) NOT NULL,
  `datum_vreme` datetime DEFAULT NULL,
  `rezervisan` bit(1) NOT NULL,
  `dermatolog_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhjv8hgyp6bvql8y98px4vsnfy` (`dermatolog_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pregled`
--

LOCK TABLES `pregled` WRITE;
/*!40000 ALTER TABLE `pregled` DISABLE KEYS */;
INSERT INTO `pregled` VALUES (152,3000,'2018-11-22 09:07:30',_binary '',145),(163,4000,'2018-11-22 09:10:30',_binary '',145);
/*!40000 ALTER TABLE `pregled` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rezervacija`
--

DROP TABLE IF EXISTS `rezervacija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rezervacija` (
  `id` int(11) NOT NULL,
  `pacijent` varchar(255) DEFAULT NULL,
  `pregled_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8wffq9livfuvhye6thd2kfey` (`pregled_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rezervacija`
--

LOCK TABLES `rezervacija` WRITE;
/*!40000 ALTER TABLE `rezervacija` DISABLE KEYS */;
INSERT INTO `rezervacija` VALUES (181,'stefan.lukic767@gmail.com',152);
/*!40000 ALTER TABLE `rezervacija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `savetovanje`
--

DROP TABLE IF EXISTS `savetovanje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `savetovanje` (
  `id` int(11) NOT NULL,
  `datum_vreme` datetime DEFAULT NULL,
  `farmaceut_id` int(11) DEFAULT NULL,
  `zakazano` bit(1) NOT NULL,
  `pacijent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK66tr2pnq7k2nut7x8wg4f3rns` (`farmaceut_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `savetovanje`
--

LOCK TABLES `savetovanje` WRITE;
/*!40000 ALTER TABLE `savetovanje` DISABLE KEYS */;
INSERT INTO `savetovanje` VALUES (157,'2018-11-22 09:08:30',144,_binary '','StefanPACIJENT');
/*!40000 ALTER TABLE `savetovanje` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-12 16:04:09
