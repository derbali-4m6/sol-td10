package ca.qc.sol_td10.entities;

public class Destination {
    private String nom_recepteur;
    private String adresse;
    private String carte_id;

    public Destination() {
    }

    public Destination(String nom_recepteur, String adresse, String carte_id) {
        this.nom_recepteur = nom_recepteur;
        this.adresse = adresse;
        this.carte_id = carte_id;
    }

    public String getNom_recepteur() {
        return nom_recepteur;
    }

    public void setNom_recepteur(String nom_recepteur) {
        this.nom_recepteur = nom_recepteur;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCarte_id() {
        return carte_id;
    }

    public void setCarte_id(String carte_id) {
        this.carte_id = carte_id;
    }

    @Override
    public String toString() {
        return "Destination{" +
                "nom_recepteur='" + nom_recepteur + '\'' +
                ", adresse='" + adresse + '\'' +
                ", carte_id='" + carte_id + '\'' +
                '}';
    }
}
