package com.cleitoncorrea.desafio_nubanck.repository;

import com.cleitoncorrea.desafio_nubanck.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<Clientes, Long> {
}
