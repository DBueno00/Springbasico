package br.com.springbasico.springbasico.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springbasico.springbasico.core.models.Client;

public interface ClientRepository extends JpaRepository<Client,Long> {
    
}
