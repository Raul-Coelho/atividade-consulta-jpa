package mapeamento;

import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class Professor extends PessoaUm{
	private Float salario;
	public Professor() {
		super();
	}
	
	public Professor(String nome, String cpf, Integer idade, LocalDate dataNasc, Float salario) {
		super(nome, cpf, idade, dataNasc);
		this.salario = salario;
	}

	public Float getSalario() {
		return salario;
	}

	public void setSalario(Float salario) {
		this.salario = salario;
	}
}
