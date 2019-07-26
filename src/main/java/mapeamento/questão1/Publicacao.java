
package mapeamento.questão1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Publicacao implements Serializable{
    
    @Id
    private int codPublicacao;
    private String titulo;
    @OneToMany(mappedBy="publicacao",cascade={CascadeType.ALL})
    private Collection<Area> area;

    public Publicacao() {
    }

    public Publicacao(int codPublicacao, String titulo, Collection<Area> area) {
        this.codPublicacao = codPublicacao;
        this.titulo = titulo;
        this.area = area;
    }

    public int getCodPublicacao() {
        return codPublicacao;
    }

    public void setCodPublicacao(int codPublicacao) {
        this.codPublicacao = codPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Collection<Area> getArea() {
        return area;
    }

    public void setArea(Collection<Area> area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Publicacao{" + "codPublicacao=" + codPublicacao + ", titulo=" + titulo + '}';
    }
    
}
