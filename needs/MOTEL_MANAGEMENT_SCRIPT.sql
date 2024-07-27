-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th7 27, 2024 lúc 05:40 AM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `motel_management`
--
CREATE DATABASE IF NOT EXISTS `motel_management` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `motel_management`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `userId` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `username` varchar(10) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`userId`, `name`, `username`, `password`) VALUES
('admin000', 'Root', 'root', '$2a$10$j2WJzi7wT1BazuUsnKa8vOmbanLacmKc042wJLWWUxMK5eM3zPXjS');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `checkout`
--

DROP TABLE IF EXISTS `checkout`;
CREATE TABLE `checkout` (
  `checkOutId` varchar(20) NOT NULL,
  `contractId` varchar(20) NOT NULL,
  `checkOutDate` date DEFAULT curdate(),
  `reason` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `contract`
--

DROP TABLE IF EXISTS `contract`;
CREATE TABLE `contract` (
  `contractId` varchar(20) NOT NULL,
  `identifier` varchar(20) NOT NULL,
  `roomId` varchar(20) NOT NULL,
  `quantity` int(11) DEFAULT 0,
  `roomDeposit` int(11) DEFAULT 0,
  `isFamily` tinyint(1) DEFAULT 0,
  `startingDate` date DEFAULT curdate(),
  `endingDate` date DEFAULT curdate(),
  `isRegisteredPerAddress` tinyint(1) DEFAULT 0,
  `checkedOut` tinyint(1) DEFAULT 0,
  `creatingTime` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `contract`
--

INSERT INTO `contract` (`contractId`, `identifier`, `roomId`, `quantity`, `roomDeposit`, `isFamily`, `startingDate`, `endingDate`, `isRegisteredPerAddress`, `checkedOut`, `creatingTime`) VALUES
('CON001', '345678345612', 'P001', 1, 1000, 0, '2023-01-01', '2023-12-31', 1, 0, '2024-05-30 07:10:59'),
('CON002', '789438345612', 'P002', 2, 2000, 1, '2023-02-01', '2023-12-31', 1, 0, '2024-05-30 07:10:59'),
('CON003', '385473543601', 'P003', 1, 1500, 0, '2023-03-01', '2023-12-31', 0, 0, '2024-05-30 07:10:59'),
('CON004', '512128518257', 'P004', 3, 3000, 1, '2023-04-01', '2023-12-31', 1, 0, '2024-05-30 07:10:59'),
('CON005', '385473234412', 'P005', 1, 1200, 0, '2023-05-01', '2023-12-31', 0, 0, '2024-05-30 07:10:59');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `electricrange`
--

DROP TABLE IF EXISTS `electricrange`;
CREATE TABLE `electricrange` (
  `rangeId` varchar(20) NOT NULL,
  `rangeName` varchar(20) NOT NULL,
  `minRangeValue` int(11) DEFAULT 0,
  `maxRangeValue` int(11) DEFAULT 0,
  `price` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `electricrange`
--

INSERT INTO `electricrange` (`rangeId`, `rangeName`, `minRangeValue`, `maxRangeValue`, `price`) VALUES
('E12412904244', 'RangeC', 201, 300, 3000),
('E12485028391', 'RangeA', 0, 100, 1000),
('E2027963334', 'RangeE', 401, 2147483647, 5000),
('E59290352903', 'RangeB', 101, 200, 2000),
('E63434634638', 'RangeD', 301, 400, 4000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `invoice`
--

DROP TABLE IF EXISTS `invoice`;
CREATE TABLE `invoice` (
  `invoiceId` varchar(20) NOT NULL,
  `roomId` varchar(20) NOT NULL,
  `priceRaisedDate` date NOT NULL,
  `dateCreated` date DEFAULT curdate(),
  `paymentYear` int(11) NOT NULL,
  `paymentMonth` int(11) NOT NULL,
  `formerElectricNumber` int(11) DEFAULT 0,
  `newElectricNumber` int(11) DEFAULT 0,
  `formerWaterNumber` int(11) DEFAULT 0,
  `newWaterNumber` int(11) DEFAULT 0,
  `electricPrice` int(11) DEFAULT 0,
  `waterPrice` int(11) DEFAULT 0,
  `garbage` int(11) DEFAULT 0,
  `wifi` int(11) DEFAULT 0,
  `vehicle` int(11) DEFAULT 0,
  `wasPaid` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `invoice`
--

INSERT INTO `invoice` (`invoiceId`, `roomId`, `priceRaisedDate`, `dateCreated`, `paymentYear`, `paymentMonth`, `formerElectricNumber`, `newElectricNumber`, `formerWaterNumber`, `newWaterNumber`, `electricPrice`, `waterPrice`, `garbage`, `wifi`, `vehicle`, `wasPaid`) VALUES
('INV001', 'P001', '2023-01-01', '2023-01-20', 2023, 1, 100, 150, 50, 100, 100000, 50000, 300, 200, 100, 0),
('INV002', 'P002', '2023-01-01', '2023-02-20', 2023, 2, 200, 300, 100, 110, 300000, 15000, 400, 300, 200, 1),
('INV003', 'P003', '2023-01-01', '2023-03-20', 2023, 3, 300, 310, 150, 160, 40000, 20000, 500, 400, 300, 0),
('INV004', 'P004', '2023-01-01', '2023-04-20', 2023, 4, 200, 210, 100, 110, 30000, 15000, 600, 500, 400, 1),
('INV005', 'P005', '2023-01-01', '2023-05-20', 2023, 5, 200, 210, 100, 110, 30000, 15000, 700, 600, 500, 0),
('INV011', 'P001', '2023-01-01', '2023-02-01', 2023, 2, 150, 160, 50, 60, 20000, 10000, 300, 200, 100, 0),
('INV012', 'P001', '2023-01-01', '2023-03-01', 2023, 3, 160, 170, 60, 70, 20000, 10000, 300, 200, 100, 1),
('INV013', 'P001', '2023-01-01', '2023-04-01', 2023, 4, 170, 180, 70, 80, 20000, 10000, 300, 200, 100, 1),
('INV014', 'P001', '2023-01-01', '2023-05-01', 2023, 5, 180, 190, 80, 90, 20000, 10000, 300, 200, 100, 1),
('INV015', 'P001', '2023-06-01', '2023-06-01', 2023, 6, 190, 200, 90, 100, 20000, 10000, 300, 200, 100, 0),
('INV016', 'P001', '2023-06-01', '2023-07-01', 2023, 7, 200, 210, 100, 110, 30000, 15000, 300, 200, 100, 0),
('INV017', 'P001', '2023-06-01', '2023-08-01', 2023, 8, 210, 220, 110, 120, 30000, 15000, 300, 200, 100, 1),
('INV018', 'P001', '2023-06-01', '2023-09-01', 2023, 9, 220, 230, 120, 130, 30000, 15000, 300, 200, 100, 0),
('INV019', 'P001', '2023-06-01', '2023-10-01', 2023, 10, 230, 240, 130, 140, 30000, 15000, 300, 200, 100, 1),
('INV020', 'P001', '2023-06-01', '2023-11-01', 2023, 11, 240, 250, 140, 150, 30000, 15000, 300, 200, 100, 0),
('INV021', 'P001', '2023-12-01', '2023-12-01', 2023, 12, 250, 270, 150, 170, 60000, 30000, 300, 200, 100, 1),
('INV022', 'P001', '2023-12-01', '2024-01-01', 2024, 1, 270, 290, 170, 190, 60000, 30000, 300, 200, 100, 0),
('INV023', 'P001', '2023-12-01', '2024-02-01', 2024, 2, 290, 300, 190, 200, 30000, 15000, 300, 200, 100, 1),
('INV024', 'P001', '2023-12-01', '2024-03-01', 2024, 3, 300, 310, 200, 210, 40000, 30000, 300, 200, 100, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `person`
--

DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `identifier` varchar(20) NOT NULL,
  `lastName` varchar(70) NOT NULL,
  `firstName` varchar(30) NOT NULL,
  `birthday` date NOT NULL,
  `phone` varchar(15) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `jobTitle` varchar(70) NOT NULL,
  `permanentAddress` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `bankAccountNumber` varchar(255) DEFAULT NULL,
  `bank` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `person`
--

INSERT INTO `person` (`identifier`, `lastName`, `firstName`, `birthday`, `phone`, `gender`, `jobTitle`, `permanentAddress`, `email`, `bankAccountNumber`, `bank`) VALUES
('345678345612', 'Nguyen', 'An', '1990-01-01', '0901234567', 'M', 'Engineer', '123 Street A', 'an.nguyen@example.com', '1234567890', 'Bank A'),
('385473234412', 'Ho', 'E', '1995-05-05', '0905234567', 'M', 'Scientist', '567 Street E', 'e.ho@example.com', '5234567890', 'Bank E'),
('385473543601', 'Tran', 'Chau', '1992-03-03', '0903234567', 'F', 'Doctor', '345 Street C', 'chau.tran@example.com', '3234567890', 'Bank C'),
('512128518257', 'Pham', 'Dung', '1988-04-04', '0904234567', 'F', 'Nurse', '456 Street D', 'dung.pham@example.com', '4234567890', 'Bank D'),
('789438345612', 'Le', 'Binh', '1985-02-02', '0902234567', 'M', 'Teacher', '234 Street B', 'binh.le@example.com', '2234567890', 'Bank B');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `persontemphistory`
--

DROP TABLE IF EXISTS `persontemphistory`;
CREATE TABLE `persontemphistory` (
  `identifier` varchar(20) NOT NULL,
  `lastName` varchar(70) NOT NULL,
  `firstName` varchar(30) NOT NULL,
  `birthday` date NOT NULL,
  `phone` varchar(15) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `jobTitle` varchar(70) NOT NULL,
  `permanentAddress` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `bankAccountNumber` varchar(255) DEFAULT NULL,
  `bank` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `persontemphistory`
--

INSERT INTO `persontemphistory` (`identifier`, `lastName`, `firstName`, `birthday`, `phone`, `gender`, `jobTitle`, `permanentAddress`, `email`, `bankAccountNumber`, `bank`) VALUES
('345678345612', 'Nguyen', 'An', '1990-01-01', '0901234567', 'M', 'Engineer', '123 Street A', 'an.nguyen@example.com', '1234567890', 'Bank A'),
('385473234412', 'Ho', 'E', '1995-05-05', '0905234567', 'M', 'Scientist', '567 Street E', 'e.ho@example.com', '5234567890', 'Bank E'),
('385473543601', 'Tran', 'Chau', '1992-03-03', '0903234567', 'F', 'Doctor', '345 Street C', 'chau.tran@example.com', '3234567890', 'Bank C'),
('512128518257', 'Pham', 'Dung', '1988-04-04', '0904234567', 'F', 'Nurse', '456 Street D', 'dung.pham@example.com', '4234567890', 'Bank D'),
('789438345612', 'Le', 'Binh', '1985-02-02', '0902234567', 'M', 'Teacher', '234 Street B', 'binh.le@example.com', '2234567890', 'Bank B');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `region`
--

DROP TABLE IF EXISTS `region`;
CREATE TABLE `region` (
  `regionId` varchar(20) NOT NULL,
  `region` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `region`
--

INSERT INTO `region` (`regionId`, `region`) VALUES
('R2032879587', 'Tp.Ho Chi Minh');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `room`
--

DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `roomId` varchar(20) NOT NULL,
  `quantity` int(11) DEFAULT 0 CHECK (`quantity` <= `maxQuantity`),
  `maxQuantity` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `room`
--

INSERT INTO `room` (`roomId`, `quantity`, `maxQuantity`) VALUES
('P001', 1, 3),
('P002', 2, 4),
('P003', 1, 2),
('P004', 3, 5),
('P005', 1, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `roompricehistory`
--

DROP TABLE IF EXISTS `roompricehistory`;
CREATE TABLE `roompricehistory` (
  `roomId` varchar(20) NOT NULL,
  `priceRaisedDate` date NOT NULL DEFAULT curdate(),
  `roomPrice` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `roompricehistory`
--

INSERT INTO `roompricehistory` (`roomId`, `priceRaisedDate`, `roomPrice`) VALUES
('P001', '2023-01-01', 10000),
('P001', '2023-06-01', 15000),
('P001', '2023-12-01', 16000),
('P001', '2024-01-01', 17000),
('P002', '2023-01-01', 20000),
('P003', '2023-01-01', 15000),
('P004', '2023-01-01', 25000),
('P005', '2023-01-01', 18000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `waterrange`
--

DROP TABLE IF EXISTS `waterrange`;
CREATE TABLE `waterrange` (
  `rangeId` varchar(20) NOT NULL,
  `rangeName` varchar(20) NOT NULL,
  `minRangeValue` int(11) DEFAULT 0,
  `maxRangeValue` int(11) DEFAULT 0,
  `price` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `waterrange`
--

INSERT INTO `waterrange` (`rangeId`, `rangeName`, `minRangeValue`, `maxRangeValue`, `price`) VALUES
('W23095290359', 'RangeE', 200, 250, 3000),
('W28935825324', 'RangeD', 150, 200, 2000),
('W29342903493', 'RangeB', 50, 100, 1000),
('W34906234492', 'RangeA', 0, 50, 500),
('W5028006877', 'RangeF', 250, 2147483647, 5000),
('W84395423858', 'RangeC', 100, 150, 1500);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`userId`),
  ADD UNIQUE KEY `userId` (`userId`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Chỉ mục cho bảng `checkout`
--
ALTER TABLE `checkout`
  ADD PRIMARY KEY (`checkOutId`),
  ADD UNIQUE KEY `checkOutId` (`checkOutId`),
  ADD KEY `FK_CheckOut_contractId` (`contractId`);

--
-- Chỉ mục cho bảng `contract`
--
ALTER TABLE `contract`
  ADD PRIMARY KEY (`contractId`),
  ADD UNIQUE KEY `contractId` (`contractId`),
  ADD UNIQUE KEY `UK_Contract` (`identifier`,`roomId`,`startingDate`),
  ADD KEY `FK_Contract_roomId` (`roomId`);

--
-- Chỉ mục cho bảng `electricrange`
--
ALTER TABLE `electricrange`
  ADD PRIMARY KEY (`rangeId`),
  ADD UNIQUE KEY `rangeId` (`rangeId`),
  ADD UNIQUE KEY `rangeName` (`rangeName`);

--
-- Chỉ mục cho bảng `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`invoiceId`),
  ADD UNIQUE KEY `invoiceId` (`invoiceId`),
  ADD UNIQUE KEY `UK_Invoice` (`roomId`,`paymentMonth`,`paymentYear`),
  ADD KEY `FK_RoomPriceHistory_priceRaisedDate` (`roomId`,`priceRaisedDate`);

--
-- Chỉ mục cho bảng `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`identifier`),
  ADD UNIQUE KEY `identifier` (`identifier`),
  ADD UNIQUE KEY `phone` (`phone`);

--
-- Chỉ mục cho bảng `persontemphistory`
--
ALTER TABLE `persontemphistory`
  ADD PRIMARY KEY (`identifier`),
  ADD UNIQUE KEY `identifier` (`identifier`),
  ADD UNIQUE KEY `phone` (`phone`);

--
-- Chỉ mục cho bảng `region`
--
ALTER TABLE `region`
  ADD PRIMARY KEY (`regionId`),
  ADD UNIQUE KEY `regionId` (`regionId`),
  ADD UNIQUE KEY `region` (`region`);

--
-- Chỉ mục cho bảng `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`roomId`),
  ADD UNIQUE KEY `roomId` (`roomId`);

--
-- Chỉ mục cho bảng `roompricehistory`
--
ALTER TABLE `roompricehistory`
  ADD PRIMARY KEY (`roomId`,`priceRaisedDate`);

--
-- Chỉ mục cho bảng `waterrange`
--
ALTER TABLE `waterrange`
  ADD PRIMARY KEY (`rangeId`),
  ADD UNIQUE KEY `rangeId` (`rangeId`),
  ADD UNIQUE KEY `rangeName` (`rangeName`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `checkout`
--
ALTER TABLE `checkout`
  ADD CONSTRAINT `FK_CheckOut_contractId` FOREIGN KEY (`contractId`) REFERENCES `contract` (`contractId`);

--
-- Các ràng buộc cho bảng `contract`
--
ALTER TABLE `contract`
  ADD CONSTRAINT `FK_Contract_identifier` FOREIGN KEY (`identifier`) REFERENCES `person` (`identifier`),
  ADD CONSTRAINT `FK_Contract_roomId` FOREIGN KEY (`roomId`) REFERENCES `room` (`roomId`);

--
-- Các ràng buộc cho bảng `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `FK_RoomPriceHistory_priceRaisedDate` FOREIGN KEY (`roomId`,`priceRaisedDate`) REFERENCES `roompricehistory` (`roomId`, `priceRaisedDate`);

--
-- Các ràng buộc cho bảng `roompricehistory`
--
ALTER TABLE `roompricehistory`
  ADD CONSTRAINT `FK_RaisingRoomPrice_roomId` FOREIGN KEY (`roomId`) REFERENCES `room` (`roomId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
