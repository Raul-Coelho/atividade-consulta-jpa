package mapeamento;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Telefone {
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
    
}
