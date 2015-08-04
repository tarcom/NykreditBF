-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Vært: 127.0.0.1
-- Genereringstid: 03. 08 2015 kl. 22:14:13
-- Serverversion: 5.5.34
-- PHP-version: 5.4.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `nykreditbf`
--

-- --------------------------------------------------------

--
-- Struktur-dump for tabellen `tbl_fights`
--

CREATE TABLE IF NOT EXISTS `tbl_fights` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_red_1` varchar(200) NOT NULL,
  `player_red_2` varchar(200) NOT NULL,
  `player_blue_1` varchar(200) NOT NULL,
  `player_blue_2` varchar(200) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `match_winner` varchar(20) NOT NULL,
  `points_at_steake` int(11) NOT NULL,
  `winning_table` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Data dump for tabellen `tbl_fights`
--

INSERT INTO `tbl_fights` (`id`, `player_red_1`, `player_red_2`, `player_blue_1`, `player_blue_2`, `timestamp`, `match_winner`, `points_at_steake`, `winning_table`) VALUES
(1, 'monn', 'alsk', 'cani', 'dwp', '2015-08-03 09:50:36', 'red', 1, 1),
(2, 'monn', 'cani', 'dwp', 'alsk', '2015-08-03 09:53:05', 'blue', 1, 1),
(3, 'monn', 'dwp', 'alsk', 'cani', '2015-08-03 09:56:06', 'red', 1, 1),
(4, 'dwp', 'cani', 'alsk', 'frmi', '2015-08-03 09:58:42', 'blue', 1, 1),
(5, 'cani', 'dwp', 'alsk', 'monn', '2015-08-03 10:01:51', 'red', 2, 1),
(6, 'cani', 'frmi', 'dwp', 'monn', '2015-08-03 10:05:12', 'blue', 1, 1),
(7, 'peeh', 'alsk', 'frmi', 'dwp', '2015-08-03 13:36:02', 'red', 1, 1),
(8, 'dwp', 'peeh', 'alsk', 'frmi', '2015-08-03 13:38:36', 'red', 1, 1),
(9, 'dwp', 'alsk', 'peeh', 'frmi', '2015-08-03 13:41:09', 'red', 1, 1),
(10, 'dwp', 'frmi', 'peeh', 'alsk', '2015-08-03 13:43:29', 'red', 1, 1),
(11, 'alsk', 'dwp', 'frmi', 'peeh', '2015-08-03 13:46:20', 'red', 1, 1);

-- --------------------------------------------------------

--
-- Struktur-dump for tabellen `tbl_players`
--

CREATE TABLE IF NOT EXISTS `tbl_players` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `playerReady` tinyint(1) NOT NULL DEFAULT '0',
  `oprettet` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Data dump for tabellen `tbl_players`
--

INSERT INTO `tbl_players` (`id`, `name`, `playerReady`, `oprettet`) VALUES
(1, 'alsk', 1, '2015-08-03 09:47:47'),
(2, 'monn', 0, '2015-08-03 09:47:50'),
(3, 'dwp', 1, '2015-08-03 09:47:55'),
(4, 'cani', 0, '2015-08-03 09:47:59'),
(5, 'frmi', 1, '2015-08-03 09:56:01'),
(6, 'peeh', 1, '2015-08-03 13:33:37');

-- --------------------------------------------------------

--
-- Struktur-dump for tabellen `tbl_points`
--

CREATE TABLE IF NOT EXISTS `tbl_points` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `points` int(11) NOT NULL,
  `winning_table` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=45 ;

--
-- Data dump for tabellen `tbl_points`
--

INSERT INTO `tbl_points` (`id`, `name`, `points`, `winning_table`, `timestamp`) VALUES
(1, 'monn', 1, 1, '2015-08-03 09:50:36'),
(2, 'alsk', 1, 1, '2015-08-03 09:50:36'),
(3, 'cani', -1, 1, '2015-08-03 09:50:36'),
(4, 'dwp', -1, 1, '2015-08-03 09:50:36'),
(5, 'monn', -1, 1, '2015-08-03 09:53:05'),
(6, 'cani', -1, 1, '2015-08-03 09:53:05'),
(7, 'dwp', 1, 1, '2015-08-03 09:53:05'),
(8, 'alsk', 1, 1, '2015-08-03 09:53:05'),
(9, 'monn', 1, 1, '2015-08-03 09:56:06'),
(10, 'dwp', 1, 1, '2015-08-03 09:56:06'),
(11, 'alsk', -1, 1, '2015-08-03 09:56:06'),
(12, 'cani', -1, 1, '2015-08-03 09:56:06'),
(13, 'dwp', -1, 1, '2015-08-03 09:58:42'),
(14, 'cani', -1, 1, '2015-08-03 09:58:42'),
(15, 'alsk', 1, 1, '2015-08-03 09:58:42'),
(16, 'frmi', 1, 1, '2015-08-03 09:58:42'),
(17, 'cani', 2, 1, '2015-08-03 10:01:51'),
(18, 'dwp', 2, 1, '2015-08-03 10:01:51'),
(19, 'alsk', -2, 1, '2015-08-03 10:01:51'),
(20, 'monn', -2, 1, '2015-08-03 10:01:51'),
(21, 'cani', -1, 1, '2015-08-03 10:05:12'),
(22, 'frmi', -1, 1, '2015-08-03 10:05:12'),
(23, 'dwp', 1, 1, '2015-08-03 10:05:12'),
(24, 'monn', 1, 1, '2015-08-03 10:05:12'),
(25, 'peeh', 1, 1, '2015-08-03 13:36:02'),
(26, 'alsk', 1, 1, '2015-08-03 13:36:02'),
(27, 'frmi', -1, 1, '2015-08-03 13:36:02'),
(28, 'dwp', -1, 1, '2015-08-03 13:36:02'),
(29, 'dwp', 1, 1, '2015-08-03 13:38:36'),
(30, 'peeh', 1, 1, '2015-08-03 13:38:36'),
(31, 'alsk', -1, 1, '2015-08-03 13:38:36'),
(32, 'frmi', -1, 1, '2015-08-03 13:38:36'),
(33, 'dwp', 1, 1, '2015-08-03 13:41:09'),
(34, 'alsk', 1, 1, '2015-08-03 13:41:09'),
(35, 'peeh', -1, 1, '2015-08-03 13:41:09'),
(36, 'frmi', -1, 1, '2015-08-03 13:41:09'),
(37, 'dwp', 1, 1, '2015-08-03 13:43:29'),
(38, 'frmi', 1, 1, '2015-08-03 13:43:29'),
(39, 'peeh', -1, 1, '2015-08-03 13:43:29'),
(40, 'alsk', -1, 1, '2015-08-03 13:43:29'),
(41, 'alsk', 1, 1, '2015-08-03 13:46:20'),
(42, 'dwp', 1, 1, '2015-08-03 13:46:20'),
(43, 'frmi', -1, 1, '2015-08-03 13:46:20'),
(44, 'peeh', -1, 1, '2015-08-03 13:46:20');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
