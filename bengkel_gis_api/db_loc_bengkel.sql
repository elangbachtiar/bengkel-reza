-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 18, 2020 at 08:19 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_loc_bengkel`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_bengkel`
--

CREATE TABLE `tbl_bengkel` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `telp` varchar(12) NOT NULL,
  `email` varchar(50) NOT NULL,
  `alamat` text NOT NULL,
  `hari` varchar(200) NOT NULL,
  `jam_buka` time NOT NULL,
  `jam_tutup` time NOT NULL,
  `gambar` varchar(200) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `rating` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_bengkel`
--

INSERT INTO `tbl_bengkel` (`id`, `nama`, `telp`, `email`, `alamat`, `hari`, `jam_buka`, `jam_tutup`, `gambar`, `latitude`, `longitude`, `rating`) VALUES
(1, 'Honda Motorcycle Repair Shop South Jakarta', '(021)7812522', '-', 'Jl. Margasatwa Raya No.30, RT.1/RW.7, Cilandak Tim., Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12560', 'Senin s/d Jumat', '08:00:00', '17:00:00', 'http://10.0.2.2/bengkel_gis_api/assets/image_bengkel/bengkel_1.jpeg', -6.208975, 106.843666, 4),
(2, 'Bengkel Pro Q', '(021)7883292', '-', 'Jl. Taman Margasatwa Raya No.15, RT.9/RW.5, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540', 'Senin s/d Jumat', '09:00:00', '21:00:00', 'http://10.0.2.2/bengkel_gis_api/assets/image_bengkel/bengkel_2.png', -6.285066, 106.825463, 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_bengkel`
--
ALTER TABLE `tbl_bengkel`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_bengkel`
--
ALTER TABLE `tbl_bengkel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
