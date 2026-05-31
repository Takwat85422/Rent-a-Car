package ro.unibuc.rentacar.converter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import ro.unibuc.rentacar.entity.Locatie;
import ro.unibuc.rentacar.repository.LocatieRepository;

import java.util.Locale;

@Component
public class LocatieFormatter implements Formatter<Locatie> {

    private final LocatieRepository repo;

    public LocatieFormatter(LocatieRepository repo) {
        this.repo = repo;
    }

    @Override
    public Locatie parse(String text, Locale locale) {
        if (text == null || text.isBlank()) return null;
        return repo.findById(Long.valueOf(text)).orElse(null);
    }

    @Override
    public String print(Locatie l, Locale locale) {
        return (l == null || l.getIdLocatie() == null) ? "" : l.getIdLocatie().toString();
    }
}