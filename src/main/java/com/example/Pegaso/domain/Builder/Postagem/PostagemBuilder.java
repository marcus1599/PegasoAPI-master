package com.example.Pegaso.domain.Builder.Postagem;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.Pegaso.Data.Models.Postagem;
import com.example.Pegaso.domain.VO.V1.PostagemVO;
import com.example.Pegaso.domain.VO.V1.PostagemVO_OutPut;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PostagemBuilder {

    public PostagemVO_OutPut convertToOutPut(Postagem postagem) {
        PostagemVO_OutPut vo = new PostagemVO_OutPut();

        vo.setIdPostagem(postagem.getIdPostagem());
        vo.setNome(postagem.getNome());
        vo.setDescricao(postagem.getDescricao());

        vo.setDicas(postagem.getDicas());

        return vo;
    }

    public PostagemVO convertToVO(Postagem postagem) {
        PostagemVO vo = new PostagemVO();

        vo.setKey(postagem.getIdPostagem());
        vo.setNome(postagem.getNome());
        vo.setDescricao(postagem.getDescricao());
        vo.setDicas(postagem.getDicas());
        vo.setImagems(postagem.getImagems());
        vo.setVideos(postagem.getVideos());
        vo.setUsuario(postagem.getUsuario());

        return vo;
    }

    public List<PostagemVO_OutPut> convertToOutPut(List<Postagem> list) {

        return list.stream().map(this::convertToOutPut).collect(Collectors.toList());
    }

    public List<PostagemVO> convertToVO(List<Postagem> list) {

        return list.stream().map(this::convertToVO).collect(Collectors.toList());
    }
}
