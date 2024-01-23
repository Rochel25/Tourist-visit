-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  sam. 04 juin 2022 à 07:24
-- Version du serveur :  5.7.19
-- Version de PHP :  7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `visite`
--

-- --------------------------------------------------------

--
-- Structure de la table `site`
--

DROP TABLE IF EXISTS `site`;
CREATE TABLE IF NOT EXISTS `site` (
  `NUMSITE` int(5) NOT NULL,
  `NOMSITE` varchar(50) NOT NULL,
  `LIEU` varchar(50) NOT NULL,
  `TARIF` int(50) NOT NULL,
  PRIMARY KEY (`NUMSITE`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `site`
--

INSERT INTO `site` (`NUMSITE`, `NOMSITE`, `LIEU`, `TARIF`) VALUES
(1, 'TSIMBAZAZA', 'TANA', 20000),
(2, 'ISALO', 'IHOSY', 6000),
(3, 'FSDF', 'TANA', 45600);

-- --------------------------------------------------------

--
-- Structure de la table `visiter`
--

DROP TABLE IF EXISTS `visiter`;
CREATE TABLE IF NOT EXISTS `visiter` (
  `NUMVISITEUR` varchar(5) NOT NULL,
  `NUMSITE` int(5) NOT NULL,
  `NBJOURS` int(11) NOT NULL,
  `DATEVISITE` int(11) NOT NULL,
  KEY `NUMVISITEUR` (`NUMVISITEUR`) USING BTREE,
  KEY `NUMSITE` (`NUMSITE`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `visiteur`
--

DROP TABLE IF EXISTS `visiteur`;
CREATE TABLE IF NOT EXISTS `visiteur` (
  `NUMVISITEUR` varchar(5) NOT NULL,
  `NOM` varchar(40) NOT NULL,
  `ADRESSE` varchar(40) NOT NULL,
  PRIMARY KEY (`NUMVISITEUR`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `visiteur`
--

INSERT INTO `visiteur` (`NUMVISITEUR`, `NOM`, `ADRESSE`) VALUES
('1', 'RADO', 'FIANARA'),
('2', 'ANDRIAMBOLOLONA RINDRA', 'Ambalafary');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
