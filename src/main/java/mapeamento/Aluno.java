package mapeamento;

import conversores.ConversorLocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Aluno extends PessoaUm {

    @Id
    private String matricula;
    @Convert(converter = ConversorLocalDate.class)
    private LocalDate dataIngresso;
    private String turma;
}
