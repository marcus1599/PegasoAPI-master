package com.example.Pegaso.Data.Models;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Entity
public class GestaoPostagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id_gestao;

    @Column(nullable = true)
    ArrayList<Postagem> postagems;

}
