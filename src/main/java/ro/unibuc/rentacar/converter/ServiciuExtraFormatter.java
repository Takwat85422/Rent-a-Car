package ro.unibuc.rentacar.converter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import ro.unibuc.rentacar.entity.ServiciuExtra;
import ro.unibuc.rentacar.repository.ServiciuExtraRepository;

import java.util.Locale;

@Component
public class ServiciuExtraFormatter implements Formatter<ServiciuExtra> {

    private final ServiciuExtraRepository repo;

    public ServiciuExtraFormatter(ServiciuExtraRepository repo) {
        this.repo = repo;
    }

    @Override
    public ServiciuExtra parse(String text, Locale locale) {
        if (text == null || text.isBlank()) return null;
        return repo.findById(Long.valueOf(text)).orElse(null);
    }

    @Override
    public String print(ServiciuExtra s, Locale locale) {
        return (s == null || s.getIdServiciu() == null) ? "" : s.getIdServiciu().toString();
    }
}