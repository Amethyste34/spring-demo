package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.model.Ville;
import fr.diginamic.hello.services.VilleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Ville> getVilles() {
        return service.getVilles();
    }
}