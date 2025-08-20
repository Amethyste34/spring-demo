package fr.diginamic.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principale de l'application Spring Boot "Hello".
 * <p>
 * Cette classe est annotée avec {@link SpringBootApplication}, ce qui :
 * <ul>
 *     <li>Indique à Spring Boot qu'il s'agit de la classe de configuration principale</li>
 *     <li>Active l'auto-configuration de Spring Boot</li>
 *     <li>Scanne les composants dans le package courant et ses sous-packages</li>
 * </ul>
 * </p>
 * <p>
 * Le point d'entrée de l'application est la méthode {@link #main(String[])}.
 * Lors de l'exécution, Spring Boot démarre un serveur web intégré (ex. Tomcat)
 * et initialise tous les beans Spring.
 * </p>
 */
@SpringBootApplication
public class HelloApplication {

	/**
	 * Point d'entrée de l'application Spring Boot.
	 *
	 * @param args arguments de ligne de commande passés à l'application
	 */
	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}
}