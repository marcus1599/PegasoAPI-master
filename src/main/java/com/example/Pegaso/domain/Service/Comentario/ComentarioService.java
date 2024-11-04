package com.example.Pegaso.domain.Service.Comentario;

import java.util.List;

import com.example.Pegaso.Data.Models.Dica;
import com.example.Pegaso.Data.Models.Usuario;
import com.example.Pegaso.domain.VO.V1.ComentarioVO;
import com.example.Pegaso.domain.VO.V1.ComentarioVO_OutPut;

public interface ComentarioService {

    List<ComentarioVO> findByDicaContainin(Dica dica) throws Exception;

    List<ComentarioVO> findByUserContainin(Usuario usuario) throws Exception;

    ComentarioVO saveComment(ComentarioVO comentarioVO) throws Exception;

    List<ComentarioVO> findAllComment() throws Exception;

    ComentarioVO findCommentById(Long id) throws Exception;

    ComentarioVO_OutPut findByIdCommentCostomized(Long id) throws Exception;

    ComentarioVO updateComment(ComentarioVO comentarioVO, Long id) throws Exception;

    void deleteComment(Long id) throws Exception;

}