-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 25, 2016 at 01:28 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 7.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lonesafedb`
--

-- --------------------------------------------------------

--
-- Table structure for table `jobs`
--

CREATE TABLE `jobs` (
  `job_num` int(255) NOT NULL,
  `isactive` tinyint(1) NOT NULL,
  `username` varchar(20) NOT NULL,
  `date` date NOT NULL,
  `starttime` time NOT NULL,
  `endtime` time NOT NULL,
  `workinghours` tinyint(2) NOT NULL,
  `risklevel` tinyint(1) NOT NULL,
  `needsos` tinyint(1) NOT NULL,
  `checkin1` varchar(20) DEFAULT NULL,
  `checkin2` varchar(20) DEFAULT NULL,
  `checkin3` varchar(20) DEFAULT NULL,
  `checkin4` varchar(20) DEFAULT NULL,
  `checkin5` varchar(20) DEFAULT NULL,
  `checkin6` varchar(20) DEFAULT NULL,
  `checkin7` varchar(20) DEFAULT NULL,
  `checkin8` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `jobs`
--

INSERT INTO `jobs` (`job_num`, `isactive`, `username`, `date`, `starttime`, `endtime`, `workinghours`, `risklevel`, `needsos`, `checkin1`, `checkin2`, `checkin3`, `checkin4`, `checkin5`, `checkin6`, `checkin7`, `checkin8`) VALUES
(109, 0, 'mornez', '2016-10-06', '00:00:00', '00:00:00', 1, 5, 0, 'Escalation 1', 'Escalation 1', 'Escalation 1', NULL, NULL, NULL, NULL, NULL),
(110, 0, 'ash', '2016-10-06', '00:00:00', '00:00:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(111, 0, 'ash', '2016-10-06', '00:00:00', '00:00:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(112, 0, 'ash', '2016-10-06', '00:00:00', '00:00:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(113, 0, 'Roger', '2016-10-06', '00:00:00', '00:00:00', 1, 5, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(114, 0, 'mornez', '2016-10-06', '00:00:00', '00:00:00', 1, 5, 0, 'Escalation 1', 'Cancelled', 'Escalation 1', NULL, NULL, NULL, NULL, NULL),
(115, 0, 'Roger', '2016-10-06', '00:00:00', '00:00:00', 1, 1, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(116, 0, 'Roger', '2016-10-06', '00:00:00', '00:00:00', 1, 1, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(117, 0, 'Roger', '2016-10-06', '00:00:00', '00:00:00', 7, 5, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(118, 0, 'otdrmn', '2016-10-06', '00:00:00', '00:00:00', 1, 5, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(119, 0, 'otdrmn', '2016-10-06', '00:00:00', '00:00:00', 3, 5, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(120, 0, 'otdrmn', '2016-10-06', '00:00:00', '00:00:00', 1, 5, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(121, 0, 'ash', '2016-10-06', '00:00:00', '00:00:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(122, 0, 'otdrmn', '2016-10-06', '00:00:00', '00:00:00', 5, 1, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(123, 0, 'indy', '2016-10-06', '00:00:00', '00:00:00', 1, 1, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(124, 0, 'otdrmn', '2016-10-06', '00:00:00', '00:00:00', 1, 5, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(125, 0, 'indy', '2016-10-06', '00:00:00', '00:00:00', 3, 1, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(126, 0, 'mornez', '2016-10-06', '00:00:00', '00:00:00', 1, 5, 0, 'Escalation 1', 'Cancelled', 'Escalation 1', NULL, NULL, NULL, NULL, NULL),
(127, 0, 'Toby', '2016-10-06', '00:00:00', '00:00:00', 8, 2, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(128, 0, 'Toby', '2016-10-06', '00:00:00', '00:00:00', 12, 1, 0, 'Escalation 1', 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL),
(129, 0, 'Roger', '2016-10-06', '00:00:00', '00:00:00', 1, 5, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(130, 0, 'Roger', '2016-10-06', '00:00:00', '00:00:00', 1, 5, 0, 'Checked', 'Checked', 'Cancelled', NULL, NULL, NULL, NULL, NULL),
(131, 0, 'mornez', '2016-10-06', '00:00:00', '00:00:00', 6, 3, 0, 'Escalation 1', NULL, 'Escalation 1', NULL, NULL, NULL, NULL, NULL),
(132, 0, 'mornez', '2016-10-06', '00:00:00', '00:00:00', 6, 5, 0, 'Escalation 1', 'Cancelled', 'Escalation 1', NULL, NULL, NULL, NULL, NULL),
(133, 0, 'mornez', '2016-10-07', '00:00:00', '00:00:00', 1, 5, 0, 'Escalation 1', 'Checked', '1', 'Cancelled', NULL, NULL, NULL, NULL),
(134, 0, 'test', '2016-10-07', '00:00:00', '00:00:00', 1, 3, 0, 'Escalation 2', 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL),
(135, 0, 'mornez', '2016-10-07', '00:00:00', '00:00:00', 3, 5, 0, 'Escalation 1', 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL),
(136, 0, 'Roger', '2016-10-07', '00:00:00', '00:00:00', 1, 5, 1, '3', 'Checked', 'Checked', 'Checked', 'Checked', 'Checked', 'Cancelled', NULL),
(137, 0, 'ash', '2016-10-07', '00:00:00', '00:00:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(138, 0, 'ash', '2016-10-08', '00:00:00', '00:00:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(140, 0, 'ash', '2016-10-08', '00:00:00', '00:00:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(146, 0, 'ash', '2016-10-08', '00:00:00', '00:00:00', 5, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(164, 0, 'ash', '2016-10-09', '16:57:00', '17:57:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(166, 0, 'ash', '2016-10-09', '17:16:00', '18:16:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(171, 0, 'ash', '2016-10-09', '18:57:00', '19:57:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(180, 0, 'ash', '2016-10-09', '20:11:00', '22:11:00', 2, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(182, 0, 'ash', '2016-10-09', '23:43:00', '00:04:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(183, 0, 'ash', '2016-10-09', '00:09:00', '00:27:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(184, 0, 'ash', '2016-10-09', '00:38:00', '00:38:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(185, 0, 'ash', '2016-10-09', '02:17:00', '02:17:00', 3, 3, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(186, 0, 'test', '2016-10-09', '01:00:00', '01:00:00', 1, 3, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(187, 0, 'test', '2016-10-09', '01:00:00', '01:00:00', 1, 3, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(188, 0, 'test', '2016-10-09', '01:00:00', '01:00:00', 2, 3, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(189, 0, 'test', '2016-10-09', '01:00:00', '01:00:00', 1, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(190, 0, 'test', '2016-10-09', '01:00:00', '01:00:00', 1, 4, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(191, 0, 'test', '2016-10-09', '01:00:00', '01:00:00', 1, 4, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(192, 0, 'test', '2016-10-09', '01:00:00', '01:00:00', 1, 4, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(193, 0, 'Roger', '2016-10-09', '01:00:00', '01:00:00', 1, 5, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(194, 0, 'test', '2016-10-09', '01:00:00', '01:00:00', 1, 4, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(196, 0, 'mornez', '2016-10-09', '01:00:00', '01:00:00', 3, 5, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(197, 0, 'Toby', '2016-10-10', '01:00:00', '01:00:00', 3, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(198, 0, 'mornez', '2016-10-10', '01:00:00', '01:00:00', 1, 5, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(199, 0, 'mornez', '2016-10-10', '01:00:00', '01:00:00', 1, 5, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(200, 0, 'Toby', '2016-10-10', '01:00:00', '01:00:00', 1, 5, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(201, 0, 'Toby', '2016-10-10', '01:00:00', '01:00:00', 1, 4, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(202, 0, 'Toby', '2016-10-10', '01:00:00', '01:00:00', 1, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(203, 0, 'Toby', '2016-10-10', '01:00:00', '01:00:00', 1, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(204, 0, 'ash', '2016-10-10', '14:25:00', '14:25:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(205, 0, 'ash', '2016-10-10', '14:33:00', '14:33:00', 2, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(206, 0, 'Toby', '2016-10-10', '14:35:00', '14:42:00', 1, 3, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(207, 0, 'mornez', '2016-10-10', '01:00:00', '01:00:00', 2, 5, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(208, 0, 'Toby', '2016-10-10', '15:20:00', '15:29:00', 1, 3, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(209, 0, 'ash', '2016-10-10', '15:23:00', '15:43:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(210, 0, 'mornez', '2016-10-10', '01:00:00', '01:00:00', 1, 1, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(211, 0, 'Toby', '2016-10-10', '15:29:00', '16:29:00', 1, 3, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(212, 0, 'Toby', '2016-10-10', '01:00:00', '01:00:00', 1, 4, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(213, 0, 'Toby', '2016-10-10', '15:40:00', '16:40:00', 1, 5, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(214, 0, 'Toby', '2016-10-10', '01:00:00', '01:00:00', 2, 4, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(215, 0, 'Toby', '2016-10-10', '15:42:00', '16:42:00', 1, 5, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(216, 0, 'Toby', '2016-10-10', '15:44:00', '16:02:00', 1, 5, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(217, 0, 'Toby', '2016-10-10', '16:30:00', '16:58:00', 1, 5, 0, 'Checked', 'Checked', 'Cancelled', NULL, NULL, NULL, NULL, NULL),
(218, 0, 'ash', '2016-10-10', '17:07:00', '17:11:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(219, 0, 'Toby', '2016-10-10', '21:51:00', '22:51:00', 1, 1, 0, 'Checked', 'Checked', NULL, NULL, NULL, NULL, NULL, NULL),
(220, 0, 'ash', '2016-10-10', '23:14:00', '23:49:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(221, 0, 'ash', '2016-10-10', '23:50:00', '23:54:00', 2, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(222, 0, 'ash', '2016-10-10', '23:54:00', '23:56:00', 3, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(223, 0, 'ash', '2016-10-10', '23:58:00', '00:58:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(224, 0, 'ash', '2016-10-10', '00:50:00', '00:50:00', 3, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(225, 0, 'mornez', '2016-10-10', '01:00:00', '01:00:00', 1, 1, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(226, 0, 'test', '2016-10-10', '01:00:00', '01:00:00', 1, 3, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(227, 0, 'mornez', '2016-10-10', '08:27:00', '08:38:00', 1, 1, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(228, 0, 'test', '2016-10-10', '01:00:00', '01:00:00', 1, 3, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(229, 0, 'test', '2016-10-10', '09:40:00', '09:55:00', 1, 3, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(230, 0, 'mornez', '2016-10-10', '09:45:00', '09:46:00', 5, 4, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(231, 0, 'mornez', '2016-10-10', '09:46:00', '09:59:00', 1, 5, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(232, 0, 'indy', '2016-10-11', '01:00:00', '01:00:00', 1, 4, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(233, 0, 'indy', '2016-10-11', '01:00:00', '01:00:00', 1, 4, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(234, 0, 'indy', '2016-10-11', '01:00:00', '01:00:00', 1, 4, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(235, 0, 'indy', '2016-10-11', '01:00:00', '01:00:00', 1, 4, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(236, 0, 'indy', '2016-10-11', '01:00:00', '01:00:00', 1, 4, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(237, 0, 'indy', '2016-10-11', '01:00:00', '01:00:00', 1, 4, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(238, 0, 'indy', '2016-10-11', '01:00:00', '01:00:00', 1, 4, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(239, 0, 'indy', '2016-10-11', '01:00:00', '01:00:00', 1, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(240, 0, 'indy', '2016-10-11', '01:00:00', '01:00:00', 1, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(241, 0, 'Toby', '2016-10-11', '14:42:00', '14:43:00', 1, 1, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(242, 0, 'Toby', '2016-10-11', '14:43:00', '15:23:00', 1, 1, 0, 'Checked', 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL),
(243, 0, 'Toby', '2016-10-11', '15:23:00', '15:24:00', 5, 5, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(244, 0, 'Toby', '2016-10-11', '15:36:00', '15:42:00', 5, 3, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(245, 0, 'Toby', '2016-10-11', '15:44:00', '15:45:00', 5, 3, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(246, 0, 'Toby', '2016-10-11', '15:45:00', '16:26:00', 5, 3, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(247, 0, 'mornez', '2016-10-11', '15:52:00', '16:11:00', 1, 5, 0, 'Escalation 1', 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL),
(248, 0, 'Toby', '2016-10-11', '16:48:00', '17:48:00', 1, 1, 0, 'Checked', 'Checked', NULL, NULL, NULL, NULL, NULL, NULL),
(249, 0, 'mornez', '2016-10-11', '07:32:00', '07:37:00', 1, 5, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(250, 0, 'mornez', '2016-10-11', '07:37:00', '07:42:00', 1, 5, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(251, 0, 'mornez', '2016-10-11', '07:44:00', '09:59:00', 1, 5, 0, 'Escalation 1', 'Checked', 'Checked', 'Checked', 'Checked', 'Checked', 'Checked', 'Checked'),
(252, 0, 'ash', '2016-10-11', '08:59:00', '10:23:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(253, 0, 'mornez', '2016-10-12', '12:24:00', '12:25:00', 1, 5, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(254, 0, 'mornez', '2016-10-12', '12:25:00', '12:26:00', 1, 5, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(255, 0, 'mornez', '2016-10-12', '12:27:00', '13:06:00', 1, 1, 0, 'Escalation 1', 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL),
(256, 0, 'ash', '2016-10-12', '13:04:00', '14:11:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(257, 0, 'ash', '2016-10-12', '15:23:00', '15:32:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(258, 0, 'Toby', '2016-10-12', '19:41:00', '19:41:00', 1, 1, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(259, 0, 'indy', '2016-10-13', '01:00:00', '01:00:00', 4, 3, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(260, 0, 'indy', '2016-10-13', '01:00:00', '01:00:00', 4, 3, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(261, 0, 'mornez', '2016-10-13', '17:11:00', '18:08:00', 1, 5, 0, 'Escalation 1', 'Checked', 'Checked', 'Checked', 'Cancelled', NULL, NULL, NULL),
(262, 0, 'indy', '2016-10-13', '18:07:00', '18:08:00', 1, 1, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(263, 0, 'indy', '2016-10-13', '18:10:00', '18:10:00', 3, 3, 1, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(264, 0, 'ash', '2016-10-13', '18:27:00', '19:27:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(265, 0, 'ash', '2016-10-13', '20:13:00', '22:13:00', 2, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(266, 0, 'ash', '2016-10-13', '20:15:00', '22:31:00', 2, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(268, 0, 'Toby', '2016-10-13', '22:24:00', '23:24:00', 8, 5, 0, 'Checked', 'Checked', 'Cancelled', NULL, NULL, NULL, NULL, NULL),
(269, 0, 'ash', '2016-10-13', '22:42:00', '23:42:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(270, 0, 'ash', '2016-10-13', '22:47:00', '23:47:00', 1, 4, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(271, 0, 'buzz', '2016-10-13', '22:47:00', '00:47:00', 2, 2, 0, '1', NULL, NULL, 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', NULL),
(272, 0, 'ash', '2016-10-13', '22:49:00', '23:49:00', 1, 4, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(273, 0, 'ash', '2016-10-13', '00:14:00', '01:14:00', 1, 4, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(274, 0, 'buzz', '2016-10-13', '00:16:00', '01:18:00', 1, 1, 0, 'Checked', 'Cancelled', NULL, 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', NULL),
(275, 0, 'ash', '2016-10-21', '04:00:00', '00:00:00', 1, 1, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(276, 0, 'ash', '2016-10-21', '04:00:00', '00:00:00', 1, 1, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(277, 0, 'ash', '2016-10-15', '05:09:17', '03:00:00', 1, 1, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(278, 0, 'ash', '2016-10-13', '09:49:00', '10:30:00', 1, 4, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(279, 0, 'buzz', '2016-10-13', '10:16:00', '10:30:00', 1, 1, 0, 'Cancelled', NULL, NULL, 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', NULL),
(280, 0, 'ash', '2016-10-13', '10:31:00', '10:32:00', 1, 4, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(281, 0, 'ash', '2016-10-13', '10:36:00', '11:36:00', 1, 4, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(282, 0, 'buzz', '2016-10-13', '10:38:00', '11:54:00', 1, 1, 0, 'Checked', 'Checked', 'Cancelled', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', NULL),
(283, 0, 'indy', '2016-10-14', '11:59:00', '13:00:00', 1, 3, 0, 'Checked', 'Checked', 'Checked', 'Cancelled', NULL, NULL, NULL, NULL),
(284, 0, 'buzz', '2016-10-14', '12:01:00', '13:02:00', 1, 1, 0, 'Checked', 'Checked', 'Cancelled', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', NULL),
(285, 0, 'ash', '2016-10-14', '12:17:00', '13:23:00', 1, 4, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(286, 0, 'ash', '2016-10-14', '13:29:00', '15:29:00', 2, 2, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(287, 0, 'ash', '2016-10-14', '13:29:00', '15:29:00', 2, 2, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(288, 0, 'Toby', '2016-10-14', '13:56:00', '14:56:00', 1, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(289, 0, 'Toby', '2016-10-14', '14:14:00', '15:14:00', 1, 5, 0, 'Checked', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(290, 0, 'ash', '2016-10-14', '14:41:00', '15:41:00', 1, 2, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(291, 0, 'Toby', '2016-10-14', '14:41:00', '14:42:00', 1, 5, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(292, 0, 'Toby', '2016-10-14', '14:43:00', '19:10:00', 7, 3, 0, 'Checked', 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL),
(293, 0, 'ash', '2016-10-14', '15:06:00', '15:09:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(294, 0, 'ash', '2016-10-14', '15:10:00', '15:37:00', 1, 1, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(295, 0, 'buzz', '2016-10-14', '15:18:00', '16:45:00', 1, 3, 0, 'Checked', 'Checked', 'Checked', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', NULL),
(296, 0, 'ash', '2016-10-14', '15:42:00', '16:42:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(297, 0, 'ash', '2016-10-14', '17:41:00', '18:41:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(298, 0, 'ash', '2016-10-14', '19:07:00', '20:07:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(299, 0, 'Toby', '2016-10-14', '19:11:00', '19:11:00', 7, 3, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(300, 0, 'Toby', '2016-10-14', '19:12:00', '22:48:00', 1, 1, 0, 'Checked', 'Escalation 1', 'Cancelled', NULL, NULL, NULL, NULL, NULL),
(301, 0, 'ash', '2016-10-14', '19:12:00', '22:12:00', 3, 3, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(302, 0, 'ash', '2016-10-14', '19:47:00', '23:47:00', 4, 2, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(303, 0, 'ash', '2016-10-14', '19:50:00', '19:51:00', 4, 2, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(305, 0, 'ash', '2016-10-14', '19:55:00', '23:55:00', 4, 2, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(306, 0, 'ash', '2016-10-14', '20:05:00', '00:05:00', 4, 2, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(307, 0, 'ash', '2016-10-14', '20:09:00', '20:10:00', 4, 2, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(308, 0, 'ash', '2016-10-14', '20:13:00', '01:13:00', 5, 2, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(309, 0, 'ash', '2016-10-14', '20:22:00', '01:22:00', 5, 2, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(310, 0, 'ash', '2016-10-14', '20:25:00', '23:25:00', 3, 2, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(311, 0, 'ash', '2016-10-14', '20:30:00', '20:31:00', 1, 2, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(312, 0, 'ash', '2016-10-14', '20:33:00', '21:33:00', 1, 2, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(313, 0, 'ash', '2016-10-14', '20:37:00', '20:37:00', 1, 2, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(314, 0, 'ash', '2016-10-14', '20:43:00', '20:44:00', 1, 2, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(315, 0, 'ash', '2016-10-14', '20:46:00', '21:46:00', 1, 2, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(316, 0, 'ash', '2016-10-14', '20:50:00', '21:50:00', 1, 2, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(317, 0, 'ash', '2016-10-14', '20:55:00', '22:55:00', 2, 2, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(318, 0, 'ash', '2016-10-14', '21:08:00', '23:08:00', 2, 2, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(319, 0, 'ash', '2016-10-14', '21:17:00', '23:17:00', 2, 2, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(320, 0, 'ash', '2016-10-14', '21:22:00', '23:22:00', 2, 2, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(321, 0, 'ash', '2016-10-14', '21:29:00', '23:29:00', 2, 2, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(322, 0, 'ash', '2016-10-14', '21:38:00', '23:38:00', 2, 2, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(323, 0, 'ash', '2016-10-14', '21:41:00', '23:41:00', 2, 2, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(324, 0, 'ash', '2016-10-14', '21:44:00', '22:44:00', 1, 2, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(325, 0, 'ash', '2016-10-14', '21:50:00', '22:50:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(327, 0, 'ash', '2016-10-14', '23:04:00', '23:11:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(328, 0, 'ash', '2016-10-14', '23:12:00', '00:12:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(331, 0, 'buzz', '2016-10-14', '23:45:00', '00:45:00', 1, 5, 0, NULL, NULL, NULL, 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', NULL),
(335, 0, 'Toby', '2016-10-14', '00:57:00', '00:58:00', 8, 4, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(338, 0, 'ash', '2016-10-14', '02:06:00', '02:06:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(339, 0, 'ash', '2016-10-14', '02:07:00', '03:07:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(340, 0, 'ash', '2016-10-14', '02:31:00', '02:46:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(341, 0, 'ash', '2016-10-14', '03:00:00', '03:02:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(342, 0, 'ash', '2016-10-14', '03:05:00', '04:05:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(343, 0, 'buzz', '2016-10-14', '04:06:00', '04:06:00', 1, 1, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(344, 0, 'mornez', '2016-10-14', '08:01:00', '08:01:00', 1, 5, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(345, 0, 'mornez', '2016-10-14', '08:01:00', '09:01:00', 1, 1, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(346, 0, 'mornez', '2016-10-14', '09:06:00', '09:07:00', 1, 1, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(347, 0, 'buzz', '2016-10-15', '14:06:00', '15:06:00', 1, 5, 0, 'Checked', 'Checked', 'Checked', 'Checked', NULL, NULL, NULL, NULL),
(348, 0, 'ash', '2016-10-15', '14:08:00', '15:08:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(349, 0, 'buzz', '2016-10-15', '15:09:00', '16:09:00', 1, 5, 0, 'Checked', 'Checked', 'Checked', 'Checked', NULL, NULL, NULL, NULL),
(350, 0, 'Toby', '2016-10-15', '18:33:00', '18:41:00', 8, 4, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(351, 0, 'ash', '2016-10-15', '18:36:00', '19:36:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(352, 0, 'buzz', '2016-10-15', '18:36:00', '20:36:00', 2, 4, 0, 'Checked', 'Checked', 'Checked', 'Checked', 'Checked', NULL, NULL, NULL),
(353, 0, 'Toby', '2016-10-15', '19:03:00', '19:06:00', 8, 4, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(354, 0, 'Toby', '2016-10-15', '19:10:00', '19:13:00', 8, 4, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(355, 0, 'Andy', '2016-10-15', '19:17:00', '19:19:00', 1, 3, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(356, 0, 'Toby', '2016-10-15', '19:19:00', '19:38:00', 1, 5, 0, 'Checked', 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL),
(357, 0, 'Andy', '2016-10-15', '19:19:00', '19:20:00', 2, 4, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(358, 0, 'Toby', '2016-10-15', '00:26:00', '01:26:00', 1, 5, 0, 'Checked', 'Checked', 'Checked', 'Checked', NULL, NULL, NULL, NULL),
(359, 0, 'Toby', '2016-10-15', '01:41:00', '01:41:00', 1, 5, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(418, 0, 'ash', '2016-10-16', '04:58:00', '05:58:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(419, 0, 'buzz', '2016-10-16', '05:28:00', '05:31:00', 1, 5, 1, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(420, 0, 'mornez', '2016-10-16', '07:38:00', '08:38:00', 1, 1, 0, 'Escalation 1', 'Checked', 'Checked', NULL, NULL, NULL, NULL, NULL),
(421, 0, 'mornez', '2016-10-16', '08:39:00', '09:27:00', 1, 1, 0, 'Escalation 1', 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL),
(422, 0, 'mornez', '2016-10-16', '09:27:00', '09:27:00', 1, 1, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(423, 0, 'mornez', '2016-10-16', '09:27:00', '09:27:00', 1, 2, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(424, 0, 'mornez', '2016-10-16', '09:27:00', '09:27:00', 1, 3, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(425, 0, 'mornez', '2016-10-16', '09:28:00', '09:28:00', 1, 4, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(426, 0, 'mornez', '2016-10-16', '09:28:00', '09:28:00', 1, 5, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(427, 0, 'mornez', '2016-10-16', '09:29:00', '10:28:00', 2, 1, 0, 'Escalation 1', 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL),
(428, 0, 'ash', '2016-10-17', '16:00:00', '17:00:00', 1, 1, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(429, 0, 'ash', '2016-10-17', '21:15:00', '22:15:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(430, 0, 'mornez', '2016-10-17', '08:30:00', '20:30:00', 12, 1, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(431, 0, 'mornez', '2016-10-17', '08:30:00', '08:41:00', 12, 1, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(432, 0, 'ash', '2016-10-17', '09:16:00', '10:10:00', 2, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(433, 0, 'Toby', '2016-10-17', '09:26:00', '09:27:00', 1, 1, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(434, 0, 'Toby', '2016-10-17', '09:27:00', '09:32:00', 1, 1, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(435, 0, 'Andy', '2016-10-17', '09:33:00', '09:33:00', 1, 1, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(436, 0, 'Andy', '2016-10-17', '09:43:00', '10:43:00', 1, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(438, 0, 'Andy', '2016-10-17', '10:04:00', '10:04:00', 1, 1, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(439, 0, 'Andy', '2016-10-17', '10:05:00', '10:05:00', 1, 1, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(440, 0, 'Andy', '2016-10-17', '10:07:00', '10:09:00', 1, 5, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(442, 0, 'Andy', '2016-10-17', '10:09:00', '10:10:00', 1, 5, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(443, 0, 'ash', '2016-10-17', '10:11:00', '10:12:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(444, 0, 'Andy', '2016-10-17', '10:12:00', '10:19:00', 1, 5, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(445, 0, 'Andy', '2016-10-17', '10:36:00', '11:36:00', 1, 5, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(446, 0, 'Andy', '2016-10-17', '10:36:00', '11:10:00', 1, 5, 0, 'Checked', 'Escalation 1', 'Cancelled', NULL, NULL, NULL, NULL, NULL),
(448, 0, 'Toby', '2016-10-18', '11:58:00', '12:01:00', 1, 1, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(449, 0, 'ash', '2016-10-18', '12:04:00', '12:27:00', 2, 3, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(450, 0, 'Toby', '2016-10-18', '12:08:00', '12:19:00', 10, 4, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(451, 0, 'mornez', '2016-10-18', '12:20:00', '12:21:00', 1, 1, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(452, 0, 'mornez', '2016-10-18', '12:21:00', '12:21:00', 1, 2, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(453, 0, 'mornez', '2016-10-18', '12:21:00', '12:21:00', 1, 3, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(454, 0, 'mornez', '2016-10-18', '12:21:00', '12:21:00', 1, 4, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(455, 0, 'mornez', '2016-10-18', '12:21:00', '12:22:00', 1, 5, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(456, 0, 'mornez', '2016-10-18', '12:22:00', '12:22:00', 2, 1, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(457, 0, 'mornez', '2016-10-18', '12:22:00', '12:22:00', 2, 2, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(458, 0, 'mornez', '2016-10-18', '12:22:00', '12:22:00', 2, 3, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(459, 0, 'mornez', '2016-10-18', '12:22:00', '12:23:00', 2, 4, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(460, 0, 'mornez', '2016-10-18', '12:23:00', '12:23:00', 2, 5, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(461, 0, 'mornez', '2016-10-18', '12:23:00', '12:23:00', 3, 1, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(462, 0, 'mornez', '2016-10-18', '12:23:00', '12:23:00', 3, 2, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(463, 0, 'mornez', '2016-10-18', '12:23:00', '12:24:00', 3, 3, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(464, 0, 'mornez', '2016-10-18', '12:24:00', '12:24:00', 3, 4, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(465, 0, 'mornez', '2016-10-18', '12:24:00', '12:24:00', 3, 5, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(466, 0, 'mornez', '2016-10-18', '12:24:00', '12:24:00', 4, 1, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(467, 0, 'mornez', '2016-10-18', '12:24:00', '12:25:00', 4, 2, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(468, 0, 'mornez', '2016-10-18', '12:25:00', '12:25:00', 4, 3, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(469, 0, 'mornez', '2016-10-18', '12:25:00', '12:25:00', 4, 4, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(470, 0, 'mornez', '2016-10-18', '12:25:00', '12:56:00', 4, 5, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(471, 0, 'ash', '2016-10-18', '12:30:00', '12:32:00', 1, 3, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(472, 0, 'ash', '2016-10-18', '12:35:00', '12:37:00', 1, 4, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(474, 0, 'Toby', '2016-10-18', '13:18:00', '14:39:00', 10, 4, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(478, 0, 'ash', '2016-10-18', '13:58:00', '13:59:00', 2, 4, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(479, 0, 'ash', '2016-10-18', '14:03:00', '14:10:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(480, 0, 'ash', '2016-10-18', '14:16:00', '15:16:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(481, 0, 'ash', '2016-10-18', '15:25:00', '16:31:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(483, 0, 'ash', '2016-10-18', '17:48:00', '18:53:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(484, 0, 'ash', '2016-10-18', '18:54:00', '18:54:00', 4, 3, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(485, 0, 'Andy', '2016-10-18', '09:54:00', '09:54:00', 2, 2, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(486, 0, 'mornez', '2016-10-19', '12:29:00', '12:29:00', 1, 1, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(487, 0, 'mornez', '2016-10-19', '12:29:00', '12:29:00', 1, 5, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(488, 0, 'Toby', '2016-10-19', '14:21:00', '14:21:00', 10, 4, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(489, 0, 'Toby', '2016-10-19', '14:56:00', '14:57:00', 10, 4, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(490, 0, 'ash', '2016-10-19', '14:57:00', '14:58:00', 1, 5, 0, 'Escalation 3', 'Escalation 1', 'Escalation 1', 'Escalation 2', 'Escalation 1', 'Escalation 1', 'Escalation 1', 'Escalation 1'),
(494, 0, 'mornez', '2016-10-19', '08:18:00', '09:18:00', 1, 1, 0, 'Escalation 1', 'Checked', NULL, NULL, NULL, NULL, NULL, NULL),
(495, 0, 'mornez', '2016-10-19', '09:39:00', '09:39:00', 1, 5, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(496, 0, 'mornez', '2016-10-19', '09:39:00', '10:59:00', 12, 5, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(497, 0, 'ash', '2016-10-20', '15:28:00', '15:34:00', 1, 5, 0, 'Cancelled', 'Escalation 1', 'Escalation 1', NULL, NULL, NULL, NULL, NULL),
(498, 0, 'Toby', '2016-10-20', '15:32:00', '15:36:00', 2, 5, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(499, 0, 'Andy', '2016-10-20', '15:34:00', '15:34:00', 2, 2, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(500, 0, 'Toby', '2016-10-20', '15:38:00', '15:44:00', 2, 5, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(501, 0, 'ash', '2016-10-20', '15:41:00', '15:42:00', 1, 5, 0, 'Cancelled', 'Escalation 1', 'Escalation 1', NULL, NULL, NULL, NULL, NULL),
(502, 0, 'Toby', '2016-10-20', '15:44:00', '15:45:00', 2, 5, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(503, 0, 'Toby', '2016-10-20', '15:45:00', '15:55:00', 5, 1, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(504, 0, 'ash', '2016-10-20', '15:49:00', '15:53:00', 1, 5, 0, 'Cancelled', 'Escalation 1', 'Escalation 1', NULL, NULL, NULL, NULL, NULL),
(505, 0, 'Toby', '2016-10-20', '17:23:00', '17:44:00', 11, 5, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(506, 0, 'ash', '2016-10-20', '19:37:00', '21:37:00', 2, 5, 0, NULL, 'Escalation 1', 'Escalation 1', NULL, NULL, NULL, NULL, NULL),
(507, 0, 'ash', '2016-10-20', '19:37:00', '21:37:00', 2, 5, 0, 'Checked', 'Escalation 1', 'Escalation 1', 'Checked', 'Checked', 'Checked', NULL, NULL),
(508, 0, 'mornez', '2016-10-20', '20:27:00', '20:29:00', 1, 5, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(509, 0, 'ash', '2016-10-20', '21:39:00', '21:41:00', 1, 5, 0, 'Checked', 'Escalation 1', 'Escalation 1', NULL, NULL, NULL, NULL, NULL),
(510, 0, 'ash', '2016-10-20', '21:43:00', '21:43:00', 1, 1, 0, 'Checked', 'Escalation 1', 'Escalation 1', NULL, NULL, NULL, NULL, NULL),
(511, 0, 'ash', '2016-10-20', '23:34:00', '23:36:00', 1, 5, 0, 'Checked', 'Escalation 1', 'Escalation 1', NULL, NULL, NULL, NULL, NULL),
(512, 0, 'ash', '2016-10-20', '23:37:00', '23:40:00', 4, 5, 0, 'Checked', 'Escalation 1', 'Escalation 1', 'Checked', NULL, 'Checked', 'Cancelled', NULL),
(513, 0, 'ash', '2016-10-20', '01:10:00', '01:11:00', 1, 5, 0, 'Cancelled', 'Escalation 1', 'Escalation 1', NULL, NULL, NULL, NULL, NULL),
(514, 0, 'ash', '2016-10-20', '01:12:00', '01:14:00', 1, 5, 0, 'Checked', 'Escalation 1', 'Escalation 1', 'Cancelled', NULL, NULL, NULL, NULL),
(515, 0, 'ash', '2016-10-20', '01:14:00', '01:14:00', 1, 5, 0, 'Checked', 'Escalation 1', 'Escalation 1', NULL, NULL, NULL, NULL, NULL),
(516, 0, 'Andy', '2016-10-20', '07:23:00', '08:26:00', 2, 4, 0, 'Checked', 'Checked', 'Cancelled', NULL, NULL, NULL, NULL, NULL),
(517, 0, 'ash', '2016-10-20', '07:50:00', '07:51:00', 5, 1, 0, 'Cancelled', 'Escalation 1', 'Escalation 1', NULL, NULL, NULL, NULL, NULL),
(518, 0, 'ash', '2016-10-20', '08:19:00', '08:20:00', 1, 5, 0, 'Checked', 'Escalation 1', 'Escalation 1', NULL, NULL, NULL, NULL, NULL),
(519, 0, 'Toby', '2016-10-20', '08:22:00', '08:41:00', 11, 5, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(520, 0, 'ash', '2016-10-20', '08:40:00', '08:40:00', 3, 5, 0, 'Cancelled', 'Escalation 1', 'Escalation 1', NULL, NULL, NULL, NULL, NULL),
(521, 0, 'mornez', '2016-10-20', '09:49:00', '10:00:00', 1, 5, 0, 'Escalation 1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(522, 0, 'mornez', '2016-10-20', '10:00:00', '10:35:00', 1, 5, 0, 'Escalation 1', 'Checked', 'Cancelled', NULL, NULL, NULL, NULL, NULL),
(523, 0, 'ash', '2016-10-21', '12:52:00', '12:53:00', 3, 5, 0, 'Checked', 'Escalation 1', 'Escalation 1', NULL, NULL, NULL, NULL, NULL),
(524, 0, 'Andy', '2016-10-21', '12:55:00', '12:56:00', 2, 4, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(525, 0, 'ash', '2016-10-21', '17:56:00', '17:57:00', 1, 5, 0, 'Checked', 'Escalation 1', 'Escalation 1', NULL, NULL, NULL, NULL, NULL),
(526, 0, 'buzz', '2016-10-22', '16:39:00', '16:49:00', 2, 5, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(527, 0, 'ash', '2016-10-22', '16:40:00', '16:45:00', 1, 5, 0, 'Checked', 'Escalation 1', 'Escalation 1', 'Cancelled', NULL, NULL, NULL, NULL),
(528, 0, 'Toby', '2016-10-22', '16:50:00', '17:13:00', 1, 2, 0, 'Checked', 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL),
(529, 0, 'ash', '2016-10-22', '16:51:00', '16:57:00', 1, 5, 0, 'Checked', 'Checked', 'Escalation 1', 'Checked', 'Checked', 'Cancelled', NULL, NULL),
(530, 0, 'ash', '2016-10-22', '17:09:00', '17:10:00', 1, 5, 0, 'Checked', 'Checked', 'Checked', 'Cancelled', NULL, NULL, NULL, NULL),
(532, 0, 'ash', '2016-10-22', '00:01:00', '00:02:00', 1, 1, 0, 'Checked', 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL),
(533, 0, 'ash', '2016-10-22', '00:03:00', '00:03:00', 1, 5, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(534, 0, 'ash', '2016-10-22', '00:06:00', '00:07:00', 1, 1, 0, 'Checked', 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL),
(535, 0, 'ash', '2016-10-24', '15:34:00', '15:34:00', 3, 4, 0, 'Checked', 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL),
(536, 0, 'Toby', '2016-10-24', '22:52:00', '22:53:00', 8, 4, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(537, 0, 'Toby', '2016-10-24', '22:53:00', '23:01:00', 8, 1, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(538, 0, 'Toby', '2016-10-24', '23:01:00', '23:03:00', 5, 3, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(539, 0, 'Toby', '2016-10-24', '23:03:00', '23:05:00', 5, 3, 0, 'Cancelled', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

CREATE TABLE `location` (
  `id` int(11) NOT NULL,
  `job_num` int(11) NOT NULL,
  `time` time DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `location`
--

INSERT INTO `location` (`id`, `job_num`, `time`, `latitude`, `longitude`) VALUES
(9, 166, '05:31:08', -36.83875459071301, 174.6171940635319),
(10, 183, '12:24:15', -36.83877017778187, 174.61710268274632),
(11, 218, '05:10:56', -36.732712, 174.7011463),
(12, 220, '11:45:48', -36.8383102, 174.617185),
(15, 221, '11:54:30', -36.8387295, 174.6170011),
(16, 222, '11:56:25', -36.8383414, 174.6172121),
(18, 224, NULL, -36.8387295, 174.6170011),
(19, 227, NULL, NULL, NULL),
(20, 229, '09:46:40', -36.7506839, 174.7015516),
(21, 230, NULL, NULL, NULL),
(22, 231, NULL, NULL, NULL),
(24, 244, '03:37:17', -36.73366154, 174.700531),
(25, 247, NULL, NULL, NULL),
(26, 249, NULL, NULL, NULL),
(27, 250, NULL, NULL, NULL),
(28, 251, NULL, NULL, NULL),
(29, 252, '09:28:12', -36.7325247, 174.7010616),
(31, 253, NULL, NULL, NULL),
(32, 254, NULL, NULL, NULL),
(33, 255, '12:27:30', -36.7498122, 174.697999),
(35, 256, NULL, NULL, NULL),
(36, 257, '03:25:27', -36.7338865, 174.7014699),
(38, 261, NULL, NULL, NULL),
(39, 262, NULL, NULL, NULL),
(40, 263, NULL, NULL, NULL),
(41, 264, NULL, NULL, NULL),
(42, 265, NULL, NULL, NULL),
(43, 266, '08:54:11', -36.8380638, 174.6173791),
(46, 269, NULL, NULL, NULL),
(47, 270, NULL, NULL, NULL),
(48, 271, '11:27:30', -36.8371335, 174.6177615),
(49, 272, '11:42:28', -36.8387565, 174.6167191),
(57, 273, NULL, NULL, NULL),
(58, 274, NULL, NULL, NULL),
(59, 278, NULL, NULL, NULL),
(60, 279, NULL, NULL, NULL),
(61, 280, NULL, NULL, NULL),
(62, 281, NULL, NULL, NULL),
(63, 282, NULL, NULL, NULL),
(64, 283, NULL, NULL, NULL),
(65, 284, NULL, NULL, NULL),
(66, 285, NULL, NULL, NULL),
(67, 287, NULL, NULL, NULL),
(68, 288, NULL, NULL, NULL),
(69, 289, NULL, NULL, NULL),
(70, 290, NULL, NULL, NULL),
(71, 291, NULL, NULL, NULL),
(72, 292, NULL, NULL, NULL),
(73, 293, NULL, NULL, NULL),
(75, 294, '03:12:37', -36.7329446, 174.7010616),
(77, 295, '03:20:21', -36.7329211, 174.7012069),
(79, 296, NULL, NULL, NULL),
(80, 297, '06:27:39', -36.8380922, 174.6173147),
(83, 298, NULL, NULL, NULL),
(84, 299, NULL, NULL, NULL),
(85, 300, '08:10:55', -36.7296348, 174.7069005),
(86, 301, NULL, NULL, NULL),
(87, 302, NULL, NULL, NULL),
(88, 303, NULL, NULL, NULL),
(90, 305, NULL, NULL, NULL),
(91, 306, NULL, NULL, NULL),
(92, 307, NULL, NULL, NULL),
(94, 308, NULL, NULL, NULL),
(95, 309, NULL, NULL, NULL),
(96, 310, NULL, NULL, NULL),
(97, 311, NULL, NULL, NULL),
(98, 312, NULL, NULL, NULL),
(99, 313, NULL, NULL, NULL),
(100, 314, NULL, NULL, NULL),
(101, 315, NULL, NULL, NULL),
(102, 316, NULL, NULL, NULL),
(103, 317, NULL, NULL, NULL),
(104, 318, NULL, NULL, NULL),
(105, 319, NULL, NULL, NULL),
(106, 320, NULL, NULL, NULL),
(107, 321, NULL, NULL, NULL),
(108, 322, NULL, NULL, NULL),
(109, 323, NULL, NULL, NULL),
(110, 324, NULL, NULL, NULL),
(111, 325, NULL, NULL, NULL),
(113, 327, '11:12:03', -36.8387643, 174.6167917),
(186, 328, '11:34:28', -36.8387643, 174.6167919),
(402, 331, '11:49:17', -36.837372, 174.6176549),
(485, 335, '12:58:12', -36.7234569, 174.7048998),
(490, 338, NULL, NULL, NULL),
(491, 339, NULL, NULL, NULL),
(492, 340, '02:58:24', -36.8384348, 174.6170423),
(573, 341, '03:04:11', -36.8384149, 174.6170728),
(584, 342, '03:06:06', -36.8381183, 174.6172957),
(586, 343, NULL, NULL, NULL),
(587, 344, NULL, NULL, NULL),
(588, 345, NULL, NULL, NULL),
(589, 346, NULL, NULL, NULL),
(590, 347, NULL, NULL, NULL),
(591, 348, NULL, NULL, NULL),
(592, 349, NULL, NULL, NULL),
(593, 350, NULL, NULL, NULL),
(594, 351, '06:50:55', -36.8377024, 174.6175209),
(595, 352, NULL, NULL, NULL),
(597, 353, NULL, NULL, NULL),
(598, 354, '07:10:55', -36.6945618, 174.752489),
(600, 355, '07:18:11', -43.5003248, 172.5842908),
(602, 356, NULL, NULL, NULL),
(603, 357, NULL, NULL, NULL),
(604, 358, NULL, NULL, NULL),
(605, 359, NULL, NULL, NULL),
(677, 418, '05:32:24', -36.83871407682453, 174.61716681745904),
(679, 419, '05:28:46', -36.8377529, 174.617433),
(682, 420, NULL, NULL, NULL),
(683, 421, NULL, NULL, NULL),
(684, 422, NULL, NULL, NULL),
(685, 423, NULL, NULL, NULL),
(686, 424, NULL, NULL, NULL),
(687, 425, NULL, NULL, NULL),
(688, 426, NULL, NULL, NULL),
(689, 427, NULL, NULL, NULL),
(690, 428, NULL, NULL, NULL),
(691, 429, '09:17:02', -36.83878736878352, 174.61733027070872),
(693, 431, NULL, NULL, NULL),
(695, 432, '09:25:11', -36.7337771, 174.7008983),
(697, 433, NULL, NULL, NULL),
(698, 434, NULL, NULL, NULL),
(699, 435, NULL, NULL, NULL),
(701, 438, NULL, NULL, NULL),
(702, 439, NULL, NULL, NULL),
(703, 440, NULL, NULL, NULL),
(706, 442, NULL, NULL, NULL),
(707, 443, '10:11:56', -36.7337771, 174.7008983),
(709, 444, '10:12:47', -36.7337771, 174.7008983),
(711, 446, '10:37:01', -36.7337771, 174.7008983),
(714, 448, '11:58:33', -36.7337771, 174.7008983),
(716, 449, NULL, NULL, NULL),
(717, 450, '12:09:46', -36.7333427, 174.7005716),
(719, 451, NULL, NULL, NULL),
(720, 452, NULL, NULL, NULL),
(721, 453, NULL, NULL, NULL),
(722, 454, NULL, NULL, NULL),
(723, 455, NULL, NULL, NULL),
(724, 456, NULL, NULL, NULL),
(725, 457, NULL, NULL, NULL),
(726, 458, NULL, NULL, NULL),
(727, 459, NULL, NULL, NULL),
(728, 460, NULL, NULL, NULL),
(729, 461, NULL, NULL, NULL),
(730, 462, NULL, NULL, NULL),
(731, 463, NULL, NULL, NULL),
(732, 464, NULL, NULL, NULL),
(733, 465, NULL, NULL, NULL),
(734, 466, NULL, NULL, NULL),
(735, 467, NULL, NULL, NULL),
(736, 468, NULL, NULL, NULL),
(737, 469, NULL, NULL, NULL),
(738, 470, NULL, NULL, NULL),
(739, 471, NULL, NULL, NULL),
(740, 472, NULL, NULL, NULL),
(742, 474, '01:19:25', -36.7337771, 174.7008983),
(748, 478, NULL, NULL, NULL),
(749, 479, '02:09:18', -36.7337771, 174.7008983),
(755, 480, NULL, NULL, NULL),
(756, 481, '04:25:40', -36.7337771, 174.7008983),
(770, 483, '06:49:17', -36.8380977, 174.6170499),
(777, 484, NULL, NULL, NULL),
(778, 485, NULL, NULL, NULL),
(779, 486, NULL, NULL, NULL),
(780, 487, NULL, NULL, NULL),
(781, 488, NULL, NULL, NULL),
(782, 489, NULL, NULL, NULL),
(783, 490, '02:57:35', -36.7332805, 174.7010616),
(786, 494, NULL, NULL, NULL),
(787, 495, NULL, NULL, NULL),
(788, 496, NULL, NULL, NULL),
(789, 497, '03:29:25', -36.7271962, 174.7049406),
(792, 498, NULL, NULL, NULL),
(793, 499, '03:34:48', -43.5337965, 172.6475001),
(795, 500, NULL, NULL, NULL),
(796, 501, '03:41:58', -36.7337844, 174.7010616),
(798, 502, NULL, NULL, NULL),
(799, 503, NULL, NULL, NULL),
(800, 504, '03:51:40', -36.7337844, 174.7010616),
(804, 505, '05:42:16', -36.6944813, 174.75244012),
(806, 507, '09:16:29', -36.7323969, 174.7010208),
(807, 508, NULL, NULL, NULL),
(822, 509, '09:40:17', -36.7324407, 174.7010616),
(824, 510, '09:43:41', -36.7323969, 174.7010208),
(827, 511, '11:36:13', -36.8382557, 174.6173832),
(831, 512, '11:39:41', -36.8386146, 174.6172916),
(833, 513, NULL, NULL, NULL),
(834, 514, '01:13:04', -36.8381572, 174.6169989),
(836, 515, NULL, NULL, NULL),
(837, 516, '08:24:45', -43.5002921, 172.5842948),
(838, 517, '07:51:13', -36.7337844, 174.7010616),
(839, 518, '08:20:01', -36.7323495, 174.7008983),
(841, 519, NULL, NULL, NULL),
(843, 520, NULL, NULL, NULL),
(844, 521, '09:49:36', -36.749772, 174.6980398),
(846, 522, '10:16:32', -36.749772, 174.6980398),
(849, 523, '12:52:43', -36.7333098, 174.7007758),
(851, 524, NULL, NULL, NULL),
(852, 525, '05:56:46', -36.7333958, 174.7029468),
(854, 526, NULL, NULL, NULL),
(855, 527, '04:41:50', -36.8381819, 174.616924),
(857, 528, NULL, NULL, NULL),
(858, 529, '04:53:35', -36.8351709, 174.6199495),
(860, 530, '05:09:16', -36.8348207, 174.6196224),
(863, 532, NULL, NULL, NULL),
(865, 533, NULL, NULL, NULL),
(866, 534, NULL, NULL, NULL),
(867, 535, NULL, NULL, NULL),
(868, 536, NULL, NULL, NULL),
(869, 537, NULL, NULL, NULL),
(870, 538, '11:03:08', -36.83689298, 174.6038682),
(872, 539, '11:04:07', -36.83710808, 174.60386155);

-- --------------------------------------------------------

--
-- Table structure for table `sec`
--

CREATE TABLE `sec` (
  `MEGATRON` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sec`
