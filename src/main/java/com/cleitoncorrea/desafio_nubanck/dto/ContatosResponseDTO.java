package com.cleitoncorrea.desafio_nubanck.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContatosResponseDTO {

    private Long id;
    private String telefone;
    private String email;
    private Long clienteId;
}
