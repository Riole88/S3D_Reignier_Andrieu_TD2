import java.util.ArrayList;
import java.util.List;

public class Resultat {
    private String idMatiere;
    private List<Double> noteMatiere;

    public Resultat(String nomMat){
        this.idMatiere = nomMat;
        this.noteMatiere = new ArrayList<Double>();
    }

    public void ajoutNote(double note) {
        this.noteMatiere.add(note);
    }

    public String getIdMatiere() {
        return idMatiere;
    }

    public List<Double> getNoteMatiere() {
        return noteMatiere;
    }
}
