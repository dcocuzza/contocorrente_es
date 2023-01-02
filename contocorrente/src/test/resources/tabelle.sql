-- Dump della struttura del database contocorrente
/*CREATE DATABASE IF NOT EXISTS `contocorrente` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
/*USE `contocorrente`;*/

-- Dump della struttura di tabella contocorrente.anagrafica
CREATE TABLE IF NOT EXISTS `anagrafica` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cf` varchar(50) NOT NULL DEFAULT '0',
  `nome` varchar(50) NOT NULL,
  `cognome` varchar(50) NOT NULL DEFAULT '',
  `data_nascita` date NOT NULL,
  PRIMARY KEY (`id`)
);

-- L’esportazione dei dati non era selezionata.

-- L’esportazione dei dati non era selezionata.

-- Dump della struttura di tabella contocorrente.contocorrente
CREATE TABLE IF NOT EXISTS `contocorrente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `iban` varchar(50) NOT NULL UNIQUE,
  `abi` varchar(50) NOT NULL,
  `cab` varchar(50) NOT NULL,
  `numero_cc` varchar(50) NOT NULL UNIQUE,
  `data_apertura` date NOT NULL,
  `data_chiusura` date DEFAULT NULL,
  `saldo` float NOT NULL DEFAULT 0,
  `intestatario` int NOT NULL,
  `cointestatario` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`intestatario`) references `anagrafica`(`id`),
  FOREIGN KEY (`cointestatario`) references `anagrafica`(`id`)
);

-- L’esportazione dei dati non era selezionata.

-- Dump della struttura di tabella contocorrente.movimento
CREATE TABLE IF NOT EXISTS `movimento` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descrizione` varchar(50) NOT NULL,
  `importo` float NOT NULL,
  `data` date NOT NULL,
  `id_conto` int NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_conto`) references `contocorrente`(`id`)
);


