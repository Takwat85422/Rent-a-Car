package ro.unibuc.rentacar.converter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import ro.unibuc.rentacar.entity.ContractInchiriere;
import ro.unibuc.rentacar.repository.ContractInchiriereRepository;

import java.util.Locale;

@Component
public class ContractInchiriereFormatter implements Formatter<ContractInchiriere> {

    private final ContractInchiriereRepository repo;

    public ContractInchiriereFormatter(ContractInchiriereRepository repo) {
        this.repo = repo;
    }

    @Override
    public ContractInchiriere parse(String text, Locale locale) {
        if (text == null || text.isBlank()) return null;
        return repo.findById(Long.valueOf(text)).orElse(null);
    }

    @Override
    public String print(ContractInchiriere c, Locale locale) {
        return (c == null || c.getIdContract() == null) ? "" : c.getIdContract().toString();
    }
}