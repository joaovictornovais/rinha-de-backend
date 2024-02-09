package br.com.joaovictor.rinhadebackend.services;

import org.springframework.stereotype.Service;

import br.com.joaovictor.rinhadebackend.entities.Cliente;
import br.com.joaovictor.rinhadebackend.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	private final ClienteRepository clienteRepository;
	
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	public Cliente buscarCliente(Long id) {
		return clienteRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Cliente não encontrado"));
	}
	
	public Cliente cadastrarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

}
