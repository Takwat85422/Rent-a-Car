package ro.unibuc.rentacar.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // id inexistent — aruncat de findById din servicii
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleNotFound(IllegalArgumentException ex, Model model) {
        model.addAttribute("titlu", "Inregistrare inexistenta");
        model.addAttribute("mesaj", ex.getMessage());
        return "eroare";
    }

    // stergere blocata de cheie straina (ex: stergi un client care are contracte)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleFKViolation(DataIntegrityViolationException ex, Model model) {
        model.addAttribute("titlu", "Stergere imposibila");
        model.addAttribute("mesaj",
                "Inregistrarea nu poate fi stearsa deoarece este referita de alte date din sistem. "
                        + "Sterge mai intai datele asociate.");
        return "eroare";
    }

    // orice alta eroare neasteptata
    @ExceptionHandler(Exception.class)
    public String handleGeneric(Exception ex, Model model) {
        model.addAttribute("titlu", "Eroare neasteptata");
        model.addAttribute("mesaj", ex.getMessage());
        return "eroare";
    }
}