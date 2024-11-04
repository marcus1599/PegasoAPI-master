package com.example.Pegaso.domain.Service.Dica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.Pegaso.Controller.DicaController;
import com.example.Pegaso.Data.Models.Dica;
import com.example.Pegaso.Data.Models.Postagem;
import com.example.Pegaso.Data.Repository.DicaRepository;
import com.example.Pegaso.domain.Builder.DozerMapper;
import com.example.Pegaso.domain.Builder.Dica.DicaBuilder;
import com.example.Pegaso.domain.VO.V1.DicaVO;
import com.example.Pegaso.domain.VO.V1.DicaVO_OutPut;
import com.example.Pegaso.domain.VO.V1.PostagemVO;
import com.example.Pegaso.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
public class DicaServiceImp implements DicaService {

    @Autowired
    private DicaRepository repository;

    @Autowired
    private DicaBuilder dicaBuilder;

    @Override
    public DicaVO saveDica(DicaVO dicaVO, PostagemVO postagemVO) throws Exception {

        var entity = DozerMapper.parseObject(dicaVO, Dica.class);
        var entityPost = DozerMapper.parseObject(postagemVO, Postagem.class);
        entityPost.addDica(entity);

        entity.setPostagem(entityPost);
        // Salvando no banco de dados e adicionando em uma variável
        var dica = repository.save(entity);

        // Convertendo para Vo para exibir para usuário
        var vo = dicaBuilder.fromToVO(dica);
        vo.add(linkTo(methodOn(DicaController.class).getOneDica(vo.getKey())).withSelfRel());
        return vo;
    }

    @Override
    public List<DicaVO> findAllDicas() throws Exception {
        var dica = repository.findAll();
        var dicaVO = dicaBuilder.fromToVO(dica);

        dicaVO.stream()
                .forEach(p -> {
                    try {
                        p.add(linkTo(methodOn(DicaController.class)
                                .getOneDica(p.getKey())).withSelfRel());
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                });

        return dicaVO;
    }

    @Override
    public List<DicaVO_OutPut> findByPostagemContainin(Postagem postagem) throws Exception {
        var dica = repository.findByPostagemEquals(postagem);
        var dicaVO = dicaBuilder.convertToOutPut(dica);

        dicaVO.stream()
                .forEach(p -> {
                    try {
                        p.add(linkTo(methodOn(DicaController.class)
                                .getOneDica(p.getKey())).withRel("Informações"));
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                });

        return dicaVO;
    }

    @Override
    public DicaVO findById(Long id) throws Exception {

        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Searched dica with specified id not found"));
        DicaVO vo = DozerMapper.parseObject(entity, DicaVO.class);
        vo.add(linkTo(methodOn(DicaController.class).getOneDica(id)).withSelfRel());
        return vo;
    }

    @Override
    public DicaVO update(DicaVO dicaVo, Long id) throws Exception {
        var entity = repository.findById(dicaVo.getKey()).orElseThrow(
                () -> new ResourceNotFoundException("Searched dica with specified id not found"));

        entity.setTitle(dicaVo.getTitle());
        entity.setBody(dicaVo.getBody());

        var dica = repository.save(entity);

        var vo = DozerMapper.parseObject(dica, DicaVO.class);
        vo.add(linkTo(methodOn(DicaController.class).getOneDica(vo.getKey())).withSelfRel());
        return vo;

    }

    @Override
    public DicaVO_OutPut findByIdDicaCostomized(Long id) {
        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Searched post with specified id not found"));

        return dicaBuilder.convertToOutPut(entity);
    }

    @Override
    public void deleteDica(Long id) {
        repository.deleteById(id);
    }

}
