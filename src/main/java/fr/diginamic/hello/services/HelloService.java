package fr.diginamic.hello.services;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Service pour générer des messages de salutations.
 * <p>
 * Cette classe fournit un bean {@link #salutations()} qui peut être utilisé
 * par un contrôleur pour renvoyer un message de bienvenue ou de salutations.
 * </p>
 */
@Service
public class HelloService {

    /**
     * Bean de salutations.
     * <p>
     * Exemple d'utilisation : un contrôleur peut appeler ce bean pour obtenir
     * le message de salutations à retourner à l'utilisateur.
     * </p>
     *
     * @return une chaîne de caractères contenant le message de salutations
     */
    @Bean
    public String salutations() {
        return "Je suis la classe de service et je vous dis Bonjour";
    }
}