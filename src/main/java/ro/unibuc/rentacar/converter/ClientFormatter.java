package ro.unibuc.rentacar.converter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import ro.unibuc.rentacar.entity.Client;
import ro.unibuc.rentacar.repository.ClientRepository;

import java.util.Locale;

@Component
public class ClientFormatter implements Formatter<Client> {

    private final ClientRepository repo;

    public ClientFormatter(ClientRepository repo) {
        this.repo = repo;
    }

    @Override
    public Client parse(String text, Locale locale) {
        if (text == null || text.isBlank()) {
            return null;
        }
        return repo.findById(Long.valueOf(text)).orElse(null);
    }

    @Override
    public String print(Client client, Locale locale) {
        return (client == null || client.getIdClient() == null)
                ? "" : client.getIdClient().toString();
    }
}