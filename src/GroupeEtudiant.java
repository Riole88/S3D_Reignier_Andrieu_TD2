import java.util.ArrayList;
public class GroupeEtudiant {
    ArrayList<Etudiant> groupeE ;
    Formation formation;

    public GroupeEtudiant(Formation f1) {
        this.groupeE = new ArrayList<Etudiant>();
        this.formation=f1;
    }
    public void ajouterE(Etudiant e){
        if (e.getFormation().getIdt().equals(formation.getIdt()) ){
            groupeE.add(e);

        }

    }
    public void supprimerE(Etudiant e){
        groupeE.remove(e);
    }

}
