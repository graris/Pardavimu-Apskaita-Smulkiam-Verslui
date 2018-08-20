-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2018 m. Geg 29 d. 23:00
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pardavimupirkimuapskaitosdb`
--

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `darbuotojas`
--

CREATE TABLE `darbuotojas` (
  `DARBUOTOJO_ID` int(11) NOT NULL,
  `VARDAS` char(30) NOT NULL,
  `PAVARDE` char(30) NOT NULL,
  `PAREIGOS` char(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Sukurta duomenų kopija lentelei `darbuotojas`
--

INSERT INTO `darbuotojas` (`DARBUOTOJO_ID`, `VARDAS`, `PAVARDE`, `PAREIGOS`) VALUES
(1, 'Vardenis', 'Pavardenis', 'Buhalteris');

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `dirba`
--

CREATE TABLE `dirba` (
  `FILIALO_ID` int(11) NOT NULL,
  `DARBUOTOJO_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=ucs2 COLLATE=ucs2_lithuanian_ci;

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `filialas`
--

CREATE TABLE `filialas` (
  `FILIALO_ID` int(11) NOT NULL,
  `IMONES_ID` int(11) DEFAULT NULL,
  `FILIALO_PAVADINIMAS` char(100) NOT NULL,
  `ADRESAS` char(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Sukurta duomenų kopija lentelei `filialas`
--

INSERT INTO `filialas` (`FILIALO_ID`, `IMONES_ID`, `FILIALO_PAVADINIMAS`, `ADRESAS`) VALUES
(1, 1, 'DomSta Shop', 'Ryklių g. 14-5');

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `imone`
--

CREATE TABLE `imone` (
  `ID` int(11) NOT NULL,
  `IMONES_PAVADINIMAS` char(100) NOT NULL,
  `ADRESAS` char(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Sukurta duomenų kopija lentelei `imone`
--

INSERT INTO `imone` (`ID`, `IMONES_PAVADINIMAS`, `ADRESAS`) VALUES
(1, 'DomSta corp.', 'Lūkiškių 15-5');

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `pajamavimo_dokumentas`
--

CREATE TABLE `pajamavimo_dokumentas` (
  `SERIJA` varchar(10) COLLATE ucs2_lithuanian_ci NOT NULL,
  `PAJAMAVIMO_DOK_ID` int(11) NOT NULL,
  `TIEKEJO_IMONES_KODAS` int(11) DEFAULT NULL,
  `FILIALO_ID` int(11) DEFAULT NULL,
  `PIRKIMO_DATA` date NOT NULL,
  `APMOKEJIMO_DATA` date NOT NULL,
  `PVM` int(11) NOT NULL,
  `APMOKETA` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=ucs2 COLLATE=ucs2_lithuanian_ci;

--
-- Sukurta duomenų kopija lentelei `pajamavimo_dokumentas`
--

INSERT INTO `pajamavimo_dokumentas` (`SERIJA`, `PAJAMAVIMO_DOK_ID`, `TIEKEJO_IMONES_KODAS`, `FILIALO_ID`, `PIRKIMO_DATA`, `APMOKEJIMO_DATA`, `PVM`, `APMOKETA`) VALUES
('AAA', 2753753, 453453, 1, '2018-05-29', '2018-05-29', 21, 0),
('BBB', 545387, 86945456, 1, '2018-05-29', '2018-05-30', 21, 0),
('CCC', 7734535, 453453, 1, '2018-05-29', '2018-05-30', 21, 0);

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `parduota_preke`
--

CREATE TABLE `parduota_preke` (
  `SASKAITOS_ID` int(11) NOT NULL,
  `UUID` varchar(36) COLLATE ucs2_lithuanian_ci NOT NULL,
  `SAVIKAINA` float NOT NULL,
  `KIEKIS` int(11) DEFAULT NULL,
  `ANTKAINIO_PROCENTAS` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=ucs2 COLLATE=ucs2_lithuanian_ci;

--
-- Sukurta duomenų kopija lentelei `parduota_preke`
--

INSERT INTO `parduota_preke` (`SASKAITOS_ID`, `UUID`, `SAVIKAINA`, `KIEKIS`, `ANTKAINIO_PROCENTAS`) VALUES
(1, '106714b5-6374-11e8-a2b3-f0761cb2060e', 1.5, 5, -0.0399987),
(2, '39a58a1c-6375-11e8-a2b3-f0761cb2060e', 12, 1, 3.95),
(3, '1077de46-6374-11e8-a2b3-f0761cb2060e', 2, 2, 2.89999),
(4, '10930618-6374-11e8-a2b3-f0761cb2060e', 0.5, 10, 10),
(5, '10930618-6374-11e8-a2b3-f0761cb2060e', 0.5, 5, 8.9),
(6, 'b4542aee-6377-11e8-a2b3-f0761cb2060e', 12, 5, 2.9),
(7, 'b4542aee-6377-11e8-a2b3-f0761cb2060e', 12, 19, -0.249998),
(8, '39a58a1c-6375-11e8-a2b3-f0761cb2060e', 12, 2, 3.95);

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `pirkejas`
--

CREATE TABLE `pirkejas` (
  `IMONES_KODAS` int(11) NOT NULL,
  `PAVADINIMAS` char(100) NOT NULL,
  `PVM_KODAS` char(80) NOT NULL,
  `ADRESAS` char(100) NOT NULL,
  `El_PASTO_ADRESAS` char(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Sukurta duomenų kopija lentelei `pirkejas`
--

INSERT INTO `pirkejas` (`IMONES_KODAS`, `PAVADINIMAS`, `PVM_KODAS`, `ADRESAS`, `El_PASTO_ADRESAS`) VALUES
(6484864, 'Pirkėjas UAB', 'LT5464846', 'pirkėjų g. 10-5', 'info@pirkejas.lt'),
(8911991, 'aferistukas UAB', 'LT29598', 'jūros g. 12-5', 'info@aferistukas.lt');

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `pirkta_preke`
--

CREATE TABLE `pirkta_preke` (
  `SERIJA` varchar(10) COLLATE ucs2_lithuanian_ci NOT NULL,
  `PAJAMAVIMO_DOK_ID` int(11) NOT NULL,
  `UUID` varchar(36) COLLATE ucs2_lithuanian_ci NOT NULL,
  `SAVIKAINA` float NOT NULL,
  `KIEKIS` int(11) DEFAULT NULL,
  `ANTKAINIO_PROCENTAS` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=ucs2 COLLATE=ucs2_lithuanian_ci;

--
-- Sukurta duomenų kopija lentelei `pirkta_preke`
--

INSERT INTO `pirkta_preke` (`SERIJA`, `PAJAMAVIMO_DOK_ID`, `UUID`, `SAVIKAINA`, `KIEKIS`, `ANTKAINIO_PROCENTAS`) VALUES
('AAA', 2753753, '106714b5-6374-11e8-a2b3-f0761cb2060e', 1.5, 25, 2),
('AAA', 2753753, '1077de46-6374-11e8-a2b3-f0761cb2060e', 2, 10, 5),
('AAA', 2753753, '10930618-6374-11e8-a2b3-f0761cb2060e', 0.5, 125, 10),
('BBB', 545387, '39a58a1c-6375-11e8-a2b3-f0761cb2060e', 12, 5, 5),
('BBB', 545387, '3cc2f41b-6375-11e8-a2b3-f0761cb2060e', 87.9, 5, 10),
('BBB', 545387, '3e5a9277-6375-11e8-a2b3-f0761cb2060e', 4, 10, 1),
('CCC', 7734535, 'b43ebe6b-6377-11e8-a2b3-f0761cb2060e', 897, 2, 10),
('CCC', 7734535, 'b4542aee-6377-11e8-a2b3-f0761cb2060e', 12, 74, 5),
('CCC', 7734535, 'b470e5e7-6377-11e8-a2b3-f0761cb2060e', 2.35, 12, 10);

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `preke`
--

CREATE TABLE `preke` (
  `UUID` varchar(36) COLLATE ucs2_lithuanian_ci NOT NULL,
  `PREKES_KODAS` int(11) NOT NULL,
  `BAR_KODAS` int(11) DEFAULT NULL,
  `PAVADINIMAS` char(100) COLLATE ucs2_lithuanian_ci NOT NULL,
  `MATAVIMO_VIENETAS` char(10) COLLATE ucs2_lithuanian_ci NOT NULL,
  `PIRKIMO_KAINA` float NOT NULL,
  `ANTKAINIO_PROCENTAS` float NOT NULL,
  `LIKUTIS` int(11) NOT NULL,
  `PIRKIMO_DATA` date NOT NULL,
  `TIEKEJO_IMONES_KODAS` int(11) DEFAULT NULL,
  `PVM` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=ucs2 COLLATE=ucs2_lithuanian_ci;

--
-- Sukurta duomenų kopija lentelei `preke`
--

INSERT INTO `preke` (`UUID`, `PREKES_KODAS`, `BAR_KODAS`, `PAVADINIMAS`, `MATAVIMO_VIENETAS`, `PIRKIMO_KAINA`, `ANTKAINIO_PROCENTAS`, `LIKUTIS`, `PIRKIMO_DATA`, `TIEKEJO_IMONES_KODAS`, `PVM`) VALUES
('106714b5-6374-11e8-a2b3-f0761cb2060e', 15616, 611616, 'Obuolys', 'vnt.', 1.5, 2, 20, '2018-05-29', 453453, 21),
('1077de46-6374-11e8-a2b3-f0761cb2060e', 3783454, 3453434, 'Kriaušė', 'vnt.', 2, 5, 8, '2018-05-29', 453453, 21),
('10930618-6374-11e8-a2b3-f0761cb2060e', 7343435, 686345, 'Vyšnia', 'vnt.', 0.5, 10, 110, '2018-05-29', 453453, 21),
('39a58a1c-6375-11e8-a2b3-f0761cb2060e', 535434, 97867546, 'Plaktukas', 'vnt.', 12, 5, 2, '2018-05-29', 86945456, 21),
('3cc2f41b-6375-11e8-a2b3-f0761cb2060e', 47837, 8934864, 'Ausinės', 'vnt.', 87.9, 10, 5, '2018-05-29', 86945456, 21),
('3e5a9277-6375-11e8-a2b3-f0761cb2060e', 457854, 3664344, 'Kibiras', 'vnt.', 4, 1, 10, '2018-05-29', 86945456, 21),
('b43ebe6b-6377-11e8-a2b3-f0761cb2060e', 3543535, 65635639, 'Procesorius', 'vnt.', 897, 10, 2, '2018-05-29', 453453, 21),
('b4542aee-6377-11e8-a2b3-f0761cb2060e', 788774, 6397845, 'Tranzistorius', 'vnt.', 12, 5, 50, '2018-05-29', 453453, 21),
('b470e5e7-6377-11e8-a2b3-f0761cb2060e', 7448793, 96385274, 'Virvė', 'vnt.', 2.35, 10, 12, '2018-05-29', 453453, 21);

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `saskaita_faktura`
--

CREATE TABLE `saskaita_faktura` (
  `SASKAITOS_ID` int(11) NOT NULL,
  `DARBUOTOJO_ID` int(11) DEFAULT NULL,
  `PIRKEJO_IMONES_KODAS` int(11) DEFAULT NULL,
  `FILIALO_ID` int(11) DEFAULT NULL,
  `PIRKIMO_DATA` date NOT NULL,
  `APMOKEJIMO_DATA` date NOT NULL,
  `KASOS_NR` int(11) NOT NULL,
  `PVM` int(11) NOT NULL,
  `APMOKETA` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=ucs2 COLLATE=ucs2_lithuanian_ci;

--
-- Sukurta duomenų kopija lentelei `saskaita_faktura`
--

INSERT INTO `saskaita_faktura` (`SASKAITOS_ID`, `DARBUOTOJO_ID`, `PIRKEJO_IMONES_KODAS`, `FILIALO_ID`, `PIRKIMO_DATA`, `APMOKEJIMO_DATA`, `KASOS_NR`, `PVM`, `APMOKETA`) VALUES
(1, 1, 6484864, 1, '2018-05-29', '2018-05-31', 123, 21, 0),
(2, 1, 6484864, 1, '2018-05-29', '2018-05-30', 123, 21, 0),
(3, 1, 8911991, 1, '2018-05-29', '2018-05-30', 123, 21, 0),
(4, 1, 6484864, 1, '2018-05-29', '2018-05-31', 123, 21, 0),
(5, 1, 8911991, 1, '2018-05-29', '2018-05-30', 123, 21, 0),
(6, 1, 8911991, 1, '2018-05-29', '2018-05-30', 123, 21, 0),
(7, 1, 6484864, 1, '2018-05-29', '2018-05-30', 123, 21, 0),
(8, 1, 8911991, 1, '2018-05-29', '2018-05-30', 123, 21, 0);

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `tiekejas`
--

CREATE TABLE `tiekejas` (
  `IMONES_KODAS` int(11) NOT NULL,
  `PAVADINIMAS` char(100) COLLATE ucs2_lithuanian_ci NOT NULL,
  `PVM_KODAS` char(80) COLLATE ucs2_lithuanian_ci NOT NULL,
  `ADRESAS` char(100) COLLATE ucs2_lithuanian_ci NOT NULL,
  `El_PASTO_ADRESAS` char(100) COLLATE ucs2_lithuanian_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=ucs2 COLLATE=ucs2_lithuanian_ci;

--
-- Sukurta duomenų kopija lentelei `tiekejas`
--

INSERT INTO `tiekejas` (`IMONES_KODAS`, `PAVADINIMAS`, `PVM_KODAS`, `ADRESAS`, `El_PASTO_ADRESAS`) VALUES
(453453, 'Tiekiava UAB', 'LT841', 'atsuktuvų g. 5-45', 'info@tiekiava.lt'),
(86945456, 'Super Tiekėjas corp.', 'LT5866', 'grfdgr 45-58', 'grfsda@segw.lt');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `darbuotojas`
--
ALTER TABLE `darbuotojas`
  ADD PRIMARY KEY (`DARBUOTOJO_ID`);

--
-- Indexes for table `dirba`
--
ALTER TABLE `dirba`
  ADD PRIMARY KEY (`FILIALO_ID`,`DARBUOTOJO_ID`),
  ADD KEY `Dirba2_FK` (`DARBUOTOJO_ID`) USING BTREE,
  ADD KEY `Dirba_FK` (`FILIALO_ID`);

--
-- Indexes for table `filialas`
--
ALTER TABLE `filialas`
  ADD PRIMARY KEY (`FILIALO_ID`),
  ADD KEY `Priklauso_FK` (`IMONES_ID`);

--
-- Indexes for table `imone`
--
ALTER TABLE `imone`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `pajamavimo_dokumentas`
--
ALTER TABLE `pajamavimo_dokumentas`
  ADD PRIMARY KEY (`SERIJA`,`PAJAMAVIMO_DOK_ID`) USING BTREE,
  ADD KEY `GAUNA_FK` (`FILIALO_ID`),
  ADD KEY `ISRASE_FK` (`TIEKEJO_IMONES_KODAS`);

--
-- Indexes for table `parduota_preke`
--
ALTER TABLE `parduota_preke`
  ADD PRIMARY KEY (`SASKAITOS_ID`,`UUID`),
  ADD KEY `PARDUOTA_PREKE2_FK` (`SASKAITOS_ID`),
  ADD KEY `PARDUOTA_PREKE_FK` (`UUID`);

--
-- Indexes for table `pirkejas`
--
ALTER TABLE `pirkejas`
  ADD PRIMARY KEY (`IMONES_KODAS`);

--
-- Indexes for table `pirkta_preke`
--
ALTER TABLE `pirkta_preke`
  ADD PRIMARY KEY (`SERIJA`,`PAJAMAVIMO_DOK_ID`,`UUID`),
  ADD KEY `PIRKTA_PREKE3_FK` (`SERIJA`),
  ADD KEY `PIRKTA_PREKE2_FK` (`PAJAMAVIMO_DOK_ID`),
  ADD KEY `PIRKTA_PREKE_FK` (`UUID`);

--
-- Indexes for table `preke`
--
ALTER TABLE `preke`
  ADD PRIMARY KEY (`UUID`),
  ADD KEY `PARDAVE_FK` (`TIEKEJO_IMONES_KODAS`);

--
-- Indexes for table `saskaita_faktura`
--
ALTER TABLE `saskaita_faktura`
  ADD PRIMARY KEY (`SASKAITOS_ID`),
  ADD KEY `ISRASO_FK` (`DARBUOTOJO_ID`),
  ADD KEY `GAUNA_FK` (`PIRKEJO_IMONES_KODAS`),
  ADD KEY `PRISKIRTA_FK` (`FILIALO_ID`);

--
-- Indexes for table `tiekejas`
--
ALTER TABLE `tiekejas`
  ADD PRIMARY KEY (`IMONES_KODAS`);

--
-- Apribojimai eksportuotom lentelėm
--

--
-- Apribojimai lentelei `dirba`
--
ALTER TABLE `dirba`
  ADD CONSTRAINT `FK_DIRBA_DIRBA2_DARBUOTO` FOREIGN KEY (`DARBUOTOJO_ID`) REFERENCES `darbuotojas` (`DARBUOTOJO_ID`),
  ADD CONSTRAINT `FK_DIRBA_DIRBA_FILIALAS` FOREIGN KEY (`FILIALO_ID`) REFERENCES `filialas` (`FILIALO_ID`);

--
-- Apribojimai lentelei `filialas`
--
ALTER TABLE `filialas`
  ADD CONSTRAINT `FK_FILIALAS_PRIKLAUSO_IMONE` FOREIGN KEY (`IMONES_ID`) REFERENCES `imone` (`ID`);

--
-- Apribojimai lentelei `pajamavimo_dokumentas`
--
ALTER TABLE `pajamavimo_dokumentas`
  ADD CONSTRAINT `FK_PAJAMAVA_GAUNA_FILIALAS` FOREIGN KEY (`FILIALO_ID`) REFERENCES `filialas` (`FILIALO_ID`),
  ADD CONSTRAINT `FK_PAJAMAVA_ISRASE_TIEKEJAS` FOREIGN KEY (`TIEKEJO_IMONES_KODAS`) REFERENCES `tiekejas` (`IMONES_KODAS`);

--
-- Apribojimai lentelei `parduota_preke`
--
ALTER TABLE `parduota_preke`
  ADD CONSTRAINT `FK_PARDUOTA_PARDUOTA__PREKE` FOREIGN KEY (`UUID`) REFERENCES `preke` (`UUID`),
  ADD CONSTRAINT `FK_PARDUOTA_PARDUOTA__SASKAITA` FOREIGN KEY (`SASKAITOS_ID`) REFERENCES `saskaita_faktura` (`SASKAITOS_ID`);

--
-- Apribojimai lentelei `pirkta_preke`
--
ALTER TABLE `pirkta_preke`
  ADD CONSTRAINT `FK_PIRKTA_PIRKTA__PREKE` FOREIGN KEY (`UUID`) REFERENCES `preke` (`UUID`),
  ADD CONSTRAINT `FK_PIRKTA_PIRKTA__SASKAITA` FOREIGN KEY (`SERIJA`,`PAJAMAVIMO_DOK_ID`) REFERENCES `pajamavimo_dokumentas` (`SERIJA`, `PAJAMAVIMO_DOK_ID`);

--
-- Apribojimai lentelei `preke`
--
ALTER TABLE `preke`
  ADD CONSTRAINT `FK_PREKE_PARDAVE_TIEKEJAS` FOREIGN KEY (`TIEKEJO_IMONES_KODAS`) REFERENCES `tiekejas` (`IMONES_KODAS`);

--
-- Apribojimai lentelei `saskaita_faktura`
--
ALTER TABLE `saskaita_faktura`
  ADD CONSTRAINT `FK_SASKAITA_GAUNA_PIRKEJAS` FOREIGN KEY (`PIRKEJO_IMONES_KODAS`) REFERENCES `pirkejas` (`IMONES_KODAS`),
  ADD CONSTRAINT `FK_SASKAITA_ISRASO_DARBUOTO` FOREIGN KEY (`DARBUOTOJO_ID`) REFERENCES `darbuotojas` (`DARBUOTOJO_ID`),
  ADD CONSTRAINT `FK_SASKAITA_PRISKIRTA_FILIALAS` FOREIGN KEY (`FILIALO_ID`) REFERENCES `filialas` (`FILIALO_ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