--

INSERT INTO `sec` (`MEGATRON`) VALUES
('b5a15c2a6bf6a815e426e97cdac732261840e16e92c46f80a3077f7f236cf148');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(255) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(200) NOT NULL,
  `manager` tinyint(1) NOT NULL,
  `onjob` tinyint(1) NOT NULL DEFAULT '0',
  `mobile` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `rego` varchar(10) DEFAULT NULL,
  `token` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `firstname`, `lastname`, `username`, `password`, `manager`, `onjob`, `mobile`, `phone`, `email`, `rego`, `token`) VALUES
(1, 'Bilal', 'Jumaah', 'buzz', 'd74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1', 1, 0, '0212466928', 'N/A', 'bilal.j@me.com', 'N/A', NULL),
(6, 'Ashneel', 'Kumar', 'ash', 'd74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1', 1, 0, '02102546984', NULL, 'ashneelak@hotmail.com', NULL, 'fLxHsQRrDJE:APA91bEcXy2zNrPMmGoViImksd8te4sJTYh8ut_UTnBs8tjWqu6uPT55YTAudNHEs5I1BAHq-1cic66wJbmfJCyk'),
(19, 'Mornez', 'Green', 'mornez', 'd74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1', 1, 0, '', '', 'mornez.green@electrix.co.nz', '', NULL),
(20, 'dhevandree', 'Pillay', 'keaten', 'd74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1', 0, 0, '021983298', NULL, NULL, NULL, NULL),
(21, 'Roger', 'Williams', 'Roger', 'd74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1', 0, 0, '021560465', NULL, NULL, NULL, NULL),
(23, 'Graeme', 'Johnson', 'otdrmn', 'd74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1', 0, 0, NULL, NULL, NULL, NULL, NULL),
(24, 'Toby', 'Garner', 'Toby', 'd74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1', 0, 0, '021000', '0941321', 'tob@bob.com', 'x1z1', NULL),
(25, 'Test', 'Dummy', 'test', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08', 0, 0, '021', '09', 'tes@test.co.nz', '', NULL),
(27, 'aa', 'bb', 'x', 'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 1, 0, '', '', '', '', NULL),
(28, 'aaa', 'aa', 'aaa', 'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 1, 0, '', '', '', '', NULL),
(29, 'aa', 'add', 's', 'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 1, 0, 'a', 'a', 'a', 'a', NULL),
(30, 'Andrew', 'Garner', 'Andy', 'd74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1', 0, 0, '021984981', '039812398', 'Andy@hotmail.com', 'NG55TH', NULL),
(31, 'B1lal', 'Testin', 'theking', 'd74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1', 1, 0, '', '', 'buzzjumah@gmail.com', '', NULL),
(32, 'Indy', 'Singh', 'indy', 'd74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1', 0, 0, NULL, NULL, NULL, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `jobs`
--
ALTER TABLE `jobs`
  ADD PRIMARY KEY (`job_num`);

--
-- Indexes for table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `job_num` (`job_num`);

--
-- Indexes for table `sec`
--
ALTER TABLE `sec`
  ADD PRIMARY KEY (`MEGATRON`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `jobs`
--
ALTER TABLE `jobs`
  MODIFY `job_num` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=540;
--
-- AUTO_INCREMENT for table `location`
--
ALTER TABLE `location`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=874;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `location`
--
ALTER TABLE `location`
  ADD CONSTRAINT `location_ibfk_1` FOREIGN KEY (`job_num`) REFERENCES `jobs` (`job_num`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
