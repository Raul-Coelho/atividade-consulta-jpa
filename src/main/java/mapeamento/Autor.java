package mapeamento;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Autor extends PessoaUm {
    private String instituicaoVinculada;

    public Autor(){

    }

    public Autor(String instituicaoVinculada) {
        this.instituicaoVinculada = instituicaoVinculada;
    }

    public Autor(String nome, String cpf, Integer idade, LocalDate dataNasc, String instituicaoVinculada) {
        super(nome, cpf, idade, dataNasc);
        this.instituicaoVinculada = instituicaoVinculada;
    }
}

