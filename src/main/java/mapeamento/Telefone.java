package mapeamento;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Telefone implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
    private String numero;
    @Enumerated(EnumType.STRING)
    private TelefoneType tipo;
	public Telefone(String numero, TelefoneType tipo) {
		super();
		this.numero = numero;
		this.tipo = tipo;
	}
	
	public Telefone() {
	}

	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public TelefoneType getTipo() {
		return tipo;
	}
	public void setTipo(TelefoneType tipo) {
		this.tipo = tipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
