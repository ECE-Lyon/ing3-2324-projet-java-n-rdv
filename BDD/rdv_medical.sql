-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Mar 31, 2024 at 10:24 AM
-- Server version: 5.7.24
-- PHP Version: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rdv_medical`
--

-- --------------------------------------------------------

--
-- Table structure for table `clients`
--

CREATE TABLE `clients` (
  `idClient` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `mdp` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `clients`
--

INSERT INTO `clients` (`idClient`, `nom`, `prenom`, `mail`, `mdp`) VALUES
(1, 'Baly', 'Fares', 'fares@mail.com', 'caca2003'),
(2, 'Pierre', 'ALpha', 'palpha@yahoo.fr', 'zidane200'),
(3, 'Maman', 'Beta', 'siuu@yahoo.fr', 'zidane300');

-- --------------------------------------------------------

--
-- Table structure for table `cliniques`
--

CREATE TABLE `cliniques` (
  `idClinique` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `localisation` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `medecins`
--

CREATE TABLE `medecins` (
  `idMedecin` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `mdp` varchar(255) NOT NULL,
  `specification` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `medecin_clinique`
--

CREATE TABLE `medecin_clinique` (
  `idJointure` int(11) NOT NULL,
  `idClinique` int(11) NOT NULL,
  `idMedecin` int(11) NOT NULL,
  `qualification` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `rdv`
--

CREATE TABLE `rdv` (
  `idRdv` int(11) NOT NULL,
  `note` text NOT NULL,
  `heure` date NOT NULL,
  `idClient` int(11) NOT NULL,
  `idJointure` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`idClient`);

--
-- Indexes for table `cliniques`
--
ALTER TABLE `cliniques`
  ADD PRIMARY KEY (`idClinique`);

--
-- Indexes for table `medecins`
--
ALTER TABLE `medecins`
  ADD PRIMARY KEY (`idMedecin`);

--
-- Indexes for table `medecin_clinique`
--
ALTER TABLE `medecin_clinique`
  ADD PRIMARY KEY (`idJointure`),
  ADD KEY `fk_J_clinique` (`idClinique`),
  ADD KEY `fk_J_medecin` (`idMedecin`);

--
-- Indexes for table `rdv`
--
ALTER TABLE `rdv`
  ADD PRIMARY KEY (`idRdv`),
  ADD KEY `fk_rdv_client` (`idClient`),
  ADD KEY `fk_rdv_J` (`idJointure`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `clients`
--
ALTER TABLE `clients`
  MODIFY `idClient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `cliniques`
--
ALTER TABLE `cliniques`
  MODIFY `idClinique` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `medecins`
--
ALTER TABLE `medecins`
  MODIFY `idMedecin` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `medecin_clinique`
--
ALTER TABLE `medecin_clinique`
  MODIFY `idJointure` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `rdv`
--
ALTER TABLE `rdv`
  MODIFY `idRdv` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `medecin_clinique`
--
ALTER TABLE `medecin_clinique`
  ADD CONSTRAINT `fk_J_clinique` FOREIGN KEY (`idClinique`) REFERENCES `cliniques` (`idClinique`),
  ADD CONSTRAINT `fk_J_medecin` FOREIGN KEY (`idMedecin`) REFERENCES `medecins` (`idMedecin`);

--
-- Constraints for table `rdv`
--
ALTER TABLE `rdv`
  ADD CONSTRAINT `fk_rdv_J` FOREIGN KEY (`idJointure`) REFERENCES `medecin_clinique` (`idJointure`),
  ADD CONSTRAINT `fk_rdv_client` FOREIGN KEY (`idClient`) REFERENCES `clients` (`idClient`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
