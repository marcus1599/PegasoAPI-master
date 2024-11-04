package com.example.Pegaso.Data.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Pegaso.Data.Models.Dica;
import com.example.Pegaso.Data.Models.Postagem;

@Repository
public interface DicaRepository extends JpaRepository<Dica, Long> {

    public List<Dica> findByPostagemEquals(Postagem postagem);

}
