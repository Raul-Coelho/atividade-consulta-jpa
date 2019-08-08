
package mapeamento.questão2;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Revisor extends PessoaDois implements Serializable{
    
    private String nota;
    @OneToMany(mappedBy="revisor",cascade={CascadeType.ALL})
    private List<Publicacao> publicacoes;
    
    public Revisor() {
    }

    public Revisor(String nota, List<Publicacao> publicacoes, int id, String nome, LocalDate dataNascimento) {
        super(id, nome, dataNascimento);
        this.nota = nota;
        this.publicacoes = publicacoes;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Collection<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    public void setPublicacoes(List<Publicacao> publicacoes) {
        this.publicacoes = publicacoes;
    }

    @Override
    public String toString() {
        return "Revisor{" + "nota=" + nota + ", publicacoes=" + publicacoes + '}';
    }
    
}
