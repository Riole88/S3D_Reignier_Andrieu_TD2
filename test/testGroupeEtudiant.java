import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Exception.*;

public class testGroupeEtudiant {

    private GroupeEtudiant groupeDACS;
    private GroupeEtudiant groupeDWM;
    private Etudiant etudiant1;
    private Etudiant etudiant2;
    private Etudiant etudiant3;
    private Etudiant etudiant4;

    @BeforeEach
    public void setUp() {
        // Initialisation des formations
        Formation formationDACS = new Formation("DACS");
        Formation formationDWM = new Formation("DWM");

        // Ajout des matières aux formations
        formationDACS.ajouteMatiere("Programmation", 4.0);
        formationDACS.ajouteMatiere("Algorithmes", 5.0);
        formationDWM.ajouteMatiere("Génétique", 6.0);

        // Création des étudiants
        etudiant1 = new Etudiant(new Identite("1234", "Andrieu","Paul"), formationDACS);
        etudiant2 = new Etudiant(new Identite("87665", "Martin","Ronot"), formationDACS);
        etudiant3 = new Etudiant(new Identite("0976", "Theo","Vautrin"), formationDWM);
        etudiant4 = new Etudiant(new Identite("16589","Leroy", "Jenkins"),formationDACS);

        // Initialisation des groupes
        groupeDACS = new GroupeEtudiant(formationDACS);
        groupeDWM = new GroupeEtudiant(formationDWM);
    }

    @Test
    public void testAjoutEtudiantFormationValide() throws InsertionInvalidGroupeEtudiant {
        groupeDACS.ajouterE(etudiant1);  // Valide
        assertTrue(groupeDACS.getEtudiant().contains(etudiant1), "L'étudiant Paul Andrieu devrait être ajouté à la formation DACS");
    }


    @Test
    public void testAjoutEtudiantFormationInvalide() {
        // Vérifie que l'ajout d'un étudiant d'une formation différente lève l'exception
        InsertionInvalidGroupeEtudiant exception = assertThrows(InsertionInvalidGroupeEtudiant.class, () -> {
            groupeDACS.ajouterE(etudiant3);  // Tentative d'ajouter un étudiant de la mauvaise formation
        });

        // Vérifie que le message de l'exception contient le texte attendu
        String expectedMessage = "Erreur : L'étudiant n'est pas dans la formation DACS";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    public void testSuppressionEtudiant() throws InsertionInvalidGroupeEtudiant, ExceptionEtudiantNonTrouve{
        groupeDACS.ajouterE(etudiant2);
        assertTrue(groupeDACS.getEtudiant().contains(etudiant2), "L'étudiant Martin Ronot devrait être ajouté au groupe DACS.");

        groupeDACS.supprimerE(etudiant2);
        assertFalse(groupeDACS.getEtudiant().contains(etudiant2), "L'étudiant Ronot devrait être supprimé du groupe DACS.");
    }

    @Test
    public void testSuppressionEtudiantNonExistant(){
        assertThrows(ExceptionEtudiantNonTrouve.class, () ->{
            groupeDACS.supprimerE(etudiant3);
        });
    }

    @Test
    public void test_triAlpha() throws InsertionInvalidGroupeEtudiant{
        //Ajout des étudiants dans un ordre non défini
        groupeDACS.ajouterE(etudiant4);
        groupeDACS.ajouterE(etudiant2);
        groupeDACS.ajouterE(etudiant1);
        //Utilisation de la méthode de tri
        groupeDACS.triAlpha();
        //Vérification de l'ordre de la liste du groupe étudiant
        assertEquals(etudiant1, groupeDACS.getEtudiant().get(0));
        assertEquals(etudiant4, groupeDACS.getEtudiant().get(1));
        assertEquals(etudiant2, groupeDACS.getEtudiant().get(2));
    }

    @Test
    public void test_triAntiAlpha() throws InsertionInvalidGroupeEtudiant{
        //Ajout des étudiants dans un ordre non défini
        groupeDACS.ajouterE(etudiant4);
        groupeDACS.ajouterE(etudiant1);
        groupeDACS.ajouterE(etudiant2);
        //Utilisation de la méthode de tri
        groupeDACS.triAntiAlpha();
        //Vérification de l'ordre de la liste du groupe étudiant
        assertEquals(etudiant2, groupeDACS.getEtudiant().get(0));
        assertEquals(etudiant4, groupeDACS.getEtudiant().get(1));
        assertEquals(etudiant1, groupeDACS.getEtudiant().get(2));
    }

    @Test
    public void test_tri_merite()throws Exception{
        //Ajout des étudiants dans le groupe
        groupeDACS.ajouterE(etudiant4);
        groupeDACS.ajouterE(etudiant1);
        groupeDACS.ajouterE(etudiant2);

        //Ajout des notes
        etudiant1.ajouterNote("Programmation",16);
        etudiant2.ajouterNote("Programmation",10);
        etudiant4.ajouterNote("Programmation", 4);

        etudiant1.ajouterNote("Algorithmes",17);
        etudiant2.ajouterNote("Algorithmes",10);
        etudiant4.ajouterNote("Algorithmes",5);

        //Appel de la méthode
        groupeDACS.triParMerite();

        //Vérification de l'ordre du groupe
        assertEquals(etudiant1, groupeDACS.getEtudiant().get(0));
        assertEquals(etudiant2, groupeDACS.getEtudiant().get(1));
        assertEquals(etudiant4, groupeDACS.getEtudiant().get(2));
    }
}
