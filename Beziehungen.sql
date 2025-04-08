/*Creating Beziehungen*/


CREATE TABLE inventur (
  FilialeNr INT UNSIGNED, 
  ProduktNr INT UNSIGNED, 
  Menge INT UNSIGNED,
  PRIMARY KEY (FilialeNr, ProduktNr)
  );                                         
  
CREATE TABLE lieferung (
  LieferantNr INT UNSIGNED, 
  ProdunktNr INT UNSIGNED, 
  BestellungNr INT UNSIGNED,
  Anzahl INT UNSIGNED,
  Datum VARCHAR(30) NOT NULL,
  Status VARCHAR(30) NOT NULL,
  PRIMARY KEY (LieferantNr, ProdunktNr, BestellungNr)
  );
  
CREATE TABLE rabatt (
  ProduKtNr INT UNSIGNED, 
  FilialeNr INT UNSIGNED,
  RabattProzent DECIMAL(5,2) NOT NULL,
  PRIMARY KEY (ProduKtNr, FilialeNr)
  );
  
CREATE TABLE enthaelt (
  EinkaufNr INT UNSIGNED,
  ProduktNr INT UNSIGNED,
  Menge INT UNSIGNED,
  Steuer DECIMAL(5,2) NOT NULL,
  PRIMARY KEY (EinkaufNr, ProduktNr)
  );
                
