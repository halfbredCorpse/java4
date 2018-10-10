-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 10, 2018 at 06:40 AM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `server_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `remote_contacts`
--

CREATE TABLE `remote_contacts` (
  `User_Id` char(11) NOT NULL,
  `Emergency_Contact` char(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `remote_records`
--

CREATE TABLE `remote_records` (
  `User_Id` char(11) NOT NULL,
  `Date_Time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `Location` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `remote_users`
--

CREATE TABLE `remote_users` (
  `User_Id` char(11) NOT NULL,
  `Last_Name` varchar(255) NOT NULL,
  `First_Name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Stand-in structure for view `x`
-- (See below for the actual view)
--
CREATE TABLE `x` (
`User_Id` char(11)
,`Date_Time` timestamp
);

-- --------------------------------------------------------

--
-- Structure for view `x`
--
DROP TABLE IF EXISTS `x`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `x`  AS  select `remote_records`.`User_Id` AS `User_Id`,`remote_records`.`Date_Time` AS `Date_Time` from `remote_records` ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `remote_contacts`
--
ALTER TABLE `remote_contacts`
  ADD PRIMARY KEY (`User_Id`);

--
-- Indexes for table `remote_records`
--
ALTER TABLE `remote_records`
  ADD PRIMARY KEY (`User_Id`);

--
-- Indexes for table `remote_users`
--
ALTER TABLE `remote_users`
  ADD PRIMARY KEY (`User_Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
