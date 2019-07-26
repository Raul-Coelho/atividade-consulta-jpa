
package mapeamento.questão1;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;

@Entity
public class Revisor extends PessoaDois implements Serializable{
    
    private String nota;

    public Revisor() {
    }

    public Revisor(String nota, int id, String nome, LocalDate dataNascimento) {
        super(id, nome, dataNascimento);
        this.nota = nota;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Revisor{" + "nota=" + nota + '}';
    }
    
}
