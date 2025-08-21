package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.model.Ville;
import fr.diginamic.hello.services.VilleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Validator;
import java.util.List;

/**
 * Contrôleur REST pour gérer les opérations sur les villes.
 * <p>
 * Cette classe fournit des endpoints pour :
 * <ul>
 *     <li>Récupérer toutes les villes</li>
 *     <li>Récupérer une ville par son identifiant</li>
 *     <li>Ajouter une nouvelle ville</li>
 *     <li>Mettre à jour une ville existante</li>
 *     <li>Supprimer une ville</li>
 * </ul>
 * Les réponses HTTP sont renvoyées avec le statut approprié et un message explicatif.
 * </p>
 */
@RestController
@RequestMapping("/villes")
public class VilleControleur {

    /** Service pour gérer la logique métier des villes */
    private final VilleService service;

    /** Injection du validator */
    @Autowired
    private Validator validator;

    /**
     * Constructeur de VilleControleur.
     * <p>
     * Le service VilleService est injecté via le constructeur pour permettre
     * l'accès aux opérations sur les villes.
     * </p>
     *
     * @param service le service de gestion des villes
     */
    public VilleControleur(VilleService service) {
        this.service = service;
    }

    /**
     * Récupère la liste de toutes les villes.
     *
     * @return une liste de toutes les instances de {@link Ville}
     */
    @GetMapping
    public List<Ville> getAllVilles() {
        return service.getAllVilles();
    }

    /**
     * Récupère une ville à partir de son identifiant.
     *
     * @param id l'identifiant de la ville
     * @return ResponseEntity contenant la ville si trouvée, ou un message d'erreur si introuvable
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getVilleById(@PathVariable int id) {
        return service.getVilleById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().body("Ville introuvable"));
    }

    /**
     * Ajoute une nouvelle ville.
     *
     * @param ville l'objet {@link Ville} à ajouter
     * @param result le résultat de la validation
     * @return ResponseEntity avec un message indiquant si l'ajout a réussi ou si la validation échoue
     */
    @PostMapping
    public ResponseEntity<String> addVille(@Valid @RequestBody Ville ville, BindingResult result) {
        if (result.hasErrors()) {
            // Récupération des messages d'erreur
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(err -> errors.append(err.getDefaultMessage()).append("; "));
            return ResponseEntity.badRequest().body(errors.toString());
        }

        boolean added = service.addVille(ville);

        if (added) {
            return ResponseEntity.ok("Ville insérée avec succès");
        } else {
            return ResponseEntity.badRequest().body("La ville existe déjà");
        }
    }

    /**
     * Met à jour une ville existante.
     *
     * @param id       l'identifiant de la ville à mettre à jour
     * @param newVille l'objet {@link Ville} contenant les nouvelles informations
     * @return ResponseEntity avec un message indiquant si la mise à jour a réussi ou si la ville n'existe pas
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateVille(@PathVariable int id, @RequestBody Ville newVille) {
        // on force l’ID à partir du path
        newVille.setId(id);

        // ⚡ validation manuelle
        Errors result = new BeanPropertyBindingResult(newVille, "ville");
        validator.validate(newVille, result);

        if (result.hasErrors()) {
            // On récupère le premier message d’erreur
            return ResponseEntity.badRequest().body(
                    result.getFieldErrors().get(0).getCode() + " " +
                            result.getFieldErrors().get(0).getDefaultMessage()
            );
        }

        boolean updated = service.updateVille(id, newVille);
        if (updated) {
            return ResponseEntity.ok("Ville modifiée avec succès");
        } else {
            return ResponseEntity.badRequest().body("Ville introuvable");
        }
    }

    /**
     * Supprime une ville à partir de son identifiant.
     *
     * @param id l'identifiant de la ville à supprimer
     * @return ResponseEntity avec un message indiquant si la suppression a réussi ou si la ville n'existe pas
     */
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