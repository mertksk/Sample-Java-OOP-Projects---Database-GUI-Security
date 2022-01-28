-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 17 Oca 2021, 09:31:09
-- Sunucu sürümü: 10.4.17-MariaDB
-- PHP Sürümü: 7.4.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `hms`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `booking`
--

CREATE TABLE `booking` (
  `Customer_id` int(11) NOT NULL,
  `Booking_table` int(11) NOT NULL,
  `Booking_date` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `booking`
--

INSERT INTO `booking` (`Customer_id`, `Booking_table`, `Booking_date`) VALUES
(123, 1, '19/05/1954'),
(123, 2, '27/07/2017');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `customer`
--

CREATE TABLE `customer` (
  `ssn` int(11) NOT NULL,
  `name` text NOT NULL,
  `gender` varchar(1) NOT NULL,
  `DOB` text NOT NULL,
  `DateOfRegistration` text NOT NULL,
  `Payment_Type` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `customer`
--

INSERT INTO `customer` (`ssn`, `name`, `gender`, `DOB`, `DateOfRegistration`, `Payment_Type`) VALUES
(123, 'hg', 'F', '19/05/1998', '19/05/1998', 'das');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
