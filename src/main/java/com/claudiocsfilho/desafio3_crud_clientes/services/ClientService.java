package com.claudiocsfilho.desafio3_crud_clientes.services;

import com.claudiocsfilho.desafio3_crud_clientes.dto.ClientDTO;
import com.claudiocsfilho.desafio3_crud_clientes.entities.Client;
import com.claudiocsfilho.desafio3_crud_clientes.repository.ClientRepository;
import com.claudiocsfilho.desafio3_crud_clientes.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll (Pageable pageable){
        Page<Client> result = repository.findAll(pageable);
        return result.map(x -> new ClientDTO(x));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById (Long id){
        Client entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado!"));
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO insert (ClientDTO dto){
        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    //Metodo auxiliar para copiar DTO para Entity
    private void copyDtoToEntity(ClientDTO dto, Client entity){
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }

}
