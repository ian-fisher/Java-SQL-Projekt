/*Fremdschlüsseln definiren*/

ALTER TABLE Lieferant
  ADD OrtNr INT UNSIGNED,
  ADD CONSTRAINT `FS_Lieferant_Ort` 
  FOREIGN KEY (OrtNr) 
  REFERENCES Ort(OrtNr) 
  ON DELETE CASCADE;
  
ALTER TABLE Filiale
  ADD OrtNr INT UNSIGNED,
  ADD CONSTRAINT `FS_Filiale_Ort` 
  FOREIGN KEY (OrtNr) 
  REFERENCES Ort(OrtNr) 
  ON DELETE CASCADE;
  
ALTER TABLE Produkt
  ADD KategorieName VARCHAR(30) NOT NULL,
  ADD CONSTRAINT `FS_Produkt_Kategorie` 
  FOREIGN KEY (KategorieName) 
  REFERENCES Kategorie(Name) 
  ON DELETE CASCADE;
  
ALTER TABLE Kasse
  ADD FilialeNr INT UNSIGNED,
  ADD CONSTRAINT `FS_Kasse_Filiale` 
  FOREIGN KEY (FilialeNr) 
  REFERENCES Filiale(FilialeNr) 
  ON DELETE CASCADE;
  
ALTER TABLE Mitarbeiter	
  ADD FilialeNr INT UNSIGNED,
  ADD CONSTRAINT `FS_Mitarbeiter_Filiale` 
  FOREIGN KEY (FilialeNr) 
  REFERENCES Filiale(FilialeNr) 
  ON DELETE CASCADE;
  
ALTER TABLE Bestellung
  ADD FilialeNr INT UNSIGNED,
  ADD CONSTRAINT `FS_Bestellung_Filiale` 
  FOREIGN KEY (FilialeNr) 
  REFERENCES Filiale(FilialeNr) 
  ON DELETE CASCADE;
  
ALTER TABLE Einkauf
  ADD KassenNr INT UNSIGNED,
  ADD CONSTRAINT `FS_Einkauf_Kasse` 
  FOREIGN KEY (KassenNr) 
  REFERENCES Kasse(KassenNr) 
  ON DELETE CASCADE;
  
ALTER TABLE Einkauf
  ADD MitarbeiterNr INT UNSIGNED,
  ADD CONSTRAINT `FS_Einkauf_Mitarbeiter` 
  FOREIGN KEY (MitarbeiterNr) 
  REFERENCES Mitarbeiter(MitarbeiterNr) 
  ON DELETE CASCADE;
  
ALTER TABLE Einkauf
  ADD KontoNr INT UNSIGNED,
  ADD CONSTRAINT `FS_Einkauf_Kundenkonto` 
  FOREIGN KEY (KontoNr) 
  REFERENCES Kundenkonto(KontoNr) 
  ON DELETE CASCADE;
  
ALTER TABLE Einkauf
  ADD EC_ZahlungNr INT UNSIGNED,
  ADD CONSTRAINT `FS_Einkauf_EC_Zahlung` 
  FOREIGN KEY (EC_ZahlungNr) 
  REFERENCES EC_Zahlung(EC_ZahlungNr) 
  ON DELETE CASCADE;
