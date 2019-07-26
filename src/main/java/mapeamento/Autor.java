package mapeamento;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Autor extends PessoaUm {
    private String instituicaoVinculada;
    @ManyToMany(mappedBy="autores")
    private List<Livro> livros;
    
    public Autor(){
    	this.livros = new ArrayList<>();
    }
    
    
	public Autor(String nome, String cpf, Integer idade, Endereco endereco, LocalDate dataNasc,
			String instituicaoVinculada, List<Livro> livros) {
		super(nome, cpf, idade, endereco, dataNasc);
		this.instituicaoVinculada = instituicaoVinculada;
		this.livros = livros;
	}
	
	

	public Autor(String nome, String cpf, Integer idade, Endereco endereco, LocalDate dataNasc,
			String instituicaoVinculada) {
		super(nome, cpf, idade, endereco, dataNasc);
		this.instituicaoVinculada = instituicaoVinculada;
		this.livros = new ArrayList<>();
	}


	public String getInstituicaoVinculada() {
		return instituicaoVinculada;
	}

	public void setInstituicaoVinculada(String instituicaoVinculada) {
		this.instituicaoVinculada = instituicaoVinculada;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
    
	public void setLivro(Livro livro) {
		this.livros.add(livro);
	}
	
}

