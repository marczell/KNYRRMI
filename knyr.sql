-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2017. Már 15. 16:57
-- Kiszolgáló verziója: 10.1.19-MariaDB
-- PHP verzió: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `knyr`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `cpvkodok`
--

CREATE TABLE `cpvkodok` (
  `cpvid` int(11) NOT NULL,
  `cpvkod` int(15) NOT NULL,
  `lathato` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- A tábla adatainak kiíratása `cpvkodok`
--

INSERT INTO `cpvkodok` (`cpvid`, `cpvkod`, `lathato`) VALUES
(1, 11111111, 0),
(2, 22222, 0),
(3, 1234567891, 1),
(4, 111, 1);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `kozbeszerzesieljarasfajtai`
--

CREATE TABLE `kozbeszerzesieljarasfajtai` (
  `kejid` int(11) NOT NULL,
  `kozbeszerzesieljarasfajtai` char(100) COLLATE utf8_hungarian_ci NOT NULL,
  `lathato` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `kozbeszerzesieljarasfajtai`
--

INSERT INTO `kozbeszerzesieljarasfajtai` (`kejid`, `kozbeszerzesieljarasfajtai`, `lathato`) VALUES
(2, 'Nyílt eljárás', 0),
(3, 'Meghívásos', 0);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `projektek`
--

CREATE TABLE `projektek` (
  `projektid` int(11) NOT NULL,
  `projekt` char(150) COLLATE utf8_hungarian_ci NOT NULL,
  `lathato` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `projektek`
--

INSERT INTO `projektek` (`projektid`, `projekt`, `lathato`) VALUES
(2, 'IT beszerzés', 0),
(3, 'Irodaszer', 0);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `szerzodes`
--

CREATE TABLE `szerzodes` (
  `sorszam` int(11) NOT NULL COMMENT 'Beszerzés sorszáma',
  `besznev` varchar(250) CHARACTER SET latin2 COLLATE latin2_hungarian_ci NOT NULL COMMENT 'beszerzés neve',
  `keljarasazon` varchar(50) CHARACTER SET latin2 COLLATE latin2_hungarian_ci NOT NULL COMMENT 'eljárás azonosítója',
  `bertek` int(15) NOT NULL COMMENT 'becsült érték',
  `kozbeszerzesieljarasfajta` int(11) NOT NULL,
  `szerzodesfajtaja` int(11) NOT NULL,
  `cpvkod` int(11) NOT NULL,
  `projekt` int(11) NOT NULL,
  `kozbeszkezdete` date NOT NULL COMMENT 'Közbeszerzési eljárás kezdete',
  `kozbeszvege` date NOT NULL COMMENT 'Közbeszerzési eljárás vége',
  `szerzazon` varchar(25) COLLATE utf8_hungarian_ci NOT NULL,
  `szerzodofel` int(11) NOT NULL,
  `szerzodeserteke` int(11) NOT NULL,
  `szerztargy` varchar(250) CHARACTER SET latin2 COLLATE latin2_hungarian_ci NOT NULL,
  `szerzodeskotesdatuma` date NOT NULL,
  `szerzodestervezettlezarasa` date NOT NULL,
  `szerzmodazon` varchar(25) CHARACTER SET latin2 COLLATE latin2_hungarian_ci NOT NULL,
  `szerzmodertek` int(25) NOT NULL,
  `szerzmodtargy` varchar(250) CHARACTER SET latin2 COLLATE latin2_hungarian_ci NOT NULL,
  `szerzmoddatum` date NOT NULL,
  `szerzmodvege` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `szerzodes`
--

INSERT INTO `szerzodes` (`sorszam`, `besznev`, `keljarasazon`, `bertek`, `kozbeszerzesieljarasfajta`, `szerzodesfajtaja`, `cpvkod`, `projekt`, `kozbeszkezdete`, `kozbeszvege`, `szerzazon`, `szerzodofel`, `szerzodeserteke`, `szerztargy`, `szerzodeskotesdatuma`, `szerzodestervezettlezarasa`, `szerzmodazon`, `szerzmodertek`, `szerzmodtargy`, `szerzmoddatum`, `szerzmodvege`) VALUES
(4, '', '', 0, 3, 2, 1, 2, '0000-00-00', '0000-00-00', '', 3, 345, '', '2016-04-04', '2016-04-09', 'ok', 0, '', '0000-00-00', '0000-00-00'),
(8, '', '', 0, 2, 2, 1, 2, '0000-00-00', '0000-00-00', '', 3, 155, '', '2016-04-06', '2016-04-09', 'ok', 0, '', '0000-00-00', '0000-00-00');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `szerzodesfajtai`
--

CREATE TABLE `szerzodesfajtai` (
  `szerzodesfajtaid` int(11) NOT NULL,
  `szerzodesfajta` char(150) COLLATE utf8_hungarian_ci NOT NULL,
  `lathato` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `szerzodesfajtai`
--

INSERT INTO `szerzodesfajtai` (`szerzodesfajtaid`, `szerzodesfajta`, `lathato`) VALUES
(2, 'Megbízási szerz?dés', 0);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `szerzodo_fel`
--

CREATE TABLE `szerzodo_fel` (
  `szfid` int(11) NOT NULL,
  `szerzodofel` char(100) COLLATE utf8_hungarian_ci NOT NULL,
  `szekhely-varos` char(50) COLLATE utf8_hungarian_ci NOT NULL,
  `szekhely-iranyitoszam` int(11) NOT NULL,
  `szekhely-kozterulet` char(100) COLLATE utf8_hungarian_ci NOT NULL,
  `szekhely-hazszam` int(11) NOT NULL,
  `telefonszam` char(25) COLLATE utf8_hungarian_ci NOT NULL,
  `faxszam` char(25) COLLATE utf8_hungarian_ci NOT NULL,
  `e-mail` char(50) COLLATE utf8_hungarian_ci NOT NULL,
  `cegjegyzekszam` char(15) COLLATE utf8_hungarian_ci NOT NULL,
  `adoszam` char(15) COLLATE utf8_hungarian_ci NOT NULL,
  `kapcsolattarto-neve` char(100) COLLATE utf8_hungarian_ci NOT NULL,
  `kapcsolattarto-tel` char(25) COLLATE utf8_hungarian_ci NOT NULL,
  `kapcsolattarto-email` char(50) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `szerzodo_fel`
--

INSERT INTO `szerzodo_fel` (`szfid`, `szerzodofel`, `szekhely-varos`, `szekhely-iranyitoszam`, `szekhely-kozterulet`, `szekhely-hazszam`, `telefonszam`, `faxszam`, `e-mail`, `cegjegyzekszam`, `adoszam`, `kapcsolattarto-neve`, `kapcsolattarto-tel`, `kapcsolattarto-email`) VALUES
(3, 'valaki', 'f', 1111, 'asdhas', 55, '1235678', '234567', 'gggg@hu.lr', '88-88-888888', '12345678-1-12', 'cxvy', '45', 'm@telenor.hu'),
(5, '??', '??', 4444, 'g', 4, '5', '1', 'v@bk.ji', '88-88-999999', '88888888-8-88', '??', '5', 'v@bk.ji');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `user`
--

CREATE TABLE `user` (
  `NEV` varchar(50) COLLATE utf8_hungarian_ci DEFAULT NULL,
  `JELSZO` varchar(50) COLLATE utf8_hungarian_ci DEFAULT NULL,
  `SZERKESZTHET` tinyint(4) NOT NULL,
  `ADMINE` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `user`
--

INSERT INTO `user` (`NEV`, `JELSZO`, `SZERKESZTHET`, `ADMINE`) VALUES
('admin', 'admin', 1, 1);

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `cpvkodok`
--
ALTER TABLE `cpvkodok`
  ADD PRIMARY KEY (`cpvid`);

--
-- A tábla indexei `kozbeszerzesieljarasfajtai`
--
ALTER TABLE `kozbeszerzesieljarasfajtai`
  ADD PRIMARY KEY (`kejid`);

--
-- A tábla indexei `projektek`
--
ALTER TABLE `projektek`
  ADD PRIMARY KEY (`projektid`);

--
-- A tábla indexei `szerzodes`
--
ALTER TABLE `szerzodes`
  ADD PRIMARY KEY (`sorszam`),
  ADD KEY `szerzodofel` (`szerzodofel`),
  ADD KEY `projekt` (`projekt`),
  ADD KEY `cpvkod` (`cpvkod`),
  ADD KEY `kozbeszerzésieljarasfajta` (`kozbeszerzesieljarasfajta`),
  ADD KEY `szerzodesfajtaja` (`szerzodesfajtaja`);

--
-- A tábla indexei `szerzodesfajtai`
--
ALTER TABLE `szerzodesfajtai`
  ADD PRIMARY KEY (`szerzodesfajtaid`);

--
-- A tábla indexei `szerzodo_fel`
--
ALTER TABLE `szerzodo_fel`
  ADD PRIMARY KEY (`szfid`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `cpvkodok`
--
ALTER TABLE `cpvkodok`
  MODIFY `cpvid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT a táblához `kozbeszerzesieljarasfajtai`
--
ALTER TABLE `kozbeszerzesieljarasfajtai`
  MODIFY `kejid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT a táblához `projektek`
--
ALTER TABLE `projektek`
  MODIFY `projektid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT a táblához `szerzodes`
--
ALTER TABLE `szerzodes`
  MODIFY `sorszam` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Beszerzés sorszáma', AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT a táblához `szerzodesfajtai`
--
ALTER TABLE `szerzodesfajtai`
  MODIFY `szerzodesfajtaid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT a táblához `szerzodo_fel`
--
ALTER TABLE `szerzodo_fel`
  MODIFY `szfid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `szerzodes`
--
ALTER TABLE `szerzodes`
  ADD CONSTRAINT `szerzodes_ibfk_1` FOREIGN KEY (`szerzodofel`) REFERENCES `szerzodo_fel` (`szfid`),
  ADD CONSTRAINT `szerzodes_ibfk_2` FOREIGN KEY (`projekt`) REFERENCES `projektek` (`projektid`),
  ADD CONSTRAINT `szerzodes_ibfk_3` FOREIGN KEY (`kozbeszerzesieljarasfajta`) REFERENCES `kozbeszerzesieljarasfajtai` (`kejid`),
  ADD CONSTRAINT `szerzodes_ibfk_4` FOREIGN KEY (`cpvkod`) REFERENCES `cpvkodok` (`cpvid`),
  ADD CONSTRAINT `szerzodes_ibfk_5` FOREIGN KEY (`szerzodesfajtaja`) REFERENCES `szerzodesfajtai` (`szerzodesfajtaid`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
