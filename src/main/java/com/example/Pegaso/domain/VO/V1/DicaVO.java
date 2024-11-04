package com.example.Pegaso.domain.VO.V1;

import java.io.Serializable;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.example.Pegaso.Data.Models.Comentario;
import com.example.Pegaso.Data.Models.Postagem;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonPropertyOrder({ "id_dica", "title", "body", "curtidas", "comment", "post" })
public class DicaVO extends RepresentationModel<DicaVO> implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id_dica")
    @Mapping("idDica")
    private Long key;
    private String title;
    private String body;
    private int curtidas = 0;
    private List<Comentario> comentario;
    private Postagem postagem;

    public DicaVO() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public int getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(int curtidas) {
        this.curtidas = curtidas;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Comentario> getComentario() {
        return this.comentario;
    }

    public void setComentario(List<Comentario> comentario) {
        this.comentario = comentario;
    }

    public Postagem getPostagem() {
        return (Postagem) this.postagem;
    }

    public void setPostagem(Postagem postagem) {
        this.postagem = (Postagem) postagem;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((body == null) ? 0 : body.hashCode());
        result = prime * result + curtidas;
        result = prime * result + ((comentario == null) ? 0 : comentario.hashCode());
        result = prime * result + ((postagem == null) ? 0 : postagem.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        DicaVO other = (DicaVO) obj;
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (body == null) {
            if (other.body != null)
                return false;
        } else if (!body.equals(other.body))
            return false;
        if (curtidas != other.curtidas)
            return false;
        if (comentario == null) {
            if (other.comentario != null)
                return false;
        } else if (!comentario.equals(other.comentario))
            return false;
        if (postagem == null) {
            if (other.postagem != null)
                return false;
        } else if (!postagem.equals(other.postagem))
            return false;
        return true;
    }

}
