import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ProduktListe {
    public String[] ProdukteNamen;
    public float[] ProduktePreisen;
    public int[] ProdukteId;
    public ProduktListe(MySQLConnector datenbank) throws SQLException {
        String abfrage = "SELECT COUNT(*) FROM Produkt";
        try {
            ResultSet ergebnis = datenbank.executeQuery(abfrage);
            ergebnis.next();
            int len = ergebnis.getInt(1);
            ProdukteNamen = new String[len + 1];
            ProduktePreisen = new float[len + 1];
            ProdukteId = new int[len + 1];
            abfrage = "SELECT * FROM Produkt;";
            ergebnis = datenbank.executeQuery(abfrage);
            ProdukteNamen[0] = "nicht ausgewaehlt";
            int i = 1;
            while (ergebnis.next()) {
                ProdukteNamen[i] = ergebnis.getString(2);
                ProduktePreisen[i] = ergebnis.getFloat(3);
                ProdukteId[i] = ergebnis.getInt(1);
                i++;
            }
        } catch (SQLException e) {
            System.out.printf("FEHLER: %s%n%n%n", e.getMessage());
        }
    }
}
