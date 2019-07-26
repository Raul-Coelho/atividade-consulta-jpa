
package mapeamento.questão1;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Escritor extends PessoaDois implements Serializable{
    
    private int premios;
    @OneToMany(mappedBy="escritor",cascade={CascadeType.ALL})
    private Collection<Publicacao> publicacoes;

    public Escritor() {
    }

    public Escritor(int premios, Collection<Publicacao> publicacoes, int id, String nome, LocalDate dataNascimento) {
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

    public Collection<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    public void setPublicacoes(Collection<Publicacao> publicacoes) {
        this.publicacoes = publicacoes;
    }

    @Override
    public String toString() {
        return "Escritor{" + "premios=" + premios + ", publicacoes=" + publicacoes + '}';
    }
    
}
