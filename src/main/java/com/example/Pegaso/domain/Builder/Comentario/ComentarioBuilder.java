package com.example.Pegaso.domain.Builder.Comentario;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.Pegaso.Data.Models.Comentario;
import com.example.Pegaso.Data.Models.Dica;
import com.example.Pegaso.Data.Models.Usuario;
import com.example.Pegaso.domain.VO.V1.ComentarioVO;
import com.example.Pegaso.domain.VO.V1.ComentarioVO_OutPut;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ComentarioBuilder {

    public ComentarioVO_OutPut convertToOutPut(Comentario comentario) {

        ComentarioVO_OutPut vo = new ComentarioVO_OutPut();
        vo.setIdComentario(comentario.getIdComentario());
        vo.setCorpo(comentario.getCorpo());
        vo.setCurtidas(comentario.getCurtidas());
        vo.setDica(comentario.getDica());
        vo.setUsuario(comentario.getUsuario());
        return vo;
    }

    public List<ComentarioVO_OutPut> convertToOutPut(List<Comentario> list) {

        return list.stream().map(this::convertToOutPut).collect(Collectors.toList());
    }

    public ComentarioVO convertToVo(Comentario comentario) {
        ComentarioVO vo = new ComentarioVO();
        Dica dica = comentario.getDica();
        Usuario user = comentario.getUsuario();
        vo.setKey(comentario.getIdComentario());
        vo.setCorpo(comentario.getCorpo());
        vo.setUsuario(user);
        vo.setDica(dica);

        return vo;
    }

    public List<ComentarioVO> convertToVo(List<Comentario> list) {

        return list.stream().map(this::convertToVo).collect(Collectors.toList());
    }
}
