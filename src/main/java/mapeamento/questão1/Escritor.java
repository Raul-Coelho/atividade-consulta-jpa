
package mapeamento.questão1;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;

@Entity
public class Escritor extends PessoaDois implements Serializable{
    
    private int premios;

    public Escritor() {
    }

    public Escritor(int premios, int id, String nome, LocalDate dataNascimento) {
        super(id, nome, dataNascimento);
        this.premios = premios;
    }

    public int getPremios() {
        return premios;
    }

    public void setPremios(int premios) {
        this.premios = premios;
    }

    @Override
    public String toString() {
        return "Escritor{" + "premios=" + premios + '}';
    }
    
}
