package ro.unibuc.rentacar.converter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import ro.unibuc.rentacar.entity.Autovehicul;
import ro.unibuc.rentacar.repository.AutovehiculRepository;

import java.util.Locale;

@Component
public class AutovehiculFormatter implements Formatter<Autovehicul> {

    private final AutovehiculRepository repo;

    public AutovehiculFormatter(AutovehiculRepository repo) {
        this.repo = repo;
    }

    @Override
    public Autovehicul parse(String text, Locale locale) {
        if (text == null || text.isBlank()) return null;
        return repo.findById(Long.valueOf(text)).orElse(null);
    }

    @Override
    public String print(Autovehicul a, Locale locale) {
        return (a == null || a.getIdAutovehicul() == null) ? "" : a.getIdAutovehicul().toString();
    }
}