
package mapeamento.questão2;

import conversores.ConversorLocalDate;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PessoaDois implements Serializable{

    @Id
    private int id;
    private String nome;
    @Convert(converter=ConversorLocalDate.class)
    private LocalDate dataNascimento;

    public PessoaDois() {
    }

    public PessoaDois(int id, String nome, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public PessoaDois(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "PessoaDois{" + "id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento + '}';
    }
    
}
