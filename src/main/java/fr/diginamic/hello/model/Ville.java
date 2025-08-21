package fr.diginamic.hello.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Classe représentant une ville.
 * <p>
 * Une ville est caractérisée par :
 * <ul>
 *     <li>un identifiant unique {@link #id}</li>
 *     <li>un nom {@link #nom}</li>
 *     <li>un nombre d'habitants {@link #nbHabitants}</li>
 * </ul>
 * Cette classe fournit des accesseurs et mutateurs pour tous les attributs,
 * ainsi qu'une méthode {@link #toString()} pour un affichage lisible.
 * </p>
 */
public class Ville {

    /** Identifiant unique de la ville */
    private int id;

    /** Nom de la ville */
    @NotNull(message = "Le nom de la ville ne doit pas être nul")
    @Size(min = 2, message = "Le nom de la ville doit contenir au moins 2 caractères")
    private String nom;

    /** Nombre d'habitants de la ville */
    @Min(value = 1, message = "Le nombre d'habitants doit être supérieur ou égal à 1")
    private int nbHabitants;

    /**
     * Constructeur vide obligatoire pour Jackson.
     */
    public Ville() {
    }

    /**
     * Constructeur avec paramètres.
     *
     * @param id          identifiant unique de la ville
     * @param nom         nom de la ville
     * @param nbHabitants nombre d'habitants de la ville
     */
    public Ville(int id, String nom, int nbHabitants) {
        this.id = id;
        this.nom = nom;
        this.nbHabitants = nbHabitants;
    }

    /**
     * Retourne l'identifiant de la ville.
     *
     * @return l'id de la ville
     */
    public int getId() {
        return id;
    }

    /**
     * Définit l'identifiant de la ville.
     *
     * @param id nouvel identifiant
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retourne le nom de la ville.
     *
     * @return le nom de la ville
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de la ville.
     *
     * @param nom nouveau nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne le nombre d'habitants de la ville.
     *
     * @return nombre d'habitants
     */
    public int getNbHabitants() {
        return nbHabitants;
    }

    /**
     * Définit le nombre d'habitants de la ville.
     *
     * @param nbHabitants nouveau nombre d'habitants
     */
    public void setNbHabitants(int nbHabitants) {
        this.nbHabitants = nbHabitants;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères
     * de la ville, comprenant le nom et le nombre d'habitants.
     *
     * @return chaîne de caractères représentant la ville
     */
    @Override
    public String toString() {
        return "Ville{" +
                "nom='" + nom + '\'' +
                ", nbHabitants=" + nbHabitants +
                '}';
    }
}