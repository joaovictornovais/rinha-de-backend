package br.com.joaovictor.rinhadebackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joaovictor.rinhadebackend.entities.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

}
