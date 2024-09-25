import java.util.Collection;
import java.util.HashMap;

public class Formation {
    private final String idt;

    private final HashMap<String, Double> matiere;

    public Formation(String idFormation) {
        this.idt = idFormation;
        this.matiere = new HashMap<String, Double>();
    }

    public void ajouteMatiere(String mat, double coef) {
        this.matiere.put(mat, coef);
    }

    public void supMatiere(String mat, double coef) {
        if (this.matiere.containsKey(mat)) {
            this.matiere.remove(mat, coef);
        }
    }

    public double getCoef(String mat) {
        double res = -1;
        if (this.matiere.containsKey(mat)) {
            res = this.matiere.get(mat);
        }
        return res;
    }

    public HashMap<String, Double> getMatiere() {
        return matiere;
    }

    @Override
    public String toString() {
        return "Formation{" +
                "idt='" + idt + '\'' +
                ", matiere=" + matiere +
                '}';
    }

    public String getIdt() {
        return idt;
    }
}
