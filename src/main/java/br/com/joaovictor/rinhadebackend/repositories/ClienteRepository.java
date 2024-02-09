package br.com.joaovictor.rinhadebackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joaovictor.rinhadebackend.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
