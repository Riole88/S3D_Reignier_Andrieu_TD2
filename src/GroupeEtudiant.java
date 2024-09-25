import java.util.ArrayList;
import java.util.List;

public class GroupeEtudiant {
    ArrayList<Etudiant> groupeE ;
    Formation formation;

    public GroupeEtudiant(Formation f1) {
        this.groupeE = new ArrayList<Etudiant>();
        this.formation=f1;
    }
    public void ajouterE(Etudiant e)  {
        if (e.getFormation().getIdt().equals(formation.getIdt()) ){
            groupeE.add(e);

        } else {
            System.out.println("l'étudiant n'est pas dans la formation");
        }


    }
    public void supprimerE(Etudiant e){
        groupeE.remove(e);
    }



    public List<Etudiant> getEtudiant(){
        return groupeE;
    }

    @Override
    public String toString() {
        return "GroupeEtudiant{" +
                "groupeE=" + groupeE +
                ", formation=" + formation +
                '}';
    }
}
