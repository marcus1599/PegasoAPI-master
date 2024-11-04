package com.example.Pegaso.domain.Service.Imagem;

import com.example.Pegaso.Data.Models.Imagem;
import com.example.Pegaso.Data.Models.Postagem;
import com.example.Pegaso.Data.Repository.ImagensRepositiry;
import com.example.Pegaso.Data.Repository.PostagemRepository;
import com.example.Pegaso.domain.DTO.ImagemDTO;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImagensService {

    @Autowired
    private final ImagensRepositiry repositiry;
    private final PostagemRepository postRepository;

    public Imagem salvar(ImagemDTO dto) {
        Imagem imagem = new Imagem();

        Long idPostagem = dto.getIdPostagem();
        postRepository.findById(idPostagem);

        Postagem postagem = postExiste(idPostagem);

        imagem.setTitulo(dto.getTitulo());
        imagem.setDescricao(dto.getDescricao());
        imagem.setEndereco(dto.getEndereco());
        imagem.setCurtidas(dto.getCurtidas());
        imagem.setFigure(dto.getFigure());
        imagem.setPostagem(postagem);

        return repositiry.save(imagem);
    }

    public byte[] addPhoto(Long id, Part arquivo) {

        Optional<Imagem> contato = repositiry.findById(id);

        return contato.map(c -> {
            try {
                InputStream is = arquivo.getInputStream();
                byte[] bytes = new byte[(int) arquivo.getSize()];
                IOUtils.readFully(is, bytes);
                c.setFigure(bytes);
                repositiry.save(c);
                is.close();

                return bytes;

            } catch (IOException e) {
                return null;
            }
        }).orElse(null);
    }

    public List<Imagem> findTudo() {
        return repositiry.findAll();
    }

    private Postagem postExiste(Long idPost) {

        Postagem postagem = postRepository
                .findById(idPost)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Postagem inexistente."));

        return postagem;
    }

}
