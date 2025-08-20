package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.services.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur REST pour le service "Hello".
 * <p>
 * Cette classe expose un endpoint pour renvoyer un message de salutations.
 * Elle utilise {@link HelloService} pour générer le message.
 * </p>
 * <p>
 * URL de base : <code>/hello</code>
 * </p>
 */
@RestController
@RequestMapping("/hello")
public class HelloControleur {

    /** Service pour générer le message de salutations */
    @Autowired
    private HelloService helloService;

    /**
     * Endpoint GET pour renvoyer un message de salutations.
     * <p>
     * Exemple d'appel HTTP : <code>GET /hello</code>
     * </p>
     *
     * @return chaîne de caractères contenant le message de salutations
     */
    @GetMapping
    public String direHello() {
        return helloService.salutations();
    }
}