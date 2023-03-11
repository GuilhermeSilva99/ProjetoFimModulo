package br.com.ada.programacaowebIsb.service;

import br.com.ada.programacaowebIsb.model.Aluguel;
import br.com.ada.programacaowebIsb.repository.AluguelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;

    public void createAluguel(Aluguel aluguel){
        this.aluguelRepository.save(aluguel);
    }

    public List<Aluguel> listarTodos(){
        return this.aluguelRepository.findAll();
    }

    public Page<Aluguel> listarPaginado(Integer numPagina, Integer tamPagina){
        return this.aluguelRepository.findAll(PageRequest.of(numPagina,tamPagina,Sort.by(Sort.Order.asc("id"))));
    }

    public Optional<Aluguel> buscarAluguelPorId(Long id){
        return this.aluguelRepository.findById(id);
    }

    public void removerAluguelPorId(Long id){
        this.aluguelRepository.deleteById(id);
    }
}