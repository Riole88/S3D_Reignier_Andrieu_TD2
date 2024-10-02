import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import Exception.InsertionInvalidGroupeEtudiant;
import Exception.ExceptionCoefInvalide;
import Exception.ExceptionNoteInvalide;
import Exception.ExceptionMatiereInexistante;

public class GroupeEtudiant {
    private ArrayList<Etudiant> groupeE;
    private Formation formation;

    public GroupeEtudiant(Formation formation) {
        this.groupeE = new ArrayList<>();
        this.formation = formation;
    }

    // Ajout d'un étudiant dans le groupe avec gestion de l'exception
    public void ajouterE(Etudiant e) throws InsertionInvalidGroupeEtudiant {
        if (e.getFormation().getIdt().equals(formation.getIdt())) {
            groupeE.add(e);
            System.out.println("Étudiant ajouté au groupe.");
        } else {
            throw new InsertionInvalidGroupeEtudiant("Erreur : L'étudiant n'est pas dans la formation " + formation.getIdt());
        }
    }

    // Suppression d'un étudiant du groupe
    public void supprimerE(Etudiant e) {
        if (groupeE.remove(e)) {
            System.out.println("Étudiant supprimé du groupe.");
        } else {
            System.out.println("Erreur : Étudiant non trouvé dans le groupe.");
        }
    }

    // Retourne la liste des étudiants du groupe
    public List<Etudiant> getEtudiant() {
        return groupeE;
    }

    // Méthode pour calculer la moyenne générale du groupe pour une matière
    public double calculerMoyenneMatiere(String matiere) throws ExceptionMatiereInexistante {
        if (groupeE.isEmpty()) {
            System.out.println("Le groupe est vide.");
            return 0;
        }

        double sommeNotes = 0;
        int nbEtudiantsAvecNote = 0;

        for (Etudiant etudiant : groupeE) {
            try {
                double moyenne = etudiant.calculMoyenne(matiere);
                sommeNotes += moyenne;
                nbEtudiantsAvecNote++;
            } catch (ExceptionMatiereInexistante e) {
                // Ignorer les étudiants qui n'ont pas cette matière
            }
        }

        if (nbEtudiantsAvecNote == 0) {
            throw new ExceptionMatiereInexistante("La matière " + matiere + " n'existe pas pour les étudiants du groupe.");
        }

        return sommeNotes / nbEtudiantsAvecNote;
    }

    // Méthode pour calculer la moyenne générale du groupe
    public double calculerMoyenneGroupe() throws ExceptionCoefInvalide, ExceptionMatiereInexistante {
        if (groupeE.isEmpty()) {
            System.out.println("Le groupe est vide.");
            return 0;
        }

        double sommeMoyennes = 0;
        for (Etudiant etudiant : groupeE) {
            sommeMoyennes += etudiant.calculMoyenneG();  // Calcul de la moyenne générale de l'étudiant
        }
        return sommeMoyennes / groupeE.size();
    }

    // Affichage du groupe et de sa formation
    @Override
    public String toString() {
        return "GroupeEtudiant{" +
                "groupeE=" + groupeE +
                ", formation=" + formation +
                '}';
    }

    public void triAlpha() {
        groupeE.sort(Comparator.comparing((Etudiant e) -> e.getIdentite().getNom()));
    }

    public void triAntiAlpha() {
        groupeE.sort(Comparator.comparing((Etudiant e) -> e.getIdentite().getNom()).reversed());
    }
}
