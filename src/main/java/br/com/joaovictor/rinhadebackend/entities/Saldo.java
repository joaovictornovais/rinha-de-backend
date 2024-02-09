package br.com.joaovictor.rinhadebackend.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_saldo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Saldo {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false)
	private Integer total;
	@Transient
	private LocalDateTime dataExtrato;
	@Column(nullable = false)
	private Integer limite;
	
	@JsonBackReference
	@OneToOne(mappedBy = "saldo")
	private Cliente cliente;
	
	@JsonBackReference
	@OneToOne(mappedBy = "saldo")
	private Extrato extrato;
	
	public LocalDateTime getDataExtrato() {
		return LocalDateTime.now();
	}
}
