package br.com.ada.programacaowebIsb.repository;

import br.com.ada.programacaowebIsb.model.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long>{
}
