-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 29 jan. 2018 à 08:22
-- Version du serveur :  5.7.19
-- Version de PHP :  5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `pokecard`
--

-- --------------------------------------------------------

--
-- Structure de la table `ami`
--

DROP TABLE IF EXISTS `ami`;
CREATE TABLE IF NOT EXISTS `ami` (
  `id_friendship` int(11) NOT NULL,
  `login_user1` varchar(250) DEFAULT NULL,
  `login_user2` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id_friendship`),
  KEY `FK_AmiUser1` (`login_user1`),
  KEY `FK_AmiUser2` (`login_user2`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `collection_user`
--

DROP TABLE IF EXISTS `collection_user`;
CREATE TABLE IF NOT EXISTS `collection_user` (
  `id_ligne` int(11) NOT NULL AUTO_INCREMENT,
  `login_user` varchar(250) DEFAULT NULL,
  `id_pokemon` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_ligne`),
  KEY `FK_CollectionUser` (`login_user`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `collection_user`
--

INSERT INTO `collection_user` (`id_ligne`, `login_user`, `id_pokemon`) VALUES
(9, 'toto', 1),
(12, 'titi', 151),
(13, 'toto', 6),
(14, 'toto', 3),
(16, 'toto', 250),
(18, 'toto', 9),
(19, 'titi', 7);

-- --------------------------------------------------------

--
-- Structure de la table `requete_echange`
--

DROP TABLE IF EXISTS `requete_echange`;
CREATE TABLE IF NOT EXISTS `requete_echange` (
  `id_ligne` int(11) NOT NULL AUTO_INCREMENT,
  `login_user` varchar(250) NOT NULL,
  `id_pokemon` int(11) NOT NULL,
  `nom_pokemon` varchar(250) NOT NULL,
  `url` varchar(250) NOT NULL,
  PRIMARY KEY (`id_ligne`),
  KEY `FK_RequeteEchangeUser` (`login_user`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `requete_echange`
--

INSERT INTO `requete_echange` (`id_ligne`, `login_user`, `id_pokemon`, `nom_pokemon`, `url`) VALUES
(80, 'titi', 0, 'undefined', 'undefined');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `login_user` varchar(250) NOT NULL,
  `password` varchar(250) DEFAULT NULL,
  `mail` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`login_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`login_user`, `password`, `mail`) VALUES
('titi', '9cf95dacd226dcf43da376cdb6cbba7035218921', 'titi@gmail.com'),
('toto', '9cf95dacd226dcf43da376cdb6cbba7035218921', 'test@mail.com');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `ami`
--
ALTER TABLE `ami`
  ADD CONSTRAINT `FK_AmiUser1` FOREIGN KEY (`login_user1`) REFERENCES `user` (`login_user`),
  ADD CONSTRAINT `FK_AmiUser2` FOREIGN KEY (`login_user2`) REFERENCES `user` (`login_user`);

--
-- Contraintes pour la table `collection_user`
--
ALTER TABLE `collection_user`
  ADD CONSTRAINT `FK_CollectionUser` FOREIGN KEY (`login_user`) REFERENCES `user` (`login_user`);

--
-- Contraintes pour la table `requete_echange`
--
ALTER TABLE `requete_echange`
  ADD CONSTRAINT `FK_RequeteEchangeUser` FOREIGN KEY (`login_user`) REFERENCES `user` (`login_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
