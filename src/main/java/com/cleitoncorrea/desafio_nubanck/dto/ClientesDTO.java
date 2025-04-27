package com.cleitoncorrea.desafio_nubanck.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientesDTO {

    private String nome;
    private List<ContatosDTO> contatos = new ArrayList<ContatosDTO>();
}
