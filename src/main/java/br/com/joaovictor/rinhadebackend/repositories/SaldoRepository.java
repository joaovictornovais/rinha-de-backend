package br.com.joaovictor.rinhadebackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joaovictor.rinhadebackend.entities.Saldo;

public interface SaldoRepository extends JpaRepository<Saldo, Long> {

}
