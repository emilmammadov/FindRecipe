-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 07 Kas 2019, 20:42:49
-- Sunucu sürümü: 10.4.6-MariaDB
-- PHP Sürümü: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `recipedb`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `favtbl`
--

CREATE TABLE `favtbl` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `recipe_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `recipetbl`
--

CREATE TABLE `recipetbl` (
  `id` int(11) NOT NULL,
  `sharer_id` int(11) NOT NULL,
  `title` text COLLATE utf8_bin NOT NULL,
  `ingredient_list` text COLLATE utf8_bin NOT NULL,
  `body` text COLLATE utf8_bin NOT NULL,
  `person_count` int(5) NOT NULL,
  `prep_time_sec` int(5) NOT NULL,
  `cook_time_sec` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Tablo döküm verisi `recipetbl`
--

INSERT INTO `recipetbl` (`id`, `sharer_id`, `title`, `ingredient_list`, `body`, `person_count`, `prep_time_sec`, `cook_time_sec`) VALUES
(1, 1, 'Çikolata Parçacıklı Kurabiye', '8 yemek kaşığı tereyağı(oda sıcaklığında)\r\n1,5 çay bardağı toz şeker\r\n3/4 çay bardağı pekmez\r\n1 adet yumurta\r\n1 yemek kaşığı nişasta\r\n1 çay kaşığı kabartma tozu\r\n1 paket vanilin\r\n1/2 çay kaşığı tuz\r\n2 çay bardağı bitter damla çikolata\r\n1 çay bardağı sütlü çikolata\r\n250 gram un\r\n5 yemek kaşığı bitter çikolata(kıyılmış)', 'Tereyağını oda sıcaklığında yumuşatın ve sırası ile toz şeker, pekmez, yumurta ekleyip krema kıvamına gelene kadar çırpın.\r\nTüm kuru malzemeleri eleyerek karışıma ekleyin ve karıştırın. Karışım homojen bir hal alınca karıştırmayı durdurun. Karışıma damla çikolataları ve kıyılmış bitter çikolatayı ekleyin.\r\nKarışımı üzeri kapalı bir şekilde buzdolabında en az 1 saat dilerseniz 2 güne kadar bekletebilirsiniz.\r\nKarışım sertleştikten sonra içinden büyük parçalar koparın ve yağlı kağıt serilmiş bir tepsiye aralarında fazlaca boşluk bırakarak en fazla dört adet yerleştirin.\r\n170 derecelik fırında altları kızarana kadar 13-16 dakika pişirin. Fırından çıkan kurabiyeler yumuşak olduklarından en az 5 dakika oda sıcaklığında dinlendirin. Afiyet olsun.', 8, 900, 900),
(2, 1, 'Kıymalı Rulo Patates', '2 yemek kaşığı zeytinyağı\r\n1 adet soğan\r\n1 adet çarliston biber\r\n200 gram kıyma\r\n1 çay kaşığı karabiber\r\n1 tatlı kaşığı tuz\r\n2 çay kaşığı pul biber\r\n1 yemek kaşığı domates salçası\r\n2/3 su bardağı su\r\n150 gram mozzarella peyniri', 'Yağlı kağıdı yağlamak patatesin rulo şeklini alabilmesi için ve kolayca kağıttan çıkması için gereklidir.\r\nFansız programda pişirmek daha puf dokulu bir patates taban elde etmenize yardımcı olur.\r\nPatatesleri haşlayın ve kabuklarını soyun.\r\nHaşlanmış patatesleri bir çatal yardımıyla iyice ezin ve püre kıvamına getirin.\r\nYumurtaları ezdiğiniz patateslerin üzerine ekleyin ve güzelce karıştırın.\r\nTuz, karabiber, toz kırmızı biber ve kimyonu ilave ederek karışımı spatula yardımıyla karıştırın.\r\nKıvamına da kontrol ederek bir yemek kaşığı un ekleyin ve patatesli harca yedirin. Fırını 180 dereceye ayarlayın ve ısınmasını bekleyin.\r\nFırın tepsisine yağlı kağıt serin ve yağlı kağıdı da zeytinyağıyla yağlayın.\r\nPatatesli harcı tepsiye yağlı kağıdın üzerini kaplayacak şekilde yayın.\r\n20-25 dakika kadar üstü hafif kızarana kadar pişirin.', 5, 700, 1000),
(3, 1, 'Fırında Kızartma Tarifi', '2 yemek kaşığı zeytinyağı\r\n1 adet soğan\r\n1 adet çarliston biber\r\n200 gram kıyma\r\n1 çay kaşığı karabiber\r\n1 tatlı kaşığı tuz\r\n2 çay kaşığı pul biber\r\n1 yemek kaşığı domates salçası\r\n2/3 su bardağı su\r\n150 gram mozzarella peyniri', 'Patlıcanları alacalı bir şekilde soyun, iri küpler halinde doğrayıp tuzlu suyun içerisine koyun.Patates, soğan ve havuçların da kabuklarını soyun. Kabağı kabuklu bir şekilde bırakın. Ardından tüm sebzeleri küpler halinde doğrayın. Biberlerin de ortasındaki tohumlarını çıkararak doğrayın.\r\nBir kasenin içerisine sarımsakları rendeleyin. Üzerine zeytinyağı ve taze biberiyeyi ilave edin. Güzelce karıştırın.\r\nAcılığı çıkan patlıcanların fazla suyunu süzün. Tüm sebzeleri bir kabın içerisine alın. Üzerine tuz, toz kırmızı biber ve karabiber serpin. Zeytinyağlı karışımı da ilave edip güzelce harmanlayın.\r\nSebzelerinizi pişirme kağıdı serili bir fırın tepsinin üzerine aktarın. Aralıklı bir şekilde yayın. 190 derecede önceden ısıtılmış fırına verin. Fırında tüm sebzeler çıtır çıtır olana kadar yaklaşık 25-30 dakika kadar pişirin.', 4, 900, 600);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `tagrelationtbl`
--

CREATE TABLE `tagrelationtbl` (
  `id` int(11) NOT NULL,
  `recipe_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Tablo döküm verisi `tagrelationtbl`
