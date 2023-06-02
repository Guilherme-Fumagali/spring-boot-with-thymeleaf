package com.gfumagali.curso.boot.demomvc.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.gfumagali.curso.boot.demomvc.domain.Cargo;
import com.gfumagali.curso.boot.demomvc.util.PaginacaoUtil;

@Repository
public class CargoDaoImpl extends AbstractDao<Cargo, Long> implements CargoDao {
    
    public PaginacaoUtil<Cargo> buscaPaginada(int pagina, int qtdPorPagina, String coluna, String direcao) {
        int inicio = (pagina - 1) * qtdPorPagina;

        List<Cargo> cargos = getEntityManager()
            .createQuery("select c from Cargo c order by " + coluna + " " + direcao, Cargo.class)
            .setFirstResult(inicio)
            .setMaxResults(qtdPorPagina)
            .getResultList();  

        long totalDePaginas = (totalRegistros() + (qtdPorPagina - 1)) / qtdPorPagina;

        return new PaginacaoUtil<>(qtdPorPagina, pagina, totalDePaginas, coluna, direcao, cargos);
    }

    public long totalRegistros() {
        return getEntityManager()
            .createQuery("select count(*) from Cargo", Long.class)
            .getSingleResult();
    }
}
