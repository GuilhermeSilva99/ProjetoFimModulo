package br.com.ada.programacaowebIsb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_aluguel")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Obrigatório o preenchimento da placa")
    @NotBlank(message = "Não pode ser vazio")
    @Size(min = 7, message = "Placa deve conter no minimo 7 caracteres")
    private String placa;

    @NotEmpty(message = "Obrigatório o preenchimento do nome")
    @NotBlank(message = "Não pode ser vazio")
    @Size(min = 3, message = "Nome deve conter no minimo 3 caracteres")
    private String nomePessoa;

    @NotEmpty(message = "Obrigatório o preenchimento do local de retirada")
    @NotBlank(message = "Não pode ser vazio")
    @Size(min = 3, message = "Local deve conter no minimo 3 caracteres")
    private String local;

    @NotNull(message = "Obrigatório o preenchimento da data de inicio do aluguel")
    private LocalDateTime inicioDoAluguel;
    private LocalDateTime fimDoAluguel;

}