--

INSERT INTO `tagrelationtbl` (`id`, `recipe_id`, `tag_id`) VALUES
(2, 2, 10),
(5, 3, 10),
(10, 1, 5),
(11, 1, 7),
(13, 2, 7),
(15, 3, 5),
(16, 3, 7),
(17, 1, 2),
(19, 1, 10);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `tagtbl`
--

CREATE TABLE `tagtbl` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Tablo döküm verisi `tagtbl`
--

INSERT INTO `tagtbl` (`id`, `name`) VALUES
(1, 'et'),
(2, 'makarna'),
(5, 'yumurta'),
(6, 'mercimek'),
(7, 'ekmek'),
(8, 'tuz'),
(9, 'mantar'),
(10, 'biber');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `usertbl`
--

CREATE TABLE `usertbl` (
  `id` int(11) NOT NULL,
  `email` varchar(255) COLLATE utf8_bin NOT NULL,
  `username` varchar(255) COLLATE utf8_bin NOT NULL,
  `password` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Tablo döküm verisi `usertbl`
--

INSERT INTO `usertbl` (`id`, `email`, `username`, `password`) VALUES
(1, 'admin@gmail.com', 'admin', 'admin'),
(2, '', 'emil', 'emil'),
(5, '', 'den', 'den');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `favtbl`
--
ALTER TABLE `favtbl`
  ADD PRIMARY KEY (`id`),
  ADD KEY `favtbl_recipe_id` (`recipe_id`),
  ADD KEY `favtbl_user_id` (`user_id`);

--
-- Tablo için indeksler `recipetbl`
--
ALTER TABLE `recipetbl`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `tagrelationtbl`
--
ALTER TABLE `tagrelationtbl`
  ADD PRIMARY KEY (`id`),
  ADD KEY `tagrelationtbl_tag_id` (`tag_id`),
  ADD KEY `tagrelationtbl_recipe_id` (`recipe_id`);

--
-- Tablo için indeksler `tagtbl`
--
ALTER TABLE `tagtbl`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `usertbl`
--
ALTER TABLE `usertbl`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `favtbl`
--
ALTER TABLE `favtbl`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Tablo için AUTO_INCREMENT değeri `recipetbl`
--
ALTER TABLE `recipetbl`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Tablo için AUTO_INCREMENT değeri `tagrelationtbl`
--
ALTER TABLE `tagrelationtbl`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Tablo için AUTO_INCREMENT değeri `tagtbl`
--
ALTER TABLE `tagtbl`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Tablo için AUTO_INCREMENT değeri `usertbl`
--
ALTER TABLE `usertbl`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `favtbl`
--
ALTER TABLE `favtbl`
  ADD CONSTRAINT `favtbl_recipe_id` FOREIGN KEY (`recipe_id`) REFERENCES `recipetbl` (`id`),
  ADD CONSTRAINT `favtbl_user_id` FOREIGN KEY (`user_id`) REFERENCES `usertbl` (`id`);

--
-- Tablo kısıtlamaları `tagrelationtbl`
--
ALTER TABLE `tagrelationtbl`
  ADD CONSTRAINT `tagrelationtbl_recipe_id` FOREIGN KEY (`recipe_id`) REFERENCES `recipetbl` (`id`),
  ADD CONSTRAINT `tagrelationtbl_tag_id` FOREIGN KEY (`tag_id`) REFERENCES `tagtbl` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
