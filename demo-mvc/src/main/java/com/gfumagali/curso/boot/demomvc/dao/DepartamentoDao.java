package com.gfumagali.curso.boot.demomvc.dao;

import java.util.List;

import com.gfumagali.curso.boot.demomvc.domain.Departamento;

public interface DepartamentoDao {
    void save(Departamento departamento);
    void update(Departamento departamento);
    void delete(Long id);
    Departamento findById(Long id);
    List<Departamento> findAll();
}
