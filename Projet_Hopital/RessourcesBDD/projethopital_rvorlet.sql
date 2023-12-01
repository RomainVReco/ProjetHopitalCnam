-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 16 juin 2023 à 00:16
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `projethopital_rvorlet`
--

-- --------------------------------------------------------

--
-- Structure de la table `adresses`
--

CREATE TABLE `adresses` (
  `idAdresse` int(11) NOT NULL,
  `numero` varchar(10) DEFAULT NULL,
  `adresse1` varchar(35) DEFAULT NULL,
  `adresse2` varchar(35) DEFAULT NULL,
  `codePostal` varchar(10) DEFAULT NULL,
  `ville` varchar(50) DEFAULT NULL,
  `pays` varchar(50) DEFAULT NULL,
  `lastModified` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `adresses`
--

INSERT INTO `adresses` (`idAdresse`, `numero`, `adresse1`, `adresse2`, `codePostal`, `ville`, `pays`, `lastModified`) VALUES
(1, '30', 'rue du Bas Meudon', NULL, '92130', 'Issy les Moulineaux', 'FRANCE', '2023-04-17 23:05:58.991'),
(2, '15', 'rue de Paris', NULL, '75015', 'Paris', 'FRANCE', '2023-04-18 12:17:58.991'),
(3, '45', 'avenue du Maine', 'BAL 75', '92100', 'Boulogne Billancourt', 'FRANCE', '2022-04-18 10:10:58.991'),
(4, '60', 'avenue de la République', NULL, '73200', 'Albertville', 'FRANCE', '2021-04-18 15:20:58.991'),
(5, '75', 'chemin du bus', NULL, '69006', 'Lyon', 'FRANCE', '2020-04-18 20:22:58.991'),
(6, '90', 'allée des chemins', '', '21000', 'Lannion', 'FRANCE', '2020-04-18 15:22:58.991'),
(10, '2ter', 'rue du Dome', NULL, '92100', 'Boulogne Billancourt', 'FRANCE', '2023-05-01T18:42:27.18690'),
(12, '1544', 'avenue Jean Jaurès', 'voie de la voierie', '331001', 'Bordeaux', 'France', '1900-03-17 10:15:00.601'),
(13, '20 bis', 'rue des épaves', NULL, '66310', 'Boulternère', 'FRANCE', '2021-03-17 10:15:00.601 ');

-- --------------------------------------------------------

--
-- Structure de la table `affectation_service`
--

CREATE TABLE `affectation_service` (
  `idService` int(11) NOT NULL,
  `idMedecin` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `affectation_service`
--

INSERT INTO `affectation_service` (`idService`, `idMedecin`) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 4);

-- --------------------------------------------------------

--
-- Structure de la table `consultations`
--

CREATE TABLE `consultations` (
  `idConsultation` int(11) NOT NULL,
  `horodatageDebut` timestamp NULL DEFAULT NULL,
  `horodatageFin` timestamp NULL DEFAULT NULL,
  `detailClinique` varchar(3000) NOT NULL,
  `listePrescription` varchar(500) NOT NULL,
  `idPatient` int(11) NOT NULL,
  `idMedecin` int(11) NOT NULL,
  `listePathologie` varchar(1000) DEFAULT NULL,
  `idMateriel` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `consultations`
--

INSERT INTO `consultations` (`idConsultation`, `horodatageDebut`, `horodatageFin`, `detailClinique`, `listePrescription`, `idPatient`, `idMedecin`, `listePathologie`, `idMateriel`) VALUES
(1, '2023-04-30 19:52:06', '2023-04-30 20:03:38', 'Détail clinique de la consultation numero 1', '1 jour de repos', 1, 1, 'Poil dans la main', NULL),
(2, '2023-04-28 19:52:06', '2023-04-29 20:03:38', 'Détail clinique de la consultation numero 2', '2 jour de repos', 2, 2, 'Fleminginte', NULL),
(3, '2023-03-26 19:52:06', '2023-03-27 20:03:38', 'Détail clinique de la consultation numero 3', '3 jour de repos', 3, 3, 'Fleminginte aigüe', NULL),
(4, '2022-03-26 20:52:06', '2022-03-27 20:03:38', 'Détail clinique de la consultation numero 4', '4 jour de repos', 4, 4, 'Jemenfoutisme caractérisé', NULL),
(5, '2020-01-20 20:52:06', '2020-01-27 21:03:38', 'Détail clinique de la consultation numero 5', '5 jour de repos', 5, 1, 'Typhus, Diphtérie, Dysenterie', NULL),
(6, '2020-02-20 20:52:06', '2020-02-27 21:03:38', 'Détail clinique de la consultation numero 6', '6 jour de repos', 2, 1, 'Sida', NULL),
(999, '2023-05-05 08:00:00', '2023-06-05 08:00:00', 'Jour de repos Test & Update xoxoxoxoxo', 'Update prescription', 7, 4, 'a, b, c, d, e', 1),
(7, '2023-05-05 19:52:06', NULL, 'Détail clinique de la consultation numero 7', '7 jours de repos', 6, 2, 'Dysphorie', 5);

-- --------------------------------------------------------

--
-- Structure de la table `logins`
--

CREATE TABLE `logins` (
  `idLogin` int(11) NOT NULL,
  `login` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `typePoste` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `logins`
--

INSERT INTO `logins` (`idLogin`, `login`, `password`, `typePoste`) VALUES
(1, 'admin', 'admin', 'Administrateur'),
(3, 'glapeshe', 'admin', 'Agent d\'accueil'),
(2, 'rlaennec', 'admin', 'Médecin'),
(999, 'rvorlet', 'poney', 'Médecin');

-- --------------------------------------------------------

--
-- Structure de la table `medecins`
--

CREATE TABLE `medecins` (
  `idSalarie` int(11) NOT NULL,
  `nom` varchar(64) NOT NULL,
  `prenom` varchar(64) NOT NULL,
  `dateEntree` date NOT NULL,
  `dateSortie` date DEFAULT NULL,
  `anciennete` int(11) DEFAULT NULL,
  `remunerationFixe` decimal(11,2) NOT NULL,
  `remunerationVariable` decimal(8,2) NOT NULL,
  `nbreActes` int(11) DEFAULT NULL,
  `obtentionDoctorat` date NOT NULL,
  `idService` int(11) NOT NULL,
  `idSpecialite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `medecins`
--

INSERT INTO `medecins` (`idSalarie`, `nom`, `prenom`, `dateEntree`, `dateSortie`, `anciennete`, `remunerationFixe`, `remunerationVariable`, `nbreActes`, `obtentionDoctorat`, `idService`, `idSpecialite`) VALUES
(1, 'LAENNEC', 'René', '2023-04-18', NULL, 12, '24000.05', '1000.00', NULL, '1811-02-17', 1, 10),
(2, 'PARE', 'Ambroise', '2020-06-25', NULL, 36, '36000.85', '2000.57', NULL, '1540-09-02', 1, 20),
(3, 'CHARCOT', 'Jean-Martin', '2000-11-30', NULL, 92, '99999.99', '9999.99', NULL, '1855-01-23', 2, 11),
(4, 'FLEMING', 'Alexander', '1990-07-01', NULL, 120, '48000.00', '2500.00', NULL, '1911-05-10', 3, 8),
(999, 'TEST', 'Jean', '2023-05-16', NULL, 0, '2500.00', '200.20', 0, '2000-10-01', 4, 16);

-- --------------------------------------------------------

--
-- Structure de la table `patients`
--

CREATE TABLE `patients` (
  `idPatient` int(11) NOT NULL,
  `nom` varchar(64) NOT NULL,
  `prenom` varchar(64) NOT NULL,
  `idAdresse` int(11) DEFAULT NULL,
  `dateCreation` varchar(64) NOT NULL,
  `numeroSS` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `patients`
--

INSERT INTO `patients` (`idPatient`, `nom`, `prenom`, `idAdresse`, `dateCreation`, `numeroSS`) VALUES
(1, 'BONBEURRE', 'Jean', 1, '2023-04-18 12:17:58.991', NULL),
(2, 'AYMARRE', 'Jean', 2, '2023-03-17 12:21:23.601', NULL),
(3, 'REYSCOUSSE', 'Alain', 3, '2021-03-17 10:15:00.601', NULL),
(4, 'COURCI', 'Sarah', 4, '2020-03-17 10:15:00.601', '18566'),
(5, 'AIRE', 'Axelle', 5, '2022-09-24 15:26:58.201', '9999'),
(6, 'STRUEUSE', 'Simone', 6, '2021-03-15 10:15:00.601', '1234567890'),
(7, 'STRUEUSE', 'Simone', 12, '2020-03-17 10:15:00.601', '185');

-- --------------------------------------------------------

--
-- Structure de la table `services`
--

CREATE TABLE `services` (
  `idService` int(11) NOT NULL,
  `nomService` varchar(64) NOT NULL,
  `budget` decimal(20,2) DEFAULT NULL,
  `budgetConsomme` decimal(20,2) DEFAULT NULL,
  `nombrePersonnel` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `services`
--

INSERT INTO `services` (`idService`, `nomService`, `budget`, `budgetConsomme`, `nombrePersonnel`) VALUES
(2, 'Cardiologie', '500000.00', '100.00', 1),
(3, 'Psychiatrie', '3750000.00', '750000.54', 1),
(1, 'Pédiatrie', '1250000.00', '1000000.00', 2),
(4, 'Pédopsychiatrie', '100.00', '55.00', 40),
(999, 'ServiceTest', '999.00', '99.00', 99);

-- --------------------------------------------------------

--
-- Structure de la table `specialites`
--

CREATE TABLE `specialites` (
  `idSpecialite` int(11) NOT NULL,
  `nomSpecialite` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `specialites`
--

INSERT INTO `specialites` (`idSpecialite`, `nomSpecialite`) VALUES
(1, 'Cardiologie'),
(2, 'Chirurgie Cardiaque'),
(3, 'Chirurgie Générale'),
(4, 'Chirurgie Gynécologique'),
(5, 'Chirurgie Maxillo-Faciale'),
(6, 'Chirurgie Oculaire'),
(7, 'Chirurgie Orale'),
(8, 'Chirurgie Pédiatrique'),
(9, 'Chirurgie Plastique, Reconstructive Et Esthétique'),
(10, 'Chirurgie Thoracique'),
(11, 'Chirurgie Traumatologique (En)'),
(12, 'Chirurgie Vasculaire'),
(13, 'Chirurgie Viscérale'),
(14, 'Chirurgie'),
(15, 'Dermatologie'),
(16, 'Gastro-Entérologie'),
(17, 'Gériatrie'),
(18, 'Gynécologie'),
(19, 'Médecine Aiguë'),
(20, 'Médecine Du Travail'),
(21, 'Médecine Urgence'),
(22, 'Médecine Générale'),
(23, 'Médecine Interne'),
(24, 'Médecine Nucléaire'),
(25, 'Médecine Palliative'),
(26, 'Médecine Physique Et De Réadaptation'),
(27, 'Médecine Préventive'),
(28, 'Néonatologie'),
(29, 'Néphrologie'),
(30, 'Neurochirurgie'),
(31, 'Neurologie'),
(32, 'Pédiatrie'),
(33, 'Pneumologie'),
(34, 'Podologie'),
(35, 'Psychiatrie'),
(36, 'Radiologie'),
(37, 'Radiothérapie'),
(38, 'Rhumatologie'),
(39, 'Allergologie'),
(40, 'Anatomie Pathologique'),
(41, 'Andrologie'),
(42, 'Anesthésiologie'),
(43, 'Endocrinologie'),
(44, 'Hématologie'),
(45, 'Hépatologie'),
(46, 'Immunologie'),
(47, 'Infectiologie'),
(48, 'Obstétrique'),
(49, 'Odontologie'),
(50, 'Oncologie'),
(51, 'Ophtalmologie'),
(52, 'Orthopédie'),
(53, 'Otorhinolaryngologie'),
(54, 'Urologie'),
(55, 'Pédopsychiatrie');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `logins`
--
ALTER TABLE `logins`
  ADD UNIQUE KEY `login` (`login`);

--
-- Index pour la table `medecins`
--
ALTER TABLE `medecins`
  ADD PRIMARY KEY (`idSalarie`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
