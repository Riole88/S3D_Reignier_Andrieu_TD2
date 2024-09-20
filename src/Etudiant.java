import java.util.Map;
import java.util.HashMap;

public class Etudiant {
    private final Identite identite;
    private final Formation formation;
    private final Map<String, Double> resultat;

    public Etudiant(Identite identite, Formation formation) {
        this.identite = identite;
        this.formation = formation;
        this.resultat = new HashMap<>();
    }

    public void ajouterNote(String matiere, double note) {
        if (note >= 0 && note <= 20) {
            if (formation.getMatiere().containsKey(matiere)) {
                resultat.put(matiere, note);
            } else {
                System.out.println("Matière inexistante dans la formation.");
            }
        } else {
            System.out.println("La note doit être entre 0 et 20.");
        }
    }

    public double calculMoyenne(String matiere) {
        if (formation.getMatiere().containsKey(matiere) && resultat.containsKey(matiere)) {
            return resultat.get(matiere);
        } else {
            System.out.println("Matière inexistante ou note absente pour cette matière.");
            return 0.0;
        }
    }

    public double calculMoyenneG() {
        double sommeNotes = 0.0;
        double sommeCoeff = 0.0;

        for (String matiere : resultat.keySet()) {
            double note = resultat.get(matiere);
            double coef = formation.getCoef(matiere);

            if (coef > 0) {
                sommeNotes += note * coef;
                sommeCoeff += coef;
            } else {
                System.out.println("Coefficient invalide ou matière absente dans la formation : " + matiere);
            }
        }

        if (sommeCoeff == 0) {
            System.out.println("Pas de coefficients valides pour calculer la moyenne générale.");
            return 0.0;
        } else {
            return sommeNotes / sommeCoeff;
        }
    }
}
