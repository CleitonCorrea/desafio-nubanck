package com.cleitoncorrea.desafio_nubanck.repository;

import com.cleitoncorrea.desafio_nubanck.model.Contatos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatosRepository extends JpaRepository<Contatos, Long> {
}
