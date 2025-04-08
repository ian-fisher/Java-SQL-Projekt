import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Einkauf {
    MySQLConnector datenbank;
    ProduktListe produktListe;
    private List<int[]> Produkte = new ArrayList<int[]>();

    Einkauf(MySQLConnector datenbank, ProduktListe produktListe) {
        this.datenbank = datenbank;
        this.produktListe = produktListe;
    }

    public void addProdukt(int i, int Menge) {
        for (int j = 0; j < Produkte.size(); j++) {
            if (Produkte.get(j)[0] == i) {
                Produkte.get(j)[1] += Menge;
                return;
            }
        }
        Produkte.add(new int[]{i, Menge});
    }
    public String BelegErstellen() {
        String beleg = "";
        float sum = 0;
        for (int i = 0; i < Produkte.size(); i++) {
            beleg += produktListe.ProdukteNamen[Produkte.get(i)[0]] + " ";
            beleg += "x" + Produkte.get(i)[1] + "\n";
            beleg += produktListe.ProduktePreisen[Produkte.get(i)[0]] * Produkte.get(i)[1] + "€" + "\n";
            sum += produktListe.ProduktePreisen[Produkte.get(i)[0]] * Produkte.get(i)[1];
        }
        for (int i = 0; i < 18 - Produkte.size() * 2; i++) {
            beleg += "\n";
        }
        beleg += "Summe: " + sum + "€";
        return beleg;
    }
    public void EinkaufBeenden() throws SQLException {
        if (Produkte.size() < 1)
            return;
        String abfrage = "SELECT EinkaufNr FROM Einkauf ORDER BY EinkaufNr DESC LIMIT 1";
        ResultSet ergebnis = datenbank.executeQuery(abfrage);
        int EinkaufNr = 0;
        while (ergebnis.next()) {
            EinkaufNr = ergebnis.getInt("EinkaufNr") + 1;
        }
        System.out.println(EinkaufNr);
        abfrage = "INSERT INTO `Einkauf` (`EinkaufNr`, `Uhrzeit`, `Jahr`, `Monat`, `Tag`, `RabattProzent`, `KassenNr`, `MitarbeiterNr`, `KontoNr`, `EC_ZahlungNr`) VALUES ('" + EinkaufNr + "', '00:00:00', NULL, NULL, NULL, '0.00', NULL, NULL, NULL, NULL);";
        try {
            datenbank.executeUpdate(abfrage);
        }  catch (SQLException e) {
            System.out.printf("FEHLER: %s%n%n%n", e.getMessage());
        }
        for (int[] ints : Produkte) {
            abfrage = "INSERT INTO `enthaelt` (`EinkaufNr`, `ProduktNr`, `Menge`, `Steuer`) VALUES ('" + EinkaufNr + "', '" + ints[0] + "', '" + ints[1] + "', '0')";
            try {
                datenbank.executeUpdate(abfrage);
            } catch (SQLException e) {
                System.out.printf("FEHLER: %s%n%n%n", e.getMessage());
            }
        }
    }
}
