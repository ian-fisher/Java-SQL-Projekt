import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    static MySQLConnector datenbank = new MySQLConnector("d0430a00", "11-htz-lk-JAYC", "w01ba120.kasserver.com", "d0430a00");
    public static void main(String[] args) throws SQLException {
        datenbank.connect();
        new MainWindow("dima", datenbank).setVisible(true);
    }
}
