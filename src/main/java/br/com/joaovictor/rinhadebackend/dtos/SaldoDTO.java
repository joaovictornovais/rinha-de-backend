package br.com.joaovictor.rinhadebackend.dtos;

import java.time.LocalDateTime;

public record SaldoDTO(Integer total, LocalDateTime dataExtrato, Integer limite) {

}
