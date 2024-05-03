-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 03, 2024 at 03:05 PM
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
(-1, 'guest', 'guest', '-1', '-1'),
(2, 'Pierre', 'ALpha', 'palpha@yahoo.fr', 'zidane200'),
(3, 'Maman', 'Beta', 'siuu@yahoo.fr', 'zidane300'),
(4, 'Metro', 'Booming', 'metro@boomin.com', 'metroooo'),
(5, 'Courva', 'Eva', 'EVA@gmail.com', 'evabvalentin'),
(6, 'Courva', 'Malorie', 'courva@gmail.come', 'cestlakiffance'),
(7, 'Drake', 'Booming', 'db@yahoo.fr', 'lamborghini'),
(8, 'Courva', 'Ophalie', 'aaaa@laposte_fr.net', 'anchorhead'),
(9, 'Balsalobre', 'Thomas', 'a@gmail.com', '1234'),
(10, 'Courva', 'Thomas', 'b@gmail.com', '4567'),
(11, 'Zumba', 'Cafew', 'Cafew@carnaval.com', '4x4Tainté');

-- --------------------------------------------------------

--
-- Table structure for table `cliniques`
--

CREATE TABLE `cliniques` (
  `idClinique` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `localisation` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cliniques`
--

INSERT INTO `cliniques` (`idClinique`, `nom`, `localisation`) VALUES
(1, 'Perrache', 'Perrache'),
(2, 'VieuxLoy', 'Vieuxlyon'),
(3, 'Clinique du beauvais', 'Bellecour'),
(4, 'Hôtel dieu', 'Guillotière');

-- --------------------------------------------------------

--
-- Table structure for table `emploi_du_temps`
--

CREATE TABLE `emploi_du_temps` (
  `idAgenda` int(11) NOT NULL,
  `idJointure` int(11) NOT NULL,
  `jour` date NOT NULL,
  `heure` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `emploi_du_temps`
--

INSERT INTO `emploi_du_temps` (`idAgenda`, `idJointure`, `jour`, `heure`) VALUES
(1, 8, '2024-05-06', 8),
(2, 8, '2024-05-06', 9),
(3, 8, '2024-05-06', 10),
(4, 8, '2024-05-06', 11),
(5, 8, '2024-05-06', 14),
(6, 8, '2024-05-06', 15),
(8, 10, '2024-05-07', 8),
(10, 10, '2024-05-07', 10),
(12, 10, '2024-05-07', 16),
(14, 9, '2024-05-07', 14),
(15, 9, '2024-05-07', 17);

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

--
-- Dumping data for table `medecins`
--

INSERT INTO `medecins` (`idMedecin`, `nom`, `prenom`, `mail`, `mdp`, `specification`) VALUES
(-2, 'guest', 'guest', '-2', '-2', 'guest'),
(1, 'Fedit', 'Jules', 'fedit@gmail.com', 'jules2003', 'Gynécologue'),
(2, 'Kavakdjian', 'Nel', 'kavak@guillotiere.com', 'armenien', 'Medecin généraliste'),
(3, 'Batherosse', 'Emma', 'emma@beauvais.fr', 'nathan', 'psychologue'),
(5, 'Raphinha', 'GOAT', 'goat@gmail.com', 'raphigoat', 'Infirmier'),
(6, 'Malo', 'Karim', 'benze@gmail.com', 'benzema', 'Podologue'),
(7, 'Koko', 'chanel', 'puntacana@gmail.com', 'putnacnaa', 'Arabe'),
(9, 'zebi', 'zebi', 'zbi@gmail.com', 'puntacana', 'zbei'),
(10, 'Kavakdjian', 'Nel', 'nel@ece.fr', 'fighting', 'Génie du mal');

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

--
-- Dumping data for table `medecin_clinique`
--

INSERT INTO `medecin_clinique` (`idJointure`, `idClinique`, `idMedecin`, `qualification`) VALUES
(1, 2, 1, 'Gynéco'),
(2, 4, 2, 'Neurologue'),
(3, 3, 3, 'Infirmière'),
(4, 4, 1, 'Pediatre'),
(7, 1, 5, 'Infirmier'),
(8, 4, 5, 'Infirmier'),
(9, 2, 6, 'Podologue'),
(10, 3, 6, 'Podologue'),
(11, 1, 7, 'Arabe'),
(13, 1, 9, 'zbei'),
(14, 3, 10, 'Génie du mal');

-- --------------------------------------------------------

--
-- Table structure for table `rdv`
--

CREATE TABLE `rdv` (
  `idRdv` int(11) NOT NULL,
  `note` text NOT NULL,
  `heure` datetime NOT NULL,
  `idClient` int(11) NOT NULL,
  `idJointure` int(11) NOT NULL,
  `etat` enum('Libre','Reserve','Archive') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rdv`
--

INSERT INTO `rdv` (`idRdv`, `note`, `heure`, `idClient`, `idJointure`, `etat`) VALUES
(1, 'Le patient est malade, bv', '2024-04-26 22:35:12', 10, 3, 'Archive'),
(2, 'Il est pas malade, il simule pour ne pas aller bosser.', '2024-04-19 17:23:39', 3, 13, 'Archive'),
(3, 'Nul', '2024-06-13 09:25:21', 5, 11, 'Reserve'),
(4, 'Rien', '2024-04-20 14:49:18', 5, 1, 'Archive'),
(5, 'Cancer de la prostate', '2024-05-08 11:13:26', 4, 7, 'Reserve'),
(6, 'Drogué', '2024-07-12 05:27:28', 4, 4, 'Reserve'),
(7, 'Pourri gaté', '2024-04-03 06:40:21', 4, 3, 'Archive'),
(8, 'RDV à venir', '2024-05-07 15:00:00', 2, 10, 'Reserve'),
(9, 'RDV à venir', '2024-05-06 12:00:00', 2, 4, 'Reserve'),
(10, 'RDV à venir', '2024-05-07 11:00:00', -1, 9, 'Reserve');

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
-- Indexes for table `emploi_du_temps`
--
ALTER TABLE `emploi_du_temps`
  ADD PRIMARY KEY (`idAgenda`),
  ADD KEY `fk_emploiDuTemps_jointure` (`idJointure`);

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
  MODIFY `idClient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `cliniques`
--
ALTER TABLE `cliniques`
  MODIFY `idClinique` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `emploi_du_temps`
--
ALTER TABLE `emploi_du_temps`
  MODIFY `idAgenda` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `medecins`
--
ALTER TABLE `medecins`
  MODIFY `idMedecin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `medecin_clinique`
--
ALTER TABLE `medecin_clinique`
  MODIFY `idJointure` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `rdv`
--
ALTER TABLE `rdv`
  MODIFY `idRdv` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `emploi_du_temps`
--
ALTER TABLE `emploi_du_temps`
  ADD CONSTRAINT `fk_emploiDuTemps_jointure` FOREIGN KEY (`idJointure`) REFERENCES `medecin_clinique` (`idJointure`);

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
