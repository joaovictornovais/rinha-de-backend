package br.com.joaovictor.rinhadebackend.services;

import org.springframework.stereotype.Service;

import br.com.joaovictor.rinhadebackend.entities.Saldo;
import br.com.joaovictor.rinhadebackend.repositories.SaldoRepository;

@Service
public class SaldoService {
	
	private final SaldoRepository saldoRepository;
	
	public SaldoService(SaldoRepository saldoRepository) {
		this.saldoRepository = saldoRepository;
	}
	
	public Saldo salvarSaldo(Saldo saldo) {
		return saldoRepository.save(saldo);
	}

}
