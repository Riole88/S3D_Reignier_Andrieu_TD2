import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class testFormation {

    private Formation formation;

    @BeforeEach
    public void setUp() {
        // Initialisation de la formation avant chaque test
        formation = new Formation("Informatique");
    }

    @Test
    public void testAjoutMatiere() { //Test de la méthode ajoutMatiere en ajoutant qdev et devweb avec les différents coeff
        formation.ajouteMatiere("Qualité de développement", 4.0);
        formation.ajouteMatiere("Développement Web", 3.0);
        assertEquals(4.0, formation.getCoef("Qualité de développement"), "Le coefficient de Qualité de développement devrait être 4.0");
        assertEquals(3.0, formation.getCoef("Développement Web"), "Le coefficient de Développement Web devrait être 3.0");
    }

    @Test
    public void testGetCoefMatiereExistante() { // Test de la méthode coefMatiereExistante
        formation.ajouteMatiere("Informatique", 5.0);
        assertEquals(5.0, formation.getCoef("Informatique"), "Le coefficient de Informatique devrait être 5.0");
    }

    @Test
    public void testGetCoefMatiereInexistante() { // test de la méthode CoefMatiereInexistante
        assertEquals(-1, formation.getCoef("Biologie"), "Le coefficient d'une matière inexistante devrait être -1");
    }

    @Test
    public void testSupMatiereExistante() { // Test de la méthode SupMatiereExistante
        formation.ajouteMatiere("Qualité de développement", 4.0);
        formation.supMatiere("Qualité de développement", 4.0);
        assertEquals(-1, formation.getCoef("Qualité de développement"), "Le coefficient de Qualité de développement devrait être -1 après suppression");
    }



    @Test
    public void testToString() { // Test de la méthode ToString
        formation.ajouteMatiere("Qualité de développement", 4.0);
        String e = "Formation{idt='Informatique', matiere={Qualité de développement=4.0}}";
        assertEquals(e, formation.toString(), "La méthode toString devrait afficher correctement la formation.");
    }

}
