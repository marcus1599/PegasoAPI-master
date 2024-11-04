package com.example.Pegaso.domain.Service.Comentario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Pegaso.Controller.ComentarioController;
import com.example.Pegaso.Data.Models.Comentario;
import com.example.Pegaso.Data.Models.Dica;
import com.example.Pegaso.Data.Models.Usuario;
import com.example.Pegaso.Data.Repository.ComentarioRepository;
import com.example.Pegaso.exceptions.ResourceNotFoundException;

import lombok.AllArgsConstructor;

import com.example.Pegaso.domain.Builder.DozerMapper;
import com.example.Pegaso.domain.Builder.Comentario.ComentarioBuilder;
import com.example.Pegaso.domain.VO.V1.ComentarioVO;
import com.example.Pegaso.domain.VO.V1.ComentarioVO_OutPut;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.util.List;

@Service
@AllArgsConstructor
public class ComentarioServiceImp implements ComentarioService {

	@Autowired
	private ComentarioRepository repositoryComment;

	private final ComentarioBuilder comentarioBuilder;

	@Override
	public List<ComentarioVO> findByDicaContainin(Dica dica) {

		var comentario = repositoryComment.findByDicaEquals(dica);
		var comentarioVO = comentarioBuilder.convertToVo(comentario);
		comentarioVO.stream().forEach(
				p -> {
					try {
						p.add(linkTo(methodOn(ComentarioController.class).getOneComment(p.getKey())).withSelfRel());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
		return comentarioVO;
	}

	@Override
	public List<ComentarioVO> findByUserContainin(Usuario usuario) {

		var comentario = repositoryComment.findByUsuarioEquals(usuario);
		var comentarioVO = comentarioBuilder.convertToVo(comentario);
		comentarioVO.stream().forEach(
				p -> {
					try {
						p.add(linkTo(methodOn(ComentarioController.class).getOneComment(p.getKey())).withSelfRel());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
		return comentarioVO;
	}

	@Override
	public ComentarioVO saveComment(ComentarioVO comentarioVO) throws Exception {

		// Utilizando DozzerMaper para converter para VO
		var entity = DozerMapper.parseObject(comentarioVO, Comentario.class);
		var comentario = repositoryComment.save(entity);
		var vo = comentarioBuilder.convertToVo(comentario);
		vo.add(linkTo(methodOn(ComentarioController.class).getOneComment(vo.getKey())).withSelfRel());
		return vo;
	}

	@Override
	public List<ComentarioVO> findAllComment() {

		var comment = repositoryComment.findAll();
		var comentarioVO = comentarioBuilder.convertToVo(comment);
		comentarioVO.stream().forEach(
				p -> {
					try {
						p.add(linkTo(methodOn(ComentarioController.class).getOneComment(p.getKey())).withSelfRel());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
		return comentarioVO;
	}

	@Override
	public ComentarioVO findCommentById(Long id) throws Exception {

		var entity = repositoryComment.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Searched comment with specified id not found"));
		ComentarioVO vo = DozerMapper.parseObject(entity, ComentarioVO.class);
		vo.add(linkTo(methodOn(ComentarioController.class).getOneComment(id)).withSelfRel());
		return vo;
	}

	@Override
	public ComentarioVO_OutPut findByIdCommentCostomized(Long id) {

		var entity = repositoryComment.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Searched comment with specified id not found"));
		return comentarioBuilder.convertToOutPut(entity);
	}

	@Override
	public ComentarioVO updateComment(ComentarioVO comentarioVO, Long id) throws Exception {

		var entity = repositoryComment.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Searched comment with specified id not found"));
		entity.setCorpo(comentarioVO.getCorpo());
		entity.setCurtidas(comentarioVO.getCurtidas());
		var comentario = repositoryComment.save(entity);
		var vo = DozerMapper.parseObject(comentario, ComentarioVO.class);
		vo.add(linkTo(methodOn(ComentarioController.class).getOneComment(vo.getKey())).withSelfRel());
		return vo;
	}

	@Override
	public void deleteComment(Long id) {

		repositoryComment.deleteById(id);
	}

}