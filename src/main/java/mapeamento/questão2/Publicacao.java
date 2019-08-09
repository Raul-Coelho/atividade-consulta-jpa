
package mapeamento.questão2;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Publicacao implements Serializable{

    @Id
    private int codPublicacao;
    private String titulo;
    @OneToMany(mappedBy="publicacao",cascade={CascadeType.PERSIST})
    private List<Area> areas;
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Revisor revisor;
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Escritor escritor;

    public Publicacao() {
    }

    public Publicacao(int codPublicacao, String titulo, List<Area> areas, Revisor revisor, Escritor escritor) {
        this.codPublicacao = codPublicacao;
        this.titulo = titulo;
        this.areas = areas;
        this.revisor = revisor;
        this.escritor = escritor;
    }

    public Publicacao(int codPublicacao, String titulo, List<Area> areas) {
        this.codPublicacao = codPublicacao;
        this.titulo = titulo;
        this.areas = areas;
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

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public Revisor getRevisor() {
        return revisor;
    }

    public void setRevisor(Revisor revisor) {
        this.revisor = revisor;
    }

    public Escritor getEscritor() {
        return escritor;
    }

    public void setEscritor(Escritor escritor) {
        this.escritor = escritor;
    }

    @Override
    public String toString() {
        return "Publicacao{" + "codPublicacao=" + codPublicacao + ", titulo=" + titulo + '}';
    }

}
