package mapeamento;

import java.time.LocalDate;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class Professor extends PessoaUm{
	private Float salario;
	@Embedded
	private Telefone telefone;
	public Professor() {
		super();
	}
	
	public Professor(String nome, String cpf, Integer idade, Endereco endereco, LocalDate dataNasc, Float salario,
			Telefone telefone) {
		super(nome, cpf, idade, endereco, dataNasc);
		this.salario = salario;
		this.telefone = telefone;
	}

	public Professor(String nome, String cpf, Integer idade, Endereco endereco, LocalDate dataNasc, Float salario) {
		super(nome, cpf, idade, endereco, dataNasc);
		this.salario = salario;
	}

	public Float getSalario() {
		return salario;
	}

	public void setSalario(Float salario) {
		this.salario = salario;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	
	
}
