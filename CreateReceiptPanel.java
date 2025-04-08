import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CreateReceiptPanel extends JPanel {
    private String currentUser;
    private Einkauf einkauf;
    MySQLConnector datenbank;
    public CreateReceiptPanel(String currentUser, MySQLConnector datenbank) throws SQLException {
        this.currentUser = currentUser;
        this.datenbank = datenbank;
        ProduktListe liste = new ProduktListe(datenbank);
        setLayout(null);
        einkauf = new Einkauf(datenbank, liste);
        JLabel SelectProduct = new JLabel("Produkt auswaehlen:");
        SelectProduct.setBounds(50, 50, 150, 20);
        add(SelectProduct);

        String[] Produkte = liste.ProdukteNamen;
        JComboBox produktListe = new JComboBox(Produkte);
        produktListe.setBounds(200, 50, 175, 20);
        add(produktListe);

        JLabel oder = new JLabel("oder");
        oder.setBounds(150, 75, 100, 20);
        add(oder);

        JLabel IdAngeben = new JLabel("Produkt ID angeben:");
        IdAngeben.setBounds(50, 100, 150, 20);
        add(IdAngeben);

        JTextField ProduktId = new JTextField();
        ProduktId.setEditable(true);
        ProduktId.setBounds(200, 100, 175, 20);
        add(ProduktId);

        JLabel MengeLabel = new JLabel("Menge:");
        MengeLabel.setBounds(50, 150, 100, 20);
        add(MengeLabel);

        JTextField Menge = new JTextField("0");
        Menge.setEditable(true);
        Menge.setBounds(150, 150, 40, 20);
        add(Menge);

        JButton AnzahlErhoehen = new JButton("+");
        AnzahlErhoehen.setBounds(200, 150, 20, 20);
        add(AnzahlErhoehen);
        AnzahlErhoehen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Menge.getText().matches("-?\\d+(\\.\\d+)?")) {
                    int anzahl = Integer.parseInt(Menge.getText());
                    anzahl++;
                    Menge.setText(String.valueOf(anzahl));
                }
            }
        });

        JButton AnzahlVerringern = new JButton("-");
        AnzahlVerringern.setBounds(220, 150, 20, 20);
        add(AnzahlVerringern);
        AnzahlVerringern.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Menge.getText().matches("-?\\d+(\\.\\d+)?")) {
                    int anzahl = Integer.parseInt(Menge.getText());
                    anzahl--;
                    if (anzahl < 0) {
                        anzahl = 0;
                    }
                    Menge.setText(String.valueOf(anzahl));
                }
            }
        });

        JTextArea beleg = new JTextArea("");
        beleg.setEditable(false);
        beleg.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        beleg.setBounds(400, 50, 300, 307);
        beleg.setText(einkauf.BelegErstellen());
        add(beleg);

        JButton AddToReceiptButton = new JButton("Zum Einkauf hinzufuegen");
        AddToReceiptButton.setBounds(100, 200, 200, 20);
        add(AddToReceiptButton);
        AddToReceiptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Menge.getText().matches("-?\\d+(\\.\\d+)?")) {
                    if (Integer.parseInt(Menge.getText()) > 0) {
                        if (produktListe.getSelectedIndex() == 0) {
                            if (ProduktId.getText().matches("-?\\d+(\\.\\d+)?")) {
                                int id = Integer.parseInt(ProduktId.getText());
                                for (int i = 1; i < liste.ProdukteId.length; i++) {
                                    if (liste.ProdukteId[i] == id) {
                                        einkauf.addProdukt(i, Integer.parseInt(Menge.getText()));
                                        beleg.setText(einkauf.BelegErstellen());
                                    }
                                }
                            }
                        } else {
                            einkauf.addProdukt(produktListe.getSelectedIndex(), Integer.parseInt(Menge.getText()));
                            beleg.setText(einkauf.BelegErstellen());
                        }
                    }
                } else {
                    System.out.println("Anzahl muss eine Nummer sein");
                }
            }
        });

        JButton EinkaufBeenden = new JButton("Einkauf beenden");
        EinkaufBeenden.setBounds(125, 250, 150, 20);
        add(EinkaufBeenden);
        EinkaufBeenden.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    einkauf.EinkaufBeenden();
                    produktListe.setSelectedIndex(0);
                    Menge.setText("0");
                    ProduktId.setText("");
                    einkauf = new Einkauf(datenbank, liste);
                    beleg.setText(einkauf.BelegErstellen());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
