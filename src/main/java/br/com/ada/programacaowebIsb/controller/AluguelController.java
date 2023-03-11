package br.com.ada.programacaowebIsb.controller;

import br.com.ada.programacaowebIsb.model.Aluguel;
import br.com.ada.programacaowebIsb.service.AluguelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;
import java.util.Optional;

@Controller
public class AluguelController {

    @Autowired
    private AluguelService aluguelService;

    @GetMapping("/alugueis")
    public ModelAndView alugueis(
            @RequestParam(defaultValue = "1", value = "page") Integer numeroPagina,
            @RequestParam(defaultValue = "3", value = "size") Integer tamanhoPagina){
        ModelAndView modelAndView = new ModelAndView("alugueis");
        Page<Aluguel> aluguelPage = this.aluguelService.listarPaginado(numeroPagina-1, tamanhoPagina);
        modelAndView.addObject("alugueis",aluguelPage.getContent());
        modelAndView.addObject("totalPages",aluguelPage.getTotalPages());
        modelAndView.addObject("currentPage",numeroPagina);
        modelAndView.addObject("pageSize",aluguelPage.getSize());
        return modelAndView;
    }

    @GetMapping("/aluguel/add")
    public String addAluguel(Model model, Aluguel aluguel){
        model.addAttribute("add",Boolean.TRUE);
        model.addAttribute("aluguel", Objects.nonNull(aluguel) ? aluguel : new Aluguel() );
        return "aluguel-add";
    }

    @PostMapping("/aluguel/add")
    public String criarAluguel(@Valid @ModelAttribute("aluguel") Aluguel aluguel,
                               BindingResult result,
                               Model model){
        if(result.hasErrors()){
            return addAluguel(model, aluguel);
        }
        this.aluguelService.createAluguel(aluguel);
        return "redirect:/alugueis";
    }

    @GetMapping("/aluguel/{aluguelId}/delete")
    public String deletarAluguel(@PathVariable("aluguelId") Long aluguelId){
       this.aluguelService.removerAluguelPorId(aluguelId);
        return "redirect:/alugueis";
    }

    @GetMapping("/aluguel/{aluguelId}/edit")
    public String mostrarEdicaoAluguel(Model model, @PathVariable("aluguelId") Long aluguelId){
        Optional<Aluguel> optionalAluguel = this.aluguelService.buscarAluguelPorId(aluguelId);
        optionalAluguel.ifPresent(aluguel -> model.addAttribute("aluguel", aluguel));
        model.addAttribute("add",Boolean.FALSE);
        return "aluguel-add";
    }

    @PutMapping("/aluguel/{aluguelId}/edit")
    public String editarAluguel(@ModelAttribute("aluguel") Aluguel aluguel,
                                @PathVariable("aluguelId") Long aluguelId){
        aluguel.setId(aluguelId);
        this.aluguelService.createAluguel(aluguel);
        return "redirect:/alugueis";
    }
}