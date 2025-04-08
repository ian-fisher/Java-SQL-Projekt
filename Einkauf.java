import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Einkauf {
    MySQLConnector datenbank;
    private List<int[]> Produkte = new ArrayList<int[]>();
    private List<String> ProdukteNamen = new ArrayList<>();

    Einkauf(MySQLConnector datenbank) {
        this.datenbank = datenbank;
    }

    public void addProdukt(String name, int Id, int Anzahl) {
        for (int i = 0; i < Produkte.size(); i++) {
            if (Produkte.get(i)[0] == Id) {
                Produkte.get(i)[1] += Anzahl;
                return;
            }
        }
        Produkte.add(new int[]{Id, Anzahl});
        ProdukteNamen.add(name);
    }
    public String BelegErstellen() {
        String beleg = "";
        for (int i = 0; i < Produkte.size(); i++) {
            beleg += ProdukteNamen.get(i) + " ";
            beleg += "x" + Produkte.get(i)[1] + "\n";
        }
        return beleg;
    }
    public void EinkaufBeenden() throws SQLException {
        String abfrage = "SELECT EinkaufNr FROM Einkauf ORDER BY EinkaufNr DESC LIMIT 1";
        ResultSet ergebnis = datenbank.executeQuery(abfrage);
        int EinkaufNr = 0;
        while (ergebnis.next()) {
            EinkaufNr = ergebnis.getInt("EinkaufNr") + 1;
        }
        abfrage = "INSERT INTO Einkauf (EinkaufNr, Uhrzeit, Datum, RabattProzent, KassenNr, MitarbeiterNr, KontoNr, EC_ZahlungNr) VALUES ('" + EinkaufNr + "', '1', '1', '1', '3', '1', '1', '1'";
        datenbank.executeQuery(abfrage);
    }
}
