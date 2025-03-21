import java.sql.*;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

class SQL_Tester {
  
  /*Formatierung:
   *  Prozenten - "xxx.xx"
   *  Datum - "DDMMYYYY"
  */
  
  public static void main(String[] args) {
    // Ist aktuell für eine Verbindung zu dem verwendeten XAMPP-Server eingerichtet
    // --> für Zugriff auf eine andere Datenbank die Parameter entsprechend anpassen
    // --> die Parameter des MySQLConnector sind wie folgt strukturiert (benutzername, passwort, IP-Adresse des Datenbankservers, name der zu verwendenden Datenbank auf dem Server)
    MySQLConnector datenbank = new MySQLConnector("d0425602", "11-htz-lk-W", "w01ba120.kasserver.com", "d0425602");
    
    // In dem Datentyp "ResultSet" können die Ergebnisse einer Datenbankabfrage gespeichert werden.
    // Eine Dokumentation des Datentyps (Wie funktioniert er/Welche Methoden hat er) erhaltet ihr,
    // in dem ihr den Datentyp rechtsklickt und im aufklappenden Kontextmenü den Punkt "API-Hilfe" auswählt
    ResultSet ergebnis = null;
    
    try {
      datenbank.disconnect();
      System.out.printf("disconnected");
    } catch (SQLException e) {
      // Gibt eine Fehlermeldung aus und beendet die Anwendung, falls beim Verbinden mit der Datenbank ein Problem aufgetreten ist
      System.out.printf("FEHLER: %s%n", e.getMessage());
    } finally {
      
      try {
        // Verbindung zur Datenbank aufbauen
        // Tip: Der hier öfter verwendete Befehl "try" erlaubt es, im Programmablauf auftretende Fehler
        // abzufangen und auf sie zu reagieren, statt das Program bei einem Fehler direkt zu beenden
        datenbank.connect();
        
        try {
          // Falls schon eine Tabelle mit dem Namen "einkaufszettel" existiert, dann alte Tabelle löschen ...  
          String befehl = "DROP TABLE IF EXISTS LIKE '%42'";
          System.out.printf(">> %s%n", befehl);
          datenbank.executeUpdate(befehl);
          
          // ... neue Tabellen (Ohne Fremdschlüssel Attributen) erstellen
          befehl = "CREATE TABLE Produkt42 (ProduktNr INT UNSIGNED PRIMARY KEY, Name VARCHAR(30) NOT NULL, Preis DECIMAL(10,2) NOT NULL)";
          System.out.printf(">> %s%n", befehl);
          datenbank.executeUpdate(befehl);
          
          
          befehl = "CREATE TABLE Kategorie42 (ProduktNr INT UNSIGNED PRIMARY KEY, Name VARCHAR(30) NOT NULL, SteuerklasseProzent DECIMAL(5,2) NOT NULL)";
          System.out.printf(">> %s%n", befehl);
          datenbank.executeUpdate(befehl);
          
          
          befehl = "CREATE TABLE Filiale42 (FilialeNr INT UNSIGNED PRIMARY KEY, Name VARCHAR(30) NOT NULL, Strasse VARCHAR(30) NOT NULL, Nr INT UNSIGNED)";
          System.out.printf(">> %s%n", befehl);
          datenbank.executeUpdate(befehl);
          
          
          befehl = "CREATE TABLE Lieferant42 (LieferantNr INT UNSIGNED PRIMARY KEY, Name VARCHAR(30) NOT NULL, Strasse VARCHAR(30) NOT NULL, Nr INT UNSIGNED)"; //
          System.out.printf(">> %s%n", befehl);
          datenbank.executeUpdate(befehl);
          
          
          befehl = "CREATE TABLE Kasse42 (KasseNr VARCHAR(30) NOT NULL PRIMARY KEY)";
          System.out.printf(">> %s%n", befehl);
          datenbank.executeUpdate(befehl);
          
          
          befehl = "CREATE TABLE Mitarbeiter42 (MitarbeiterNr INT UNSIGNED PRIMARY KEY, Vorname VARCHAR(30) NOT NULL, Name VARCHAR(30) NOT NULL)";
          System.out.printf(">> %s%n", befehl);
          datenbank.executeUpdate(befehl);
          
          //rabatt mechanics unclear
          befehl = "CREATE TABLE Einkauf42 (EinkaufNr INT UNSIGNED PRIMARY KEY, Uhrzeit INT UNSIGNED, Datum INT UNSIGNED, RabattProzent DECIMAL(5,2) NOT NULL)";
          System.out.printf(">> %s%n", befehl);
          datenbank.executeUpdate(befehl);
          
          //KartenNr??
          befehl = "CREATE TABLE EC_Zahlung42 (EC_ZahlungNr INT UNSIGNED PRIMARY KEY, KartenNr INT UNSIGNED, IBAN INT UNSIGNED, Betrag INT UNSIGNED, InhaberVorname VARCHAR(30) NOT NULL, InhaberName VARCHAR(30) NOT NULL)";
          System.out.printf(">> %s%n", befehl);
          datenbank.executeUpdate(befehl);
          
          
          befehl = "CREATE TABLE Kundenkonto42 (KontoNr INT UNSIGNED PRIMARY KEY, Bonuspunkte INT UNSIGNED)";
          System.out.printf(">> %s%n", befehl);
          datenbank.executeUpdate(befehl);
          
          
          befehl = "CREATE TABLE Bestellung42 (BestellungNr INT UNSIGNED PRIMARY KEY, Datum INT UNSIGNED)";
          System.out.printf(">> %s%n", befehl);
          datenbank.executeUpdate(befehl);
          
          
          
          //BeziehungsTabellen erstellen
          
//          befehl = "CREATE TABLE inventur42 ( PRIMARY KEY)";
//          System.out.printf(">> %s%n", befehl);
//          datenbank.executeUpdate(befehl);
          
        } catch (SQLException e) {
          // Mögliche Fehler beim Ausführen des Befehls abfangen und Fehlermeldung ausgeben
          // ... das ist die Reaktion darauf, wenn bei dem Programmteil in einem "try" etwas schief geht
          System.out.printf("FEHLER: %s%n", e.getMessage());
        } 
        
        try {
          // Die gesamte aktive Tabelle wird zum überprüfen einmal ausgegeben
          String abfrage = "SELECT * FROM Produkt42";
          System.out.printf(">> %s%n", abfrage);
          ergebnis = datenbank.executeQuery(abfrage);
          
          // Zuerst Spaltenüberschriften ausgeben ... 
          for  (int i = 1; i<= ergebnis.getMetaData().getColumnCount(); i++){
            System.out.print(ergebnis.getMetaData().getColumnName(i) + "\t \t");
          }
          System.out.println();
          
          // ... und dann die Datensätze
          // Tip: der Befehl next() lässt sich auf Objekte vom Datentyp ResultSet anwenden (hier "ergebnis")
          //      und bewirkt, dass die nächste Zeile der Ergebnistabelle zur aktiven Zeile wird, deren
          //      Daten dann ausgelesen werden können. next() liefert außerdem eine Rückgabe vom Typ boolean.
          //      Diese ist "true", wenn es eine nächste Zeile gab, zu der gesprungen werden konnte und
          //      "false" falls es keine nächste Zeile im ResultSet mehr gab (Ende der Tabelle)
          while (ergebnis.next()) {
            for (int i = 1; i<=ergebnis.getMetaData().getColumnCount(); i++) {
              System.out.print(ergebnis.getString(i) + "\t \t");  
            }
            System.out.println();
          }
          
        } catch (SQLException e) {
          // Mögliche Fehler beim Ausführen der Abfrage abfangen und Fehlermeldung ausgeben
          System.out.printf("FEHLER: %s%n", e.getMessage());
        }  
        
        // Verbindung zur Datenbank trennen
        datenbank.disconnect();
        
      } catch (SQLException e) {
        // Gibt eine Fehlermeldung aus und beendet die Anwendung, falls beim Verbinden mit der Datenbank ein Problem aufgetreten ist
        System.out.printf("FEHLER: %s%n", e.getMessage());
        System.exit(1);
      }
    }         
  }                     
}
