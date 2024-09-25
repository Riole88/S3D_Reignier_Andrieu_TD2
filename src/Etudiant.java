import java.util.Map;
import java.util.HashMap;
import java.util.*;

public class Etudiant {
    private final Identite identite;
    private final Formation formation;
    private final Map<String, List<Double>> resultat;

    public Etudiant(Identite identite, Formation formation) {
        this.identite = identite;
        this.formation = formation;
        this.resultat = new HashMap<>();
    }

    public void ajouterNote(String matiere, double note) {
        if (note >= 0 && note <= 20) {
            if (formation.getMatiere().containsKey(matiere)) {
                resultat.computeIfAbsent(matiere, k -> new ArrayList<>()).add(note);
            } else {
                System.out.println("Matière inexistante dans la formation.");
            }
        } else {
            System.out.println("La note doit être entre 0 et 20.");
        }
    }

    public double calculMoyenne(String matiere) {
        if (formation.getMatiere().containsKey(matiere) && resultat.containsKey(matiere)) {
            List<Double> notes = resultat.get(matiere);
            if (notes != null && !notes.isEmpty()) {
                double somme = 0.0;
                for (double note : notes) {
                    somme += note;
                }
                return somme / notes.size();
            } else {
                System.out.println("Pas de notes pour cette matière.");
                return 0.0;
            }
        } else {
            System.out.println("Matière inexistante ou note absente pour cette matière.");
            return 0.0;
        }
    }

    public double calculMoyenneG() {
        double sommeNotes = 0.0;
        double sommeCoeff = 0.0;

        for (String matiere : resultat.keySet()) {
            List<Double> notes = resultat.get(matiere);
            double coef = formation.getCoef(matiere);

            if (coef > 0 && notes != null && !notes.isEmpty()) {
                double sommeMatiere = 0.0;
                for (double note : notes) {
                    sommeMatiere += note;
                }
                double moyenneMatiere = sommeMatiere / notes.size();
                sommeNotes += moyenneMatiere * coef;
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

    public Map<String, List<Double>> getResultat() {
        return resultat;
    }

    public Formation getFormation() {
        return formation;
    }
}
