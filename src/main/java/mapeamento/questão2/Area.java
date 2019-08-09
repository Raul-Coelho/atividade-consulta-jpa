
package mapeamento.questão2;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Area implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int cod;
    private String nome;
    @ManyToOne()
    private Publicacao publicacao;

    public Area() {
    }

    public Area(int cod, String nome, Publicacao publicacao) {
        this.cod = cod;
        this.nome = nome;
        this.publicacao = publicacao;
    }

    public Area(String nome) {
        this.nome = nome;
    }



    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    @Override
    public String toString() {
        return "Area{" + "cod=" + cod + ", nome=" + nome + ", publicacao=" + publicacao + '}';
    }
}
