import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import Exception.InsertionInvalidGroupeEtudiant;

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
