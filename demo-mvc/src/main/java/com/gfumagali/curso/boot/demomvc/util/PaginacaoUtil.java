package com.gfumagali.curso.boot.demomvc.util;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class PaginacaoUtil<T> {
    private int tamanho;
    private int pagina;
    private long totalDePaginas;
    private String coluna;
    private String direcao;
    private List<T> registros;
}
