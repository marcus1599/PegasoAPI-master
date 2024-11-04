package com.example.Pegaso.domain.Service.Usuario;

import java.util.List;

import com.example.Pegaso.domain.VO.V1.UsuarioVO;
import com.example.Pegaso.domain.VO.V1.UsuarioVO_OutPut;

public interface UsuarioService {

    UsuarioVO saveUser(UsuarioVO usuarioVO) throws Exception;

    List<UsuarioVO> findAllUser() throws Exception;

    UsuarioVO findUserById(Long id) throws Exception;

    UsuarioVO_OutPut findByIdUserCostomized(Long id) throws Exception;

    UsuarioVO updateUser(UsuarioVO usuarioVO, Long id) throws Exception;

    void deleteUser(Long id) throws Exception;

}