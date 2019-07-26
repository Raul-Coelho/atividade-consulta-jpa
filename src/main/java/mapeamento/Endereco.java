package mapeamento;

import javax.persistence.Entity;

@Entity
public class Endereco {
	private String rua;
	private String bairro;
	private String cidade;
	private String cep;
}
