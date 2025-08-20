package fr.diginamic.hello.services;

import fr.diginamic.hello.model.Ville;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VilleService {

    private final List<Ville> villes = new ArrayList<>();
    private int nextId = 1;

    public VilleService() {
        // Initialisation avec quelques villes
        villes.add(new Ville(nextId++,"Paris", 2160000));
        villes.add(new Ville(nextId++,"Lyon", 515000));
        villes.add(new Ville(nextId++,"Marseille", 861000));
    }

    public List<Ville> getAllVilles() {
        return villes;
    }

    public Optional<Ville> getVilleById(int id) {
        return villes.stream().filter(v -> v.getId() == id).findFirst();
    }

    public boolean addVille(Ville ville) {
        // Vérifier si une ville avec le même nom existe déjà (insensible à la casse)
        boolean exists = villes.stream()
                .anyMatch(v -> v.getNom().equalsIgnoreCase(ville.getNom()));

        if (exists) {
            return false; // Ville déjà existante
        }
        ville.setId((nextId++));
        villes.add(ville);
        return true;
    }

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

    public boolean deleteVille(int id) {
        return villes.removeIf(v -> v.getId() == id);
    }
}