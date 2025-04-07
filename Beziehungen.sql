/*Creating Beziehungen*/


CREATE TABLE inventur (
  UNSIGNED PRIMARY KEY (FilialeNr INT, ProduktNr INT),
  Menge INT UNSIGNED
  );                                         
  
CREATE TABLE lieferung (
  UNSIGNED PRIMARY KEY (LieferantNr UNSIGNED INT, ProdunktNr UNSIGNED INT, BestellungNr UNSUGNED INT),
  Anzahl UNSIGNED INT,
  Datum TEXT,
  Status TEXT
  );
  
CREATE TABLE zahlen (
  UNSIGNED PRIMARY KEY(EinkaufNr UNDIGNED INT, EC-ZahlungsNr UNSIGNED INT)
  );
  
CREATE TABLE hat_kundenkonto (
  UNSIGNED PRIMARY KEY(EinkaufNr UNSIGNED INT, KundenkkontoNr UNSIGNED INT)
  );
  
CREATE TABLE gekauft (
  UNSIGNED PRIMARY KEY (KassenNr UNSIGNED INT, EinkaufNr UNSIGNED INT)
  );
  
CREATE TABLE bearbeitet (
  UNSIGNED PRIMARY KEY (EinkaufNr UNSIGNED INT, MitarbeiterNr UNSIGNED INT)
  );
  
CREATE TABLE rabatt (
  UNSIGNED PRIMARY KEY (ProduKtNr UNSIGNED INT, FilialeNr UNSIGNED INT),
  RabattProzent UNSIGNED FLOAT
  );
  
CREATE TABLE enthaelt (
  UNSIGNED PRIMARY KEY (EinkaufNr UNSIGNED INT, ProduktNr UNSIGNED INT),
  Menge UNSIGNED INT,
  Steuer UNSIGNED FLOAT
  );
  
CREATE TABLE arbeitet_in (
  UNSIGNED PRIMARY KEY (MitarbeiterNr UNSIGNED INT, FilialeNr UNSIGNED INT)
  );
  
                