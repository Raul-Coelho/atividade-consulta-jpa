package mapeamento;

import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;

import conversores.ConversorLocalDate;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class PessoaUm {
	private String nome;
	@Id
	private String cpf;
	private Integer idade;
	@Embedded
	private Endereco endereco;
	@Convert(converter=ConversorLocalDate.class)
	private LocalDate dataNasc;
	public PessoaUm() {
		super();
	}
	
	public PessoaUm(String nome, String cpf, Integer idade, Endereco endereco, LocalDate dataNasc) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.idade = idade;
		this.endereco = endereco;
		this.dataNasc = dataNasc;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public LocalDate getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
}
