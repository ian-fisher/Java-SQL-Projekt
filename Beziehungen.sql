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
  
CREATE TABLE rabatt (
  UNSIGNED PRIMARY KEY (ProduKtNr UNSIGNED INT, FilialeNr UNSIGNED INT),
  RabattProzent UNSIGNED FLOAT
  );
  
CREATE TABLE enthaelt (
  UNSIGNED PRIMARY KEY (EinkaufNr UNSIGNED INT, ProduktNr UNSIGNED INT),
  Menge UNSIGNED INT,
  Steuer UNSIGNED FLOAT
  );
                