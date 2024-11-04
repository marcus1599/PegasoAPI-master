package com.example.Pegaso.domain.Service.Dica;

import java.util.List;

import com.example.Pegaso.Data.Models.Postagem;
import com.example.Pegaso.domain.VO.V1.DicaVO;
import com.example.Pegaso.domain.VO.V1.DicaVO_OutPut;
import com.example.Pegaso.domain.VO.V1.PostagemVO;

public interface DicaService {

    DicaVO saveDica(DicaVO dicaVO, PostagemVO postagemVO) throws Exception;

    List<DicaVO> findAllDicas() throws Exception;

    List<DicaVO_OutPut> findByPostagemContainin(Postagem postagem) throws Exception;

    DicaVO findById(Long id) throws Exception;

    DicaVO update(DicaVO dicaVo, Long id) throws Exception;

    DicaVO_OutPut findByIdDicaCostomized(Long id) throws Exception;

    void deleteDica(Long id) throws Exception;

}