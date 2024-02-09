package br.com.joaovictor.rinhadebackend.services;

import org.springframework.stereotype.Service;

import br.com.joaovictor.rinhadebackend.entities.Extrato;
import br.com.joaovictor.rinhadebackend.repositories.ExtratoRepository;

@Service
public class ExtratoService {
	
	private final ExtratoRepository extratoRepository;
	
	public ExtratoService(ExtratoRepository extratoRepository) {
		this.extratoRepository = extratoRepository;
	}
	
	public Extrato buscarExtrato(Long id) {
		return extratoRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Extrato não encontrado"));
	}

}
