package fr.diginamic.hello.services;

import fr.diginamic.hello.model.Ville;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service pour gérer les opérations sur les villes.
 * <p>
 * Cette classe fournit la logique métier pour :
 * <ul>
 *     <li>Récupérer toutes les villes</li>
 *     <li>Récupérer une ville par son identifiant</li>
 *     <li>Ajouter une nouvelle ville</li>
 *     <li>Mettre à jour une ville existante</li>
 *     <li>Supprimer une ville</li>
 * </ul>
 * Les villes sont stockées en mémoire dans une liste.
 * </p>
 */
@Service
public class VilleService {

    /** Liste en mémoire des villes */
    private final List<Ville> villes = new ArrayList<>();

    /** Prochain identifiant à attribuer à une ville */
    private int nextId = 1;

    /**
     * Constructeur du service.
     * <p>
     * Initialise la liste avec quelques villes par défaut.
     * </p>
     */
    public VilleService() {
        villes.add(new Ville(nextId++, "Paris", 2160000));
        villes.add(new Ville(nextId++, "Lyon", 515000));
        villes.add(new Ville(nextId++, "Marseille", 861000));
    }

    /**
     * Récupère toutes les villes.
     *
     * @return liste de toutes les villes
     */
    public List<Ville> getAllVilles() {
        return villes;
    }

    /**
     * Récupère une ville par son identifiant.
     *
     * @param id identifiant de la ville
     * @return un Optional contenant la ville si trouvée, ou vide sinon
     */
    public Optional<Ville> getVilleById(int id) {
        return villes.stream()
                .filter(v -> v.getId() == id)
                .findFirst();
    }

    /**
     * Ajoute une nouvelle ville à la liste.
     * <p>
     * Vérifie qu'aucune ville avec le même nom (insensible à la casse) n'existe déjà.
     * </p>
     *
     * @param ville l'objet Ville à ajouter
     * @return true si la ville a été ajoutée, false si elle existait déjà
     */
    public boolean addVille(Ville ville) {
        boolean exists = villes.stream()
                .anyMatch(v -> v.getNom().equalsIgnoreCase(ville.getNom()));

        if (exists) {
            return false;
        }
        ville.setId(nextId++);
        villes.add(ville);
        return true;
    }

    /**
     * Met à jour une ville existante.
     *
     * @param id       identifiant de la ville à mettre à jour
     * @param newVille objet Ville contenant les nouvelles informations
     * @return true si la ville a été trouvée et mise à jour, false sinon
     */
    public boolean updateVille(int id, Ville newVille) {
        Optional<Ville> optVille = getVilleById(id);
        if (optVille.isPresent()) {
            Ville ville = optVille.get();
            ville.setNom(newVille.getNom());
            ville.setNbHabitants(newVille.getNbHabitants());
            return true;
        }
        return false;
    }

    /**
     * Supprime une ville par son identifiant.
     *
     * @param id identifiant de la ville à supprimer
     * @return true si la ville a été supprimée, false si elle n'existait pas
     */
    public boolean deleteVille(int id) {
        return villes.removeIf(v -> v.getId() == id);
    }
}