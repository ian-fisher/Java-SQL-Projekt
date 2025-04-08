import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class MainWindow extends JFrame {
    private String currentUser; // the username of the logged-in worker
    MySQLConnector datenbank;
    public MainWindow(String currentUser, MySQLConnector datenbank) throws SQLException {
        this.currentUser = currentUser;
        this.datenbank = datenbank;
        setTitle("Grocery Store Management - Logged in as: " + currentUser);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Create tabs
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Einkauf erstellen", new CreateReceiptPanel(currentUser, datenbank));


        add(tabbedPane, BorderLayout.CENTER);
    }
}
