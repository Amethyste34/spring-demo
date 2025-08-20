package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.model.Ville;
import fr.diginamic.hello.services.VilleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    private final VilleService service;

    // Injection par constructeur
    public VilleControleur(VilleService service) {
        this.service = service;
    }

    @GetMapping
    public List<Ville> getAllVilles() {
        return service.getAllVilles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVilleById(@PathVariable int id) {
        return service.getVilleById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().body("Ville introuvable"));
    }

    @PostMapping
    public ResponseEntity<String> addVille(@RequestBody Ville ville) {
        boolean added = service.addVille(ville);

        if (added) {
            return ResponseEntity.ok("Ville insérée avec succès");
        } else {
            return ResponseEntity.badRequest().body("La ville existe déjà");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateVille(@PathVariable int id, @RequestBody Ville newVille) {
        boolean updated = service.updateVille(id, newVille);
        if (updated) {
            return ResponseEntity.ok("Ville modifiée avec succès");
        } else {
            return ResponseEntity.badRequest().body("Ville introuvable");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVille(@PathVariable int id) {
        boolean deleted = service.deleteVille(id);
        if (deleted) {
            return ResponseEntity.ok("Ville supprimée avec succès");
        } else {
            return ResponseEntity.badRequest().body("Ville introuvable");
        }
    }
}