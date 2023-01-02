
-- Dump dei dati della tabella contocorrente.anagrafica: ~3 rows (circa)
INSERT INTO `anagrafica` (`id`, `cf`, `nome`, `cognome`, `data_nascita`) VALUES
	(352, 'PCR', 'Pierpaolo', 'Pecoraio', '1998-06-25'),
	(402, 'CCZ', 'Daniele', 'Cocuzza', '1998-06-25'),
	(452, 'Gio', 'Giovanni', 'Falsaperla', '2000-06-25');

-- Dump dei dati della tabella contocorrente.contocorrente: ~2 rows (circa)
INSERT INTO `contocorrente` (`id`, `iban`, `abi`, `cab`, `numero_cc`, `data_apertura`, `data_chiusura`, `saldo`, `intestatario`, `cointestatario`) VALUES
	(105, 'IT90600', 'IT', '80', '600', '2022-12-28', NULL, 380, 402, NULL),
	(502, 'IT90700', 'IT', '80', '700', '2022-12-28', NULL, 400, 402, NULL);


-- Dump dei dati della tabella contocorrente.movimento: ~1 rows (circa)
INSERT INTO `movimento` (`id`, `descrizione`, `importo`, `data`, `id_conto`) VALUES
	(802, 'Prelievo', 20, '2022-12-28', 105);


/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
