package com.gfumagali.curso.boot.demomvc.service;

import java.util.List;

import com.gfumagali.curso.boot.demomvc.domain.Cargo;
import com.gfumagali.curso.boot.demomvc.util.PaginacaoUtil;

public interface CargoService {
    void salvar(Cargo cargo);
    void editar(Cargo cargo);
    void excluir(Long id);
    Cargo buscarPorId(Long id);
    List<Cargo> buscarTodos();
    boolean cargoTemFuncionarios(Long id);
    PaginacaoUtil<Cargo> buscarPorPagina(int pagina, int qtd_por_pagina, String coluna, String direcao);
}
