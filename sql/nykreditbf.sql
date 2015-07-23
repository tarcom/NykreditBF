-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Vært: 127.0.0.1
-- Genereringstid: 23. 07 2015 kl. 21:27:09
-- Serverversion: 5.6.24
-- PHP-version: 5.6.8

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
  `id` int(11) NOT NULL,
  `player_red_1` varchar(200) NOT NULL,
  `player_red_2` varchar(200) NOT NULL,
  `player_blue_1` varchar(200) NOT NULL,
  `player_blue_2` varchar(200) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `match_winner` varchar(20) NOT NULL,
  `points_at_steake` int(11) NOT NULL,
  `winning_table` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Data dump for tabellen `tbl_fights`
--

INSERT INTO `tbl_fights` (`id`, `player_red_1`, `player_red_2`, `player_blue_1`, `player_blue_2`, `timestamp`, `match_winner`, `points_at_steake`, `winning_table`) VALUES
(1, 'alsk', 'monn', 'dwp', 'hjsn', '2015-07-23 18:23:49', 'red', 1, 1),
(2, 'ae', 'nikl', 'q1rs', 'cjo', '2015-07-23 18:23:54', 'blue', 1, 2),
(3, 'dwp', 'hjsn', 'cjo', 'alsk', '2015-07-23 18:24:07', 'blue', 1, 1),
(4, 'dwp', 'q1rs', 'cjo', 'monn', '2015-07-23 18:24:13', 'red', 1, 1),
(5, 'alsk', 'dwp', 'monn', 'ae', '2015-07-23 18:24:14', 'blue', 1, 1),
(6, 'alsk', 'nikl', 'ae', 'monn', '2015-07-23 18:24:16', 'blue', 1, 1),
(7, 'alsk', 'hjsn', 'dwp', 'monn', '2015-07-23 18:24:17', 'blue', 1, 2),
(8, 'alsk', 'monn', 'q1rs', 'hjsn', '2015-07-23 18:24:19', 'blue', 1, 2);

-- --------------------------------------------------------

--
-- Struktur-dump for tabellen `tbl_players`
--

CREATE TABLE IF NOT EXISTS `tbl_players` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `playerReady` tinyint(1) NOT NULL DEFAULT '0',
  `oprettet` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=latin1;

--
-- Data dump for tabellen `tbl_players`
--

INSERT INTO `tbl_players` (`id`, `name`, `playerReady`, `oprettet`) VALUES
(3, 'alsk', 1, '2015-07-09 18:34:40'),
(22, 'hjsn', 0, '2015-07-09 20:02:03'),
(56, 'dwp', 0, '2015-07-09 23:08:05'),
(59, 'nikl', 0, '2015-07-23 13:56:43'),
(60, 'monn', 1, '2015-07-23 13:56:45'),
(61, 'cjo', 1, '2015-07-23 14:03:58'),
(62, 'ae', 1, '2015-07-23 14:04:06'),
(63, 'q1rs', 1, '2015-07-23 14:04:11');

-- --------------------------------------------------------

--
-- Struktur-dump for tabellen `tbl_points`
--

CREATE TABLE IF NOT EXISTS `tbl_points` (
  `id` bigint(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `points` int(11) NOT NULL,
  `winning_table` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

--
-- Data dump for tabellen `tbl_points`
--

INSERT INTO `tbl_points` (`id`, `name`, `points`, `winning_table`, `timestamp`) VALUES
(1, 'alsk', 1, 1, '2015-07-23 18:23:49'),
(2, 'monn', 1, 1, '2015-07-23 18:23:49'),
(3, 'dwp', -1, 1, '2015-07-23 18:23:49'),
(4, 'hjsn', -1, 1, '2015-07-23 18:23:49'),
(5, 'ae', -1, 2, '2015-07-23 18:23:54'),
(6, 'nikl', -1, 2, '2015-07-23 18:23:54'),
(7, 'q1rs', 1, 2, '2015-07-23 18:23:54'),
(8, 'cjo', 1, 2, '2015-07-23 18:23:54'),
(9, 'dwp', -1, 1, '2015-07-23 18:24:07'),
(10, 'hjsn', -1, 1, '2015-07-23 18:24:07'),
(11, 'cjo', 1, 1, '2015-07-23 18:24:07'),
(12, 'alsk', 1, 1, '2015-07-23 18:24:07'),
(13, 'dwp', 1, 1, '2015-07-23 18:24:13'),
(14, 'q1rs', 1, 1, '2015-07-23 18:24:13'),
(15, 'cjo', -1, 1, '2015-07-23 18:24:13'),
(16, 'monn', -1, 1, '2015-07-23 18:24:13'),
(17, 'alsk', -1, 1, '2015-07-23 18:24:14'),
(18, 'dwp', -1, 1, '2015-07-23 18:24:14'),
(19, 'monn', 1, 1, '2015-07-23 18:24:14'),
(20, 'ae', 1, 1, '2015-07-23 18:24:14'),
(21, 'alsk', -1, 1, '2015-07-23 18:24:16'),
(22, 'nikl', -1, 1, '2015-07-23 18:24:16'),
(23, 'ae', 1, 1, '2015-07-23 18:24:16'),
(24, 'monn', 1, 1, '2015-07-23 18:24:16'),
(25, 'alsk', -1, 2, '2015-07-23 18:24:17'),
(26, 'hjsn', -1, 2, '2015-07-23 18:24:17'),
(27, 'dwp', 1, 2, '2015-07-23 18:24:17'),
(28, 'monn', 1, 2, '2015-07-23 18:24:17'),
(29, 'alsk', -1, 2, '2015-07-23 18:24:19'),
(30, 'monn', -1, 2, '2015-07-23 18:24:19'),
(31, 'q1rs', 1, 2, '2015-07-23 18:24:19'),
(32, 'hjsn', 1, 2, '2015-07-23 18:24:19');

--
-- Begrænsninger for dumpede tabeller
--

--
-- Indeks for tabel `tbl_fights`
--
ALTER TABLE `tbl_fights`
  ADD PRIMARY KEY (`id`);

--
-- Indeks for tabel `tbl_players`
--
ALTER TABLE `tbl_players`
  ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `name` (`name`);

--
-- Indeks for tabel `tbl_points`
--
ALTER TABLE `tbl_points`
  ADD PRIMARY KEY (`id`);

--
-- Brug ikke AUTO_INCREMENT for slettede tabeller
--

--
-- Tilføj AUTO_INCREMENT i tabel `tbl_fights`
--
ALTER TABLE `tbl_fights`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- Tilføj AUTO_INCREMENT i tabel `tbl_players`
--
ALTER TABLE `tbl_players`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=64;
--
-- Tilføj AUTO_INCREMENT i tabel `tbl_points`
--
ALTER TABLE `tbl_points`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=33;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
