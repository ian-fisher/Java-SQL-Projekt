import java.sql.*;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.nio.file.*;
import java.io.File;

class SQL_Tester {
  
  /*Formatierung:
   *  Prozenten - "xxx.xx"
   *  Datum - "DDMMYYYY"
  */
  
  public static void main(String[] args) {
    // Ist aktuell für eine Verbindung zu dem verwendeten XAMPP-Server eingerichtet
    // --> für Zugriff auf eine andere Datenbank die Parameter entsprechend anpassen
    // --> die Parameter des MySQLConnector sind wie folgt strukturiert (benutzername, passwort, IP-Adresse des Datenbankservers, name der zu verwendenden Datenbank auf dem Server)
    MySQLConnector datenbank = new MySQLConnector("d0430a00", "11-htz-lk-JAYC", "w01ba120.kasserver.com", "d0430a00");
    
    // In dem Datentyp "ResultSet" können die Ergebnisse einer Datenbankabfrage gespeichert werden.
    // Eine Dokumentation des Datentyps (Wie funktioniert er/Welche Methoden hat er) erhaltet ihr,
    // in dem ihr den Datentyp rechtsklickt und im aufklappenden Kontextmenü den Punkt "API-Hilfe" auswählt
    ResultSet ergebnis = null;
    
    try {
      datenbank.disconnect();
      System.out.printf("disconnected");
    } catch (SQLException e) {
      // Gibt eine Fehlermeldung aus und beendet die Anwendung, falls beim Verbinden mit der Datenbank ein Problem aufgetreten ist
      System.out.printf("FEHLER: %s%n%n%n", e.getMessage());
    } finally {
      
      try {
        // Verbindung zur Datenbank aufbauen
        // Tip: Der hier öfter verwendete Befehl "try" erlaubt es, im Programmablauf auftretende Fehler
        // abzufangen und auf sie zu reagieren, statt das Program bei einem Fehler direkt zu beenden
        datenbank.connect();
        
        String Constraints[] = new String[]{"FS_Lieferant_Ort", "FS_Filiale_Ort", "FS_Produkt_Kategorie", "FS_Kasse_Filiale", "FS_Mitarbeiter_Filiale", "FS_Bestellung_Filiale", "FS_Einkauf_Kasse", "FS_Einkauf_Mitarbeiter", "FS_Einkauf_Kundenkonto", "FS_Einkauf_EC_Zahlung"};
        for(String Constraintname : Constraints){   
          try {
            String befehl = "Alter table " +Constraintname.split("_")[1]+ " Drop constraint " + Constraintname;
            System.out.printf(">> %s%n", befehl);
            datenbank.executeUpdate(befehl);
          } catch(Exception e) {
            System.out.printf("FEHLER: %s%n", e.getMessage());
          } 
        }
        
        String Tables[] = new String[]{"Produkt", "Kategorie", "Filiale", "Lieferant", "Kasse", "Mitarbeiter", "Einkauf", "EC_Zahlung", "Kundenkonto", "Bestellung", "Ort", "inventur", "lieferung", "rabatt", "enthaelt"};
        for(String Tablename : Tables){   
          try {
            String befehl = "Drop table " + Tablename;
            System.out.printf(">> %s%n", befehl);
            datenbank.executeUpdate(befehl);
          } catch(Exception e) {
            System.out.printf("FEHLER: %s%n", e.getMessage());
          } 
        }
        
        String files[] = new String[]{"./Datendefinition.sql", "./Fremdschluesseln.sql", "./Beziehungen.sql", "./beispieldatensaetze.sql"};
        
        for(String file : files){
          try{
            String DDfile = new String(Files.readAllBytes( (new File(file)).toPath() ));
          
          
            for (String befehl : DDfile.split(";")) {
              try {
                System.out.printf(">> %s%n", befehl);
                datenbank.executeUpdate(befehl);
              } catch (Exception e) {
                // Mögliche Fehler beim Ausführen des Befehls abfangen und Fehlermeldung ausgeben
                // ... das ist die Reaktion darauf, wenn bei dem Programmteil in einem "try" etwas schief geht
                System.out.printf("FEHLER: %s%n", e.getMessage());
              } 
            } // end of for
            Thread.sleep(1);
          }catch(Exception e){
            System.out.printf("Fehler: %s%n", e.getMessage());
          }
        }
        /*try {
          // Die gesamte aktive Tabelle wird zum überprüfen einmal ausgegeben
          String abfrage = "SELECT * FROM Produkt";
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
        } */ 
        
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
