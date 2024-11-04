package com.example.Pegaso.Data.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.Pegaso.Data.Models.Comentario;
import com.example.Pegaso.Data.Models.Dica;
import com.example.Pegaso.Data.Models.Usuario;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

	public List<Comentario> findByDicaEquals(Dica dica);

	public List<Comentario> findByUsuarioEquals(Usuario usuario);
}