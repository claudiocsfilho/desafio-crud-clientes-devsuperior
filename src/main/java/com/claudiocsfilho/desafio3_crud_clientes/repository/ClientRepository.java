package com.claudiocsfilho.desafio3_crud_clientes.repository;

import com.claudiocsfilho.desafio3_crud_clientes.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
