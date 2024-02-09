package br.com.joaovictor.rinhadebackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.joaovictor.rinhadebackend.entities.Transacao;
import br.com.joaovictor.rinhadebackend.repositories.TransacaoRepository;

@Service
public class TransacaoService {
	
	private final TransacaoRepository transacaoRepository;
	
	public TransacaoService(TransacaoRepository transacaoRepository) {
		this.transacaoRepository = transacaoRepository;
	}
	
	public Transacao gravar(Transacao transacao) {
		return transacaoRepository.save(transacao);
	}
	
}
