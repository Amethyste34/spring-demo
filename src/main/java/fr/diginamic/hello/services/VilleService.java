package fr.diginamic.hello.services;

import fr.diginamic.hello.model.Ville;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class VilleService {

    private final List<Ville> villes = new ArrayList<>();

    public VilleService() {
        // Initialisation avec quelques villes
        villes.add(new Ville("Paris", 2160000));
        villes.add(new Ville("Lyon", 515000));
        villes.add(new Ville("Marseille", 861000));
    }

    public List<Ville> getVilles() {
        return villes;
    }

    public boolean addVille(Ville ville) {
        // Vérifier si une ville avec le même nom existe déjà (insensible à la casse)
        boolean exists = villes.stream()
                .anyMatch(v -> v.getNom().equalsIgnoreCase(ville.getNom()));

        if (exists) {
            return false; // Ville déjà existante
        }

        villes.add(ville);
        return true;
    }
}