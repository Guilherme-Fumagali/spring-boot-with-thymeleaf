package com.gfumagali.curso.boot.demomvc.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.gfumagali.curso.boot.demomvc.domain.Departamento;
import com.gfumagali.curso.boot.demomvc.service.DepartamentoService;

@Component
public class StringToDepartamentoConverter implements Converter<String, Departamento> {
    
        @Autowired
        private DepartamentoService service;
        
        @Override
        public Departamento convert(String text) {
            if (text.isEmpty()) return null;
            return service.buscarPorId(Long.valueOf(text));
        }
}
