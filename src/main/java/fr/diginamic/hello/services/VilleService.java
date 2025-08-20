package fr.diginamic.hello.services;

import fr.diginamic.hello.model.Ville;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class VilleService {

    public List<Ville> getVilles() {
        return Arrays.asList(
                new Ville("Paris", 2160000),
                new Ville("Lyon", 515000),
                new Ville("Marseille", 861000)
        );
    }
}