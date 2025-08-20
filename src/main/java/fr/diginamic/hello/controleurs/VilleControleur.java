package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.model.Ville;
import fr.diginamic.hello.services.VilleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    private final VilleService service;

    // Injection par constructeur
    public VilleControleur(VilleService service) {
        this.service = service;
    }

    @GetMapping
    public List<Ville> extractVilles() {
        return service.getVilles();
    }

    // Étape 1 : méthode POST pour insérer une ville
    @PostMapping
    public ResponseEntity<String> addVille(@RequestBody Ville ville) {
        boolean added = service.addVille(ville);

        if (added) {
            return ResponseEntity.ok("Ville insérée avec succès");
        } else {
            return ResponseEntity.badRequest().body("La ville existe déjà");
        }
    }
}