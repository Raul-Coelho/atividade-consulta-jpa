
package mapeamento.questão2;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Escritor extends PessoaDois implements Serializable{

    private int premios;
    @OneToMany(mappedBy="escritor",cascade={CascadeType.ALL})
    private List<Publicacao> publicacoes;

    public Escritor() {
    }

    public Escritor(int premios, List<Publicacao> publicacoes, int id, String nome, LocalDate dataNascimento) {
        super(id, nome, dataNascimento);
        this.premios = premios;
        this.publicacoes = publicacoes;
    }

    public int getPremios() {
        return premios;
    }

    public void setPremios(int premios) {
        this.premios = premios;
    }

    public List<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    public void setPublicacoes(List<Publicacao> publicacoes) {
        this.publicacoes = publicacoes;
    }

    @Override
    public String toString() {
        return "Escritor{" + "premios=" + premios + ", publicacoes=" + publicacoes + '}';
    }
}
