package com.gfumagali.curso.boot.demomvc.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class AbstractEntity<ID extends Serializable> implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

}
