import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CreateReceiptPanel extends JPanel {
    private JComboBox<String> branchComboBox;
    private JButton createReceiptButton;
    private String currentUser;
    private Einkauf einkauf;

    public CreateReceiptPanel(String currentUser) {
        this.currentUser = currentUser;
        setLayout(null);
        einkauf = new Einkauf();
        JLabel SelectProduct = new JLabel("Produkt auswaehlen:");
        SelectProduct.setBounds(50, 50, 150, 20);
        add(SelectProduct);

        String[] Produkte = {"ADOLF", "HITLER", "nigga1", "nigga2", "Jaewon"};
        JComboBox ProduktListe = new JComboBox(Produkte);
        ProduktListe.setBounds(200, 50, 100, 20);
        add(ProduktListe);

        JLabel oder = new JLabel("oder");
        oder.setBounds(150, 75, 100, 20);
        add(oder);

        JLabel IdAngeben = new JLabel("Produkt ID angeben:");
        IdAngeben.setBounds(50, 100, 150, 20);
        add(IdAngeben);

        JTextField ProduktId = new JTextField();
        ProduktId.setEditable(true);
        ProduktId.setBounds(200, 100, 100, 20);
        add(ProduktId);

        JLabel AnzahlLabel = new JLabel("Anzahl:");
        AnzahlLabel.setBounds(50, 150, 100, 20);
        add(AnzahlLabel);

        JTextField Anzahl = new JTextField("0");
        Anzahl.setEditable(true);
        Anzahl.setBounds(150, 150, 40, 20);
        add(Anzahl);

        JButton AnzahlErhoehen = new JButton("+");
        AnzahlErhoehen.setBounds(200, 150, 20, 20);
        add(AnzahlErhoehen);
        AnzahlErhoehen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Anzahl.getText().matches("-?\\d+(\\.\\d+)?")) {
                    int anzahl = Integer.parseInt(Anzahl.getText());
                    anzahl++;
                    Anzahl.setText(String.valueOf(anzahl));
                }
            }
        });
        JButton AnzahlVerringern = new JButton("-");
        AnzahlVerringern.setBounds(220, 150, 20, 20);
        add(AnzahlVerringern);
        AnzahlVerringern.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Anzahl.getText().matches("-?\\d+(\\.\\d+)?")) {
                    int anzahl = Integer.parseInt(Anzahl.getText());
                    anzahl--;
                    if (anzahl < 0) {
                        anzahl = 0;
                    }
                    Anzahl.setText(String.valueOf(anzahl));
                }
            }
        });

        JTextArea beleg = new JTextArea("");
        beleg.setEditable(false);
        beleg.setBounds(400, 50, 200, 200);
        add(beleg);

        JButton AddToReceiptButton = new JButton("Add To Receipt");
        AddToReceiptButton.setBounds(150, 200, 100, 20);
        add(AddToReceiptButton);
        AddToReceiptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Anzahl.getText().matches("-?\\d+(\\.\\d+)?")) {
                    einkauf.addProdukt("aboba", 52, Integer.parseInt(Anzahl.getText()));
                    beleg.setText(einkauf.BelegErstellen());
                } else {
                    System.out.println("Anzahl muss eine Nummer sein");
                }
            }
        });
    }
}
