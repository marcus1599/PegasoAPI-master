package com.example.Pegaso.domain.Service.Postagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.Pegaso.Controller.DicaController;
import com.example.Pegaso.Controller.PostagemController;
import com.example.Pegaso.Data.Models.Postagem;
import com.example.Pegaso.Data.Repository.PostagemRepository;
import com.example.Pegaso.domain.Builder.DozerMapper;
import com.example.Pegaso.domain.Builder.Postagem.PostagemBuilder;
import com.example.Pegaso.domain.VO.V1.PostagemVO;
import com.example.Pegaso.domain.VO.V1.PostagemVO_OutPut;
import com.example.Pegaso.exceptions.ResourceNotFoundException;

import lombok.AllArgsConstructor;

import java.util.List;

@Service
@AllArgsConstructor
public class PostagemServiceImp implements PostagemService {

    @Autowired
    private PostagemRepository repository;

    private final PostagemBuilder postagemBuilder;

    @Override
    public PostagemVO savePostagem(PostagemVO postagemVO) throws Exception {

        // Conversão de VO para entidade
        var entity = DozerMapper.parseObject(postagemVO, Postagem.class);

        // Salvando no banco de dados e adicionando em uma variável
        var postagem = repository.save(entity);

        // Convertendo para Vo para exibir para usuário
        var vo = postagemBuilder.convertToVO(postagem);
        vo.add(linkTo(methodOn(PostagemController.class).getOnePost(vo.getKey())).withSelfRel());
        return vo;
    }

    @Override
    public List<PostagemVO> findAllPost() {
        var post = repository.findAll();
        var postagemVO = postagemBuilder.convertToVO(post);

        postagemVO.stream()
                .forEach(p -> {
                    try {
                        p.add(linkTo(methodOn(PostagemController.class)
                                .getOnePost(p.getKey())).withRel("Detalhes"));
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                });
        postagemVO.stream()
                .forEach(p -> {
                    try {
                        p.add(linkTo(methodOn(DicaController.class)
                                .getDicasByPostagem(p.getKey())).withRel("Dicas"));
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                });

        return postagemVO;
    }

    @Override
    public PostagemVO findPostById(Long id) throws Exception {

        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Searched post with specified id not found"));
        PostagemVO vo = DozerMapper.parseObject(entity, PostagemVO.class);
        vo.add(linkTo(methodOn(PostagemController.class).getOnePost(id)).withSelfRel());
        return vo;
    }

    @Override
    public PostagemVO_OutPut findByIdPostagemCostomized(Long id) {
        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Searched post with specified id not found"));

        return postagemBuilder.convertToOutPut(entity);
    }

    @Override
    public PostagemVO update(PostagemVO postagemVO, Long id) throws Exception {
        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Searched post with specified id not found"));

        entity.setNome(postagemVO.getNome());
        entity.setDescricao(postagemVO.getDescricao());

        var postagem = repository.save(entity);

        var vo = DozerMapper.parseObject(postagem, PostagemVO.class);
        vo.add(linkTo(methodOn(PostagemController.class).getOnePost(vo.getKey())).withSelfRel());
        return vo;

    }

    @Override
    public void deletePost(Long id) {
        repository.deleteById(id);
    }

}
