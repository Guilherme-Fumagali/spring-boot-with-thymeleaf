package com.gfumagali.curso.boot.demomvc.dao;

import java.util.List;

import com.gfumagali.curso.boot.demomvc.domain.Cargo;
import com.gfumagali.curso.boot.demomvc.util.PaginacaoUtil;

public interface CargoDao {
    void save(Cargo cargo);
    void update(Cargo cargo);
    void delete(Long id);
    Cargo findById(Long id);
    List<Cargo> findAll();
    PaginacaoUtil<Cargo> buscaPaginada(int pagina, int qtd_por_pagina, String coluna, String direcao);
}
