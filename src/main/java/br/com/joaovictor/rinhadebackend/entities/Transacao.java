package br.com.joaovictor.rinhadebackend.entities;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.joaovictor.rinhadebackend.dtos.TransacaoDTO;
import br.com.joaovictor.rinhadebackend.enums.TipoTransacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_transacao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Transacao {
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false)
	private Integer valor;
	@Column(nullable = false)
	private TipoTransacao tipo;
	@Column(nullable = false, length = 10)
	private String descricao;
	@Column(nullable = false)
	private LocalDateTime realizadaEm;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "extrato_id")
	private Extrato extrato;
	
	public Transacao(TransacaoDTO transacaoDTO) {
		BeanUtils.copyProperties(transacaoDTO, this);
	}

}
