public class Identite {
    private final String NIP;
    private final String nom;
    private final String prenom;

    public Identite(String NIP, String nom, String prenom) {
        this.NIP = NIP;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNIP() {
        return NIP;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    @Override
    public String toString() {
        return "Identite{" +
                "NIP='" + NIP + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}
