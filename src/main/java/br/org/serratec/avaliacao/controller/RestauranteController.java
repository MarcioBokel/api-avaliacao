package br.org.serratec.avaliacao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.org.serratec.avaliacao.dto.RestauranteDto;
import br.org.serratec.avaliacao.service.RestauranteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {
	@Autowired
	private RestauranteService restauranteService;
	
	@GetMapping
	public ResponseEntity<List<RestauranteDto>> obterTodos(){
		return ResponseEntity.ok(restauranteService.obterTodos());
	}
	
	@GetMapping("/cliente")
	public ResponseEntity<List<RestauranteDto>> obterPorInicioCliente(@RequestBody String inicioDoCliente){
		return ResponseEntity.ok(restauranteService.obterPorInicioCliente(inicioDoCliente));
	}
	
	@GetMapping("/prato")
	public ResponseEntity<List<RestauranteDto>> obterPorFinalPrato(@RequestBody String fimDoPrato){
		return ResponseEntity.ok(restauranteService.obterPorFinalPrato(fimDoPrato));
	}
	
	@GetMapping("/cliente-e-prato")
	public ResponseEntity<List<RestauranteDto>> buscarCliente(@RequestParam String cliente, @RequestParam String prato){
		return ResponseEntity.ok(restauranteService.obterPorClienteePrato(cliente, prato));
	}
	
	@PostMapping
	public ResponseEntity<RestauranteDto> salvarCliente(@Valid @RequestBody RestauranteDto novoRestaurante){
		return new ResponseEntity<RestauranteDto>(restauranteService.salvarRestaurante(novoRestaurante), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<RestauranteDto> atualizarRestaurante(@PathVariable Long id, @Valid @RequestBody RestauranteDto restauranteAlterado) {
		Optional<RestauranteDto> restaurante = restauranteService.atualizarRestaurante(id, restauranteAlterado);
		
		if (restaurante.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(restaurante.get());
	}

	@GetMapping("/{id}")
    public ResponseEntity<RestauranteDto> obterClientePorId(@PathVariable Long id) {
        Optional<RestauranteDto> restaurante = restauranteService.obterClientePorId(id);

        if (restaurante.isPresent()) {
            return ResponseEntity.ok(restaurante.get());
        }

        return ResponseEntity.notFound().build();
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if(restauranteService.excluirRestaurante(id)){
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}

