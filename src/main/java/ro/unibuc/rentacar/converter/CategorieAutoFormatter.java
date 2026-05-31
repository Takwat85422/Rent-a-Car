package ro.unibuc.rentacar.converter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import ro.unibuc.rentacar.entity.CategorieAuto;
import ro.unibuc.rentacar.repository.CategorieAutoRepository;

import java.util.Locale;

@Component
public class CategorieAutoFormatter implements Formatter<CategorieAuto> {

    private final CategorieAutoRepository repo;

    public CategorieAutoFormatter(CategorieAutoRepository repo) {
        this.repo = repo;
    }

    // String (id-ul din <select>) -> CategorieAuto
    @Override
    public CategorieAuto parse(String text, Locale locale) {
        if (text == null || text.isBlank()) {
            return null;
        }
        return repo.findById(Long.valueOf(text)).orElse(null);
    }

    // CategorieAuto -> String (id), ca sa stie ce optiune sa marcheze ca selectata la editare
    @Override
    public String print(CategorieAuto categorie, Locale locale) {
        return (categorie == null || categorie.getIdCategorie() == null)
                ? "" : categorie.getIdCategorie().toString();
    }
}