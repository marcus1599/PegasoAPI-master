package com.example.Pegaso.domain.VO.V1;

import java.util.List;

import com.example.Pegaso.Data.Models.Comentario;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioVO_OutPut {

    Long idUsuario;
    String nome;
    String biografia;
    String email;
    List<Comentario> comentario;
}