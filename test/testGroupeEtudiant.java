import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Exception.InsertionInvalidGroupeEtudiant;

public class testGroupeEtudiant {

    private GroupeEtudiant groupeDACS;
    private GroupeEtudiant groupeDWM;
    private Etudiant etudiant1;
    private Etudiant etudiant2;
    private Etudiant etudiant3;

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
    public void testSuppressionEtudiant() throws InsertionInvalidGroupeEtudiant {
        groupeDACS.ajouterE(etudiant2);
        assertTrue(groupeDACS.getEtudiant().contains(etudiant2), "L'étudiant Martin Ronot devrait être ajouté au groupe DACS.");

        groupeDACS.supprimerE(etudiant2);
        assertFalse(groupeDACS.getEtudiant().contains(etudiant2), "L'étudiant Ronot devrait être supprimé du groupe DACS.");
    }

    @Test
    public void testSuppressionEtudiantNonExistant() {
        groupeDACS.supprimerE(etudiant3);  // Étudiant qui n'a jamais été ajouté
        assertFalse(groupeDACS.getEtudiant().contains(etudiant3), "L'étudiant Théo Vautrin ne devrait pas exister dans le groupe DACS.");
    }
}
