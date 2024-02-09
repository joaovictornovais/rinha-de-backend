package br.com.joaovictor.rinhadebackend.dtos;

import br.com.joaovictor.rinhadebackend.enums.TipoTransacao;

public record TransacaoDTO(Integer valor, TipoTransacao tipo, String descricao) {

}
