package br.org.serratec.avaliacao.dto;

import br.org.serratec.avaliacao.model.Restaurante;
import jakarta.validation.constraints.NotBlank;


public record RestauranteDto(
		Long id,
		
		@NotBlank (message = "O Cliente deve ser informado!")
		String cliente,
		
		@NotBlank (message = "O prato escolhido do cardápio deve ser informado!")
		String prato,
		
		@NotBlank (message = "O número do pedido deve ser informado")
		String pedido
		
		) {
	public Restaurante toEntity() {
		return new Restaurante(
				this.id,
				this.cliente,
				this.prato,
				this.pedido
				);
	}
}