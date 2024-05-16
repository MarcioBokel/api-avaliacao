package br.org.serratec.avaliacao.model;

import br.org.serratec.avaliacao.dto.RestauranteDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="restaurante")
public class Restaurante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cliente;
	private String prato;
	private String pedido;

	public Restaurante() {}

	public Restaurante(Long id, String cliente, String prato, String pedido) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.prato = prato;
		this.pedido = pedido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCliente() {
		return cliente;
	}
	
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	public String getPrato() {
		return prato;
	}
	
	public void setPrato(String prato) {
		this.prato = prato;
	}
	
	public String getPedido() {
		return pedido;
	}
	
	public void setPedido(String pedido) {
		this.pedido = pedido;
	}
	
	public RestauranteDto toDto() {
		return new RestauranteDto(
				this.id,
				this.cliente,
				this.prato,
				this.pedido
				);
	}
}

