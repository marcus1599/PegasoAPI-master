package com.example.Pegaso.domain.VO.V1;

import java.io.Serializable;
import org.springframework.hateoas.RepresentationModel;

import com.example.Pegaso.Data.Models.Dica;
import com.example.Pegaso.Data.Models.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@JsonPropertyOrder({ "id_Comentario", "corpo", "curtidas", "dica", "usuario" })
public class ComentarioVO extends RepresentationModel<ComentarioVO> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id_Comentario")
    @Mapping("idComentario")
    private Long key;
    private String corpo;
    private int curtidas = 0;
    private Dica dica;
    private Usuario usuario;

    public ComentarioVO() {
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public int getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(int curtidas) {
        this.curtidas = curtidas;
    }

    public Dica getDica() {
        return (Dica) this.dica;
    }

    public void setDica(Dica dica) {
        this.dica = (Dica) dica;
    }

    public Usuario getUsuario() {
        return (Usuario) this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = (Usuario) usuario;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;

        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((corpo == null) ? 0 : corpo.hashCode());
        result = prime * result + curtidas;
        result = prime * result + ((dica == null) ? 0 : dica.hashCode());
        result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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

        ComentarioVO other = (ComentarioVO) obj;

        if (key == null) {
            if (other.key != null)
                return false;
        }

        else if (!key.equals(other.key))
            return false;

        if (corpo == null) {
            if (other.corpo != null)
                return false;
        }

        else if (!corpo.equals(other.corpo))
            return false;

        if (curtidas != other.curtidas)
            return false;

        if (dica == null) {
            if (other.dica != null)
                return false;
        }

        else if (!dica.equals(other.dica))
            return false;

        if (usuario == null) {
            if (other.usuario != null)
                return false;
        }

        else if (!usuario.equals(other.usuario))
            return false;

        return true;
    }
}