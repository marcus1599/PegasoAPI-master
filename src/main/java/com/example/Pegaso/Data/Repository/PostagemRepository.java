package com.example.Pegaso.Data.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Pegaso.Data.Models.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

}
