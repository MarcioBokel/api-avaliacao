package br.org.serratec.avaliacao.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.org.serratec.avaliacao.dto.RestauranteDto;
import br.org.serratec.avaliacao.model.Restaurante;
import br.org.serratec.avaliacao.repository.RestauranteRepository;

@Service
public class RestauranteService {
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	public List<RestauranteDto> obterTodos(){
		List<RestauranteDto> restaurantes = new ArrayList<>();
		restauranteRepository.findAll().forEach(restaurante -> {
			restaurantes.add(restaurante.toDto());
			
		});
		return restaurantes;
	}
	
	public List<RestauranteDto> obterPorInicioCliente(String inicioDoCliente){
		List<RestauranteDto> restaurantes = new ArrayList<>();
		restauranteRepository.findByClienteStartingWith(inicioDoCliente).forEach(restaurante -> {
			restaurantes.add(restaurante.toDto());
			
		});
		return restaurantes;
	}
	
	public List<RestauranteDto> obterPorFinalPrato(String finalDoPrato){
		List<RestauranteDto> restaurantes = new ArrayList<>();
		restauranteRepository.findByPratoEndingWith(finalDoPrato).forEach(restaurante -> {
			restaurantes.add(restaurante.toDto());
			
		});
		return restaurantes;
	}
	
	public List<RestauranteDto> obterPorClienteePrato(String buscaCliente, String buscaPrato){
		List<RestauranteDto> restaurantes = new ArrayList<>();
		restauranteRepository.findByClienteAndPrato(buscaCliente, buscaPrato).forEach(restaurante -> {
			restaurantes.add(restaurante.toDto());
			
		});
		return restaurantes;
	}
	
	public RestauranteDto salvarRestaurante(RestauranteDto novoRestaurante) {
		// Aqui aconteceriam outras validações de conteúdo
		Restaurante restauranteSalvo = restauranteRepository.save(novoRestaurante.toEntity());
		return restauranteSalvo.toDto();
	}

	public Optional<RestauranteDto> atualizarRestaurante(Long id, RestauranteDto restauranteAlterado) {
		Restaurante restauranteEntity = restauranteAlterado.toEntity();
		
		if (restauranteRepository.existsById(id)) {
			restauranteEntity.setId(id);
			restauranteRepository.save(restauranteEntity);
			return Optional.of(restauranteEntity.toDto());
		}
		return Optional.empty();
	}
	
	public Optional<RestauranteDto> obterClientePorId(Long id) {
        Optional<Restaurante> restaurante = restauranteRepository.findById(id);

        if (restaurante.isPresent()) {
            return Optional.of(restaurante.get().toDto());
        }

        return Optional.empty();
    }
	
	
	
	public boolean excluirRestaurante(Long id) {
		if(restauranteRepository.existsById(id)){
			restauranteRepository.deleteById(id);
			return true;
		}
		
		return false;
	}
	
}

