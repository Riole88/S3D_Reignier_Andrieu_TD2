import org.junit.jupiter.api.*;

import java.util.Map;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import Exception.*;

public class testEtudiant {

    Etudiant e1;
    Etudiant e2;
    Identite i1;
    Formation f1;

    @BeforeEach
    public void setUp(){
        //Création de la méthode setUp pour ne pas répéter du code dans les tests
        i1 = new Identite("0123", "Leroy", "Jenkins");
        f1 = new Formation("Informatique");
        f1.ajouteMatiere("Management SI", 2);
        f1.ajouteMatiere("DevWeb", 1);
        f1.ajouteMatiere("Anglais", -1);
        e1 = new Etudiant(i1, f1);
    }

    @Test
    public void test_constructeur(){
        e2 = new Etudiant(i1, f1);
        assertNotNull(e2,"Vérifie si l'Etudiant e2 n'est pas null");
    }

    @Test
    public void test_ajouterNote_normal() throws ExceptionMatiereInexistante, ExceptionNoteInvalide{
        ArrayList<Double> res = new ArrayList<>();
        res.add(15.5);
        e1.ajouterNote("DevWeb", 15.5);
        List<Resultat> resultats = e1.getResultat();
        assertEquals(res,resultats.get(0).getNoteMatiere(),"Le résultat obtenu doit être 15.5");
    }

    @Test
    public void test_ajouterNote_noteSup(){
        //On regarde si on obtient une exception en ajoutant une note supérieur à 20
        //Cela doit renvoyer true si on obtient une exception
        assertThrows(ExceptionNoteInvalide.class, () ->{
            e1.ajouterNote("DevWeb", 21.5);
        });
    }

    @Test
    public void test_ajouterNote_noteInf(){
        //On regarde si on obtient une exception en ajoutant une note négatif
        //Cela doit renvoyer true si on obtient une exception
        assertThrows(ExceptionNoteInvalide.class, () ->{
            e1.ajouterNote("DevWeb", -1.5);
        });
    }

    @Test
    public void test_ajouterNote_Matiere_Inexistante(){
        //On regarde si on obtient une exception en ajoutant une note supérieur à 20
        //Cela doit renvoyer true si on obtient une exception
        assertThrows(ExceptionMatiereInexistante.class, () ->{
            e1.ajouterNote("Communication", 12.5);
        });
    }

    @Test
    public void test_calculMoyenne_normal() throws ExceptionMatiereInexistante, ExceptionNoteInvalide{
        e1.ajouterNote("DevWeb", 12);
        e1.ajouterNote("DevWeb", 8);
        assertEquals(10, e1.calculMoyenne("DevWeb"),"La moyenne doit être égal à 10");
    }

    @Test
    public void test_calculMoyenne_aucune_matiere(){
        //On regarde si on obtient une exception en calculant la moyenne d'un matiere qui n'a aucune note
        //Cela doit renvoyer true si on obtient une exception
        assertThrows(ExceptionMatiereInexistante.class, () ->{
            e1.calculMoyenne("Communication");
        });
    }

    @Test
    public void test_MoyenneG_normal()throws ExceptionMatiereInexistante, ExceptionCoefInvalide, ExceptionNoteInvalide{
        e1.ajouterNote("DevWeb", 9);
        e1.ajouterNote("Management SI", 12);
        double res = e1.calculMoyenneG();
        assertEquals(11,res,"La moyenne doit être à 11");
    }

    @Test
    public void test_calculMoyenneG_coefNegatif(){
        assertThrows(ExceptionCoefInvalide.class, () ->{
            e1.ajouterNote("DevWeb", 9);
            e1.ajouterNote("Anglais", 20);
            double res = e1.calculMoyenneG();
        });
    }
}
