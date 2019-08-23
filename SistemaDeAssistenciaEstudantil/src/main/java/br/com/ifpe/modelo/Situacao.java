package br.com.ifpe.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Situacao implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_situacao")
    private long idSituacao;
    
    @Column(name="nome_aluno", nullable = false, length = 15)
    private String descricaoSituacao;

    
    public long getIdSituacao() {
        return idSituacao;
    }

    public void setIdSituacao(long idSituacao) {
        this.idSituacao = idSituacao;
    }

    public String getDescricaoSituacao() {
        return descricaoSituacao;
    }

    public void setDescricaoSituacao(String descricaoSituacao) {
        this.descricaoSituacao = descricaoSituacao;
    }
}
