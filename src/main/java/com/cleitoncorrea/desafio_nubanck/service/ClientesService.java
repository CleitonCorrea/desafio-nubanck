package com.cleitoncorrea.desafio_nubanck.service;

import com.cleitoncorrea.desafio_nubanck.dto.ClientesDTO;
import com.cleitoncorrea.desafio_nubanck.dto.ClientesResponseDTO;
import com.cleitoncorrea.desafio_nubanck.dto.ContatosDTO;
import com.cleitoncorrea.desafio_nubanck.dto.ContatosResponseDTO;
import com.cleitoncorrea.desafio_nubanck.model.Clientes;
import com.cleitoncorrea.desafio_nubanck.model.Contatos;
import com.cleitoncorrea.desafio_nubanck.repository.ClientesRepository;
import com.cleitoncorrea.desafio_nubanck.repository.ContatosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;
    @Autowired
    private ContatosRepository contatosRepository;

    public Clientes salvarCliente(ClientesDTO dto){

        Clientes clientes = new Clientes();

        clientes.setNome(dto.getNome());

        if(dto.getContatos() != null && dto.getContatos().size() > 0){
            List<Contatos> contatos = dto.getContatos()
                                                .stream()
                    .map(c->{
                        Contatos contato = new Contatos();
                        contato.setTelefone(c.getTelefone());
                        contato.setEmail(c.getEmail());
                        contato.setClientes(clientes);
                        return contato;
                    })
                    .collect(Collectors.toList());
            clientes.setContatos(contatos);
        }


        return clientesRepository.save(clientes);
    }

    public List<ClientesResponseDTO> getAll(){
        return clientesRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public ClientesResponseDTO toDto(Clientes cliente){
    ClientesResponseDTO dto = new ClientesResponseDTO();
    dto.setId(cliente.getId());
    dto.setNome(cliente.getNome());

    List<ContatosResponseDTO> contatos = cliente.getContatos().stream().map(c->{
                ContatosResponseDTO contatosResponseDTO = new ContatosResponseDTO();
                contatosResponseDTO.setId(c.getId());
                contatosResponseDTO.setTelefone(c.getTelefone());
                contatosResponseDTO.setEmail(c.getEmail());
                return contatosResponseDTO;
            })
            .collect(Collectors.toList());

    dto.setContatos(contatos);
    return dto;
    }

    @GetMapping()
    public List<ContatosResponseDTO> getContatoByCliente(Long clienteId){
        Clientes cliente = clientesRepository.findById(clienteId)
                .orElseThrow(()->new RuntimeException("Cliente não encontrado!"));

        return cliente.getContatos().stream().map(c->{
            ContatosResponseDTO contatosResponseDTO = new ContatosResponseDTO();
            contatosResponseDTO.setId(c.getId());
            contatosResponseDTO.setTelefone(c.getTelefone());
            contatosResponseDTO.setEmail(c.getEmail());
            return contatosResponseDTO;
        }).collect(Collectors.toList());
    }

}
