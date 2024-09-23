import org.junit.jupiter.api.*;

import java.util.Map;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

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
        f1.ajouteMatiere("Communication", -1);
        e1 = new Etudiant(i1, f1);
    }

    @Test
    public void test_constructeur(){
        e2 = new Etudiant(i1, f1);
        assertNotNull(e2,"Vérifie si l'Etudiant e2 n'est pas null");
    }

    @Test
    public void test_ajouterNote_normal(){
        e1.ajouterNote("DevWeb", 12);
        Map<String, List<Double>> res = e1.getResultat();
        List<Double> notesDevWeb = res.get("DevWeb");
        assertEquals(12,notesDevWeb.get(0), "Le resultat doit être 12.0 car cela répresente la note de la matière ajouté");
    }

    @Test
    public void test_ajouterNote_noteSup(){
        e1.ajouterNote("DevWeb", 21);
        Map<String, List<Double>> res = e1.getResultat();
        List<Double> notesDevWeb = res.get("DevWeb");
        assertNull(notesDevWeb, "La matière ne doit pas contenir de note car la note était supérieur à 20");
    }

    @Test
    public void test_ajouterNote_noteInf(){
        e1.ajouterNote("DevWeb", -1);
        Map<String, List<Double>> res = e1.getResultat();
        List<Double> notesDevWeb = res.get("DevWeb");
        assertNull(notesDevWeb, "La matière ne doit pas contenir de note car la note était inférieur à 0");
    }

    @Test
    public void test_calculMoyenne_normal(){
        e1.ajouterNote("DevWeb", 11);
        e1.ajouterNote("DevWeb", 9);
        double res = e1.calculMoyenne("DevWeb");
        assertEquals(10,res,"La moyenne doit être à 10");
    }

    @Test
    public void test_calculMoyenne_aucune_matiere(){
        double res = e1.calculMoyenne("DevWeb");
        assertEquals(0,res,"La moyenne doit être à 0 car la matière n'a pas de note");
    }

    @Test
    public void test_calculMoyenneG_normal(){
        e1.ajouterNote("DevWeb", 9);
        e1.ajouterNote("Management SI", 12);
        double res = e1.calculMoyenneG();
        assertEquals(11,res,"La moyenne doit être à 11");
    }

    @Test
    public void test_calculMoyenneG_1coefNegatif(){
        e1.ajouterNote("DevWeb", 9);
        e1.ajouterNote("Anglais", 20);
        double res = e1.calculMoyenneG();
        assertEquals(9,res,"La moyenne doit être à 9 car la note d'anglais ne compte pas");
    }

    @Test
    public void test_calculMoyenneG_coefNegatifPartout(){
        e1.ajouterNote("Communication", 9);
        e1.ajouterNote("Anglais", 20);
        double res = e1.calculMoyenneG();
        assertEquals(0,res,"La moyenne doit être à 0 car aucune des 2 matière a un coef possible");
    }

}
