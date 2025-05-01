package com.cleitoncorrea.desafio_nubanck.controller;

import com.cleitoncorrea.desafio_nubanck.dto.ClientesDTO;
import com.cleitoncorrea.desafio_nubanck.dto.ClientesResponseDTO;
import com.cleitoncorrea.desafio_nubanck.dto.ContatosResponseDTO;
import com.cleitoncorrea.desafio_nubanck.model.Clientes;
import com.cleitoncorrea.desafio_nubanck.service.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClientesService clientesService;

    @PostMapping
    public ResponseEntity<Clientes> save(@RequestBody ClientesDTO clientesDTO){
       Clientes clienteSave = clientesService.salvarCliente(clientesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSave);
    }

    @GetMapping
    public ResponseEntity<List<ClientesResponseDTO>> getAllClientes(){
        return ResponseEntity.ok(clientesService.getAll());
    }

    @GetMapping("/{id}/contatos")
    public ResponseEntity<List<ContatosResponseDTO>> getContatoByIdCliente(@PathVariable Long id){
        return ResponseEntity.ok(clientesService.getContatoByCliente(id));
    }

}
