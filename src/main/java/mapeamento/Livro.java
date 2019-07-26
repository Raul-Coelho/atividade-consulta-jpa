package mapeamento;

import conversores.ConversorLocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Livro {

    private String titulo;
    @Id
    private String ISBN;
    @Convert(converter = ConversorLocalDate.class)
    private LocalDate lancamento;

    public Livro() {

    }

    public Livro(String titulo, String isbn, LocalDate lancamento) {
        this.titulo = titulo;
        ISBN = isbn;
        this.lancamento = lancamento;
    }
}
