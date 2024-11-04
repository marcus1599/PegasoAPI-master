package com.example.Pegaso.domain.VO.V1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.example.Pegaso.Data.Models.Dica;
import com.example.Pegaso.Data.Models.Imagem;
import com.example.Pegaso.Data.Models.Usuario;
import com.example.Pegaso.Data.Models.Video;
import com.example.Pegaso.domain.Builder.DozerMapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonPropertyOrder({ "id_postagem", "nome", "descricao", "dicas", "imagems", "videos", "comentarios", "curtidas" })
public class PostagemVO extends RepresentationModel<PostagemVO> implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id_postagem")
    @Mapping("idPostagem")
    private Long key;
    private String nome;
    private String descricao;
    private List<Dica> dicas;
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private ArrayList<Imagem> imagems;
    private ArrayList<Video> videos;

    public PostagemVO() {

    }

    public void addDica(DicaVO dicaVo) {
        var dicaEntity = DozerMapper.parseObject(dicaVo, Dica.class);

        this.dicas.add(dicaEntity);
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Dica> getDicas() {
        return dicas;
    }

    public void setDicas(List<Dica> dicas) {
        this.dicas = dicas;
    }

    public ArrayList<Imagem> getImagems() {
        return imagems;
    }

    public void setImagems(ArrayList<Imagem> imagems) {
        this.imagems = imagems;
    }

    public ArrayList<Video> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Video> videos) {
        this.videos = videos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((dicas == null) ? 0 : dicas.hashCode());
        result = prime * result + ((imagems == null) ? 0 : imagems.hashCode());
        result = prime * result + ((videos == null) ? 0 : videos.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PostagemVO other = (PostagemVO) obj;
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (descricao == null) {
            if (other.descricao != null)
                return false;
        } else if (!descricao.equals(other.descricao))
            return false;
        if (dicas == null) {
            if (other.dicas != null)
                return false;
        } else if (!dicas.equals(other.dicas))
            return false;
        if (imagems == null) {
            if (other.imagems != null)
                return false;
        } else if (!imagems.equals(other.imagems))
            return false;
        if (videos == null) {
            if (other.videos != null)
                return false;
        } else if (!videos.equals(other.videos)) {
            return false;
        }
        return true;
    }

}
