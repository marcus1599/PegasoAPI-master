package com.example.Pegaso.domain.Builder.Dica;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.Pegaso.Data.Models.Dica;
import com.example.Pegaso.Data.Models.Postagem;
import com.example.Pegaso.domain.VO.V1.DicaVO;
import com.example.Pegaso.domain.VO.V1.DicaVO_OutPut;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DicaBuilder {

    public DicaVO_OutPut convertToOutPut(Dica dica) {
        // OutPut personalisada
        DicaVO_OutPut vo = new DicaVO_OutPut();
        vo.setKey(dica.getIdDica());
        vo.setTitle(dica.getTitle());
        vo.setCurtidas(dica.getCurtidas());
        vo.setQuantComentario(dica.getComentario().size());
        vo.setIdPostagem(dica.getPostagem().getIdPostagem());
        return vo;
    }

    public List<DicaVO_OutPut> convertToOutPut(List<Dica> list) {

        return list.stream().map(this::convertToOutPut).collect(Collectors.toList());
    }

    public DicaVO fromToVO(Dica dica) {
        DicaVO vo = new DicaVO();
        Postagem post = dica.getPostagem();
        vo.setKey(dica.getIdDica());
        vo.setTitle(dica.getTitle());
        vo.setBody(dica.getBody());
        vo.setCurtidas(dica.getCurtidas());
        vo.setPostagem(post);
        return vo;
    }

    public List<DicaVO> fromToVO(List<Dica> list) {

        return list.stream().map(this::fromToVO).collect(Collectors.toList());
    }

}
