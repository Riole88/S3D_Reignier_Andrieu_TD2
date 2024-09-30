import java.util.Map;
import java.util.HashMap;
import java.util.*;
import Exception.*;


public class Etudiant {
    private final Identite identite;
    private final Formation formation;
    private final List<Resultat> resultat;

    public Etudiant(Identite identite, Formation formation) {
        this.identite = identite;
        this.formation = formation;
        this.resultat = new ArrayList<Resultat>();
    }

    public void ajouterNote(String matiere, double note) throws ExceptionMatiereInexistante, ExceptionNoteInvalide {
        if (note < 0 || note > 20) {
            throw new ExceptionNoteInvalide("La note doit être entre 0 et 20");
        }

        if (!formation.getMatiere().containsKey(matiere)) {
            throw new ExceptionMatiereInexistante("Matière inexistante");
        }

        boolean resultatExistant = false;
        for (Resultat r : resultat) {
            if (r.getIdMatiere().equals(matiere)) {
                r.ajoutNote(note);
                resultatExistant = true;
                break;
            }
        }

        if (!resultatExistant) {
            Resultat nouveauRes = new Resultat(matiere);
            nouveauRes.ajoutNote(note);
            resultat.add(nouveauRes);
        }
    }

    public double calculMoyenne(String matiere) throws ExceptionMatiereInexistante  {
        if (!formation.getMatiere().containsKey(matiere)) {
            throw new ExceptionMatiereInexistante("Matière inexistante.");
        }

        for (Resultat r : resultat) {
            if (r.getIdMatiere().equals(matiere)) {
                List<Double> notes = r.getNoteMatiere();
                if (!notes.isEmpty()) {
                    double somme = 0.0;
                    for (double note : notes) {
                        somme += note;
                    }
                    return somme / notes.size();
                }
            }
        }

        throw new ExceptionMatiereInexistante("Aucune note pour cette matière.");
    }

    public double calculMoyenneG() throws ExceptionMatiereInexistante, ExceptionCoefInvalide {
        double sommeNotes = 0.0;
        double sommeCoeff = 0.0;

        for (Resultat res : resultat) {
            String matiere = res.getIdMatiere();

            if (!formation.getMatiere().containsKey(matiere)) {
                throw new ExceptionMatiereInexistante("Matière inexistante : " + matiere);
            }

            double coef = formation.getCoef(matiere);

            // Vérifie si le coefficient est valide
            if (coef <= 0) {
                throw new ExceptionCoefInvalide("Coefficient invalide pour la matière : " + matiere);
            }

            List<Double> notes = res.getNoteMatiere();
            if (!notes.isEmpty()) {
                double sommeMatiere = 0.0;
                for (double note : notes) {
                    sommeMatiere += note;
                }
                double moyenneMatiere = sommeMatiere / notes.size();
                sommeNotes += moyenneMatiere * coef;
                sommeCoeff += coef;
            }
        }

        if (sommeCoeff == 0) {
            throw new ExceptionCoefInvalide("Aucun coefficient valide trouvé pour calculer la moyenne générale.");
        }

        return sommeNotes / sommeCoeff;
    }

    public List<Resultat> getResultat() {
        return resultat;
    }

    public Formation getFormation() {
        return formation;
    }
}
