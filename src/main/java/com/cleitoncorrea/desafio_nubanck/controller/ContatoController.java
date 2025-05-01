package com.cleitoncorrea.desafio_nubanck.controller;

import com.cleitoncorrea.desafio_nubanck.dto.ContatosDTO;
import com.cleitoncorrea.desafio_nubanck.dto.ContatosResponseDTO;
import com.cleitoncorrea.desafio_nubanck.model.Clientes;
import com.cleitoncorrea.desafio_nubanck.model.Contatos;
import com.cleitoncorrea.desafio_nubanck.repository.ClientesRepository;
import com.cleitoncorrea.desafio_nubanck.repository.ContatosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    @Autowired
    private ContatosRepository contatosRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    @PostMapping
    public ResponseEntity<?> save(ContatosDTO contatoDTO){

        Optional<Clientes> cliente = clientesRepository.findById(contatoDTO.getClienteId());
        if(cliente.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente nao encontrado");
        }

        Contatos contato = new Contatos();
        contato.setTelefone(contatoDTO.getTelefone());
        contato.setEmail(contatoDTO.getEmail());
        contato.setClientes(cliente.get());
        contatosRepository.save(contato);

        return ResponseEntity.status(HttpStatus.CREATED).body(contato);

    }



}
