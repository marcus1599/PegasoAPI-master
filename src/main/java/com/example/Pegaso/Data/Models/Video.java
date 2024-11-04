package com.example.Pegaso.Data.Models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/*
 * - IdVideo : Int
- titulo : String
- descricao : String
 */

@Setter
@Getter
@AllArgsConstructor
@Entity
public class Video {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long idVideo;

    @Column(nullable = false)
    String titulo;
    
    @Column(nullable = true)
    String descricao;

    @Column(nullable = false)
    String endereco;

}
