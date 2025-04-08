import java.util.*;

public class Einkauf {
    private List<int[]> Produkte = new ArrayList<int[]>();
    private List<String> ProdukteNamen = new ArrayList<>();
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
}
