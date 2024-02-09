package br.com.joaovictor.rinhadebackend.controllers;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joaovictor.rinhadebackend.dtos.ClienteDTO;
import br.com.joaovictor.rinhadebackend.dtos.TransacaoDTO;
import br.com.joaovictor.rinhadebackend.dtos.TransacaoResponseDTO;
import br.com.joaovictor.rinhadebackend.entities.Cliente;
import br.com.joaovictor.rinhadebackend.entities.Extrato;
import br.com.joaovictor.rinhadebackend.entities.Saldo;
import br.com.joaovictor.rinhadebackend.entities.Transacao;
import br.com.joaovictor.rinhadebackend.enums.TipoTransacao;
import br.com.joaovictor.rinhadebackend.services.ClienteService;
import br.com.joaovictor.rinhadebackend.services.ExtratoService;
import br.com.joaovictor.rinhadebackend.services.SaldoService;
import br.com.joaovictor.rinhadebackend.services.TransacaoService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private final ClienteService clienteService;
	private final TransacaoService transacaoService;
	private final SaldoService saldoService;
	private final ExtratoService extratoService;
	
	public ClienteController(ClienteService clienteService, 
			TransacaoService transacaoService,
			SaldoService saldoService,
			ExtratoService extratoService) {
		this.clienteService = clienteService;
		this.transacaoService = transacaoService;
		this.saldoService = saldoService;
		this.extratoService = extratoService;
	}
	
	@PostMapping
	public ResponseEntity<Cliente> cadastrarCliente(@RequestBody ClienteDTO clienteDTO) {
		return ResponseEntity.ok(clienteService.cadastrarCliente(new Cliente(clienteDTO)));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarCliente(@PathVariable Long id) {
		return ResponseEntity.ok(clienteService.buscarCliente(id));
	}
	
	@PostMapping("/{id}/transacoes")
	public ResponseEntity<TransacaoResponseDTO> transacao(
			@PathVariable Long id, @RequestBody TransacaoDTO transacaoDTO) {
		Cliente cliente = clienteService.buscarCliente(id);
		Extrato extrato = extratoService.buscarExtrato(id);
		Transacao transacao = new Transacao(transacaoDTO);
		transacao.setRealizadaEm(LocalDateTime.now());
		transacao.setCliente(cliente);
		transacao.setExtrato(extrato);
		Saldo saldo = cliente.getSaldo();
		if (transacao.getTipo() == TipoTransacao.c) {
			if (-(saldo.getLimite()) <= saldo.getTotal() - transacao.getValor()) {
				transacaoService.gravar(transacao);
				saldo.setTotal(saldo.getTotal() - transacao.getValor());
				saldoService.salvarSaldo(saldo);
				TransacaoResponseDTO transacaoResponseDTO = new TransacaoResponseDTO(saldo.getLimite(), saldo.getTotal());
				return ResponseEntity.ok(transacaoResponseDTO);
			}
			throw new RuntimeException("Você não tem limite para essa compra");
		}
		if (saldo.getTotal() >= transacao.getValor()) {
			transacaoService.gravar(transacao);
			saldo.setTotal(saldo.getTotal() - transacao.getValor());
			saldoService.salvarSaldo(saldo);
			TransacaoResponseDTO transacaoResponseDTO = new TransacaoResponseDTO(saldo.getLimite(), saldo.getTotal());
			return ResponseEntity.ok(transacaoResponseDTO);
		}
		throw new RuntimeException("Você não tem saldo para essa compra");
	}
	
	@GetMapping("/{id}/extrato")
	public ResponseEntity<Extrato> extrato(@PathVariable Long id) {
		Cliente cliente = clienteService.buscarCliente(id);
		Extrato extrato = cliente.getSaldo().getExtrato();
		extrato.setUltimasTransacoes(extrato.getUltimasTransacoes().subList(0, 10));
		return ResponseEntity.ok(extrato);
	}
	
	

}
