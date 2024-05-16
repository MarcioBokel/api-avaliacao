package br.org.serratec.avaliacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.avaliacao.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long>{

	
	List<Restaurante> findByClienteStartingWith(String inicioDoCliente);
	
	List<Restaurante> findByPratoEndingWith(String finalDoPrato);
	
	List<Restaurante> findByClienteAndPrato(String buscaCliente, String buscaPrato);
	
}
