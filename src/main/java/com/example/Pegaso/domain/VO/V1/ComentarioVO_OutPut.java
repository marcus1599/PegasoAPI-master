package com.example.Pegaso.domain.VO.V1;

import com.example.Pegaso.Data.Models.Dica;
import com.example.Pegaso.Data.Models.Usuario;

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
public class ComentarioVO_OutPut {

    Long idComentario;
    String corpo;
    int curtidas = 0;
    Dica dica;
    Usuario usuario;
}