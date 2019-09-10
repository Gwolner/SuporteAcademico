package br.com.ifpe.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_situacao")
@Access(AccessType.FIELD)
public class Situacao implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_situacao")
    private long idSituacao;
    
    @Column(name="nome_aluno", nullable = false, length = 15)
    private String descricaoSituacao;
    
    
    //Cardinalidade Situacao 1 : N Fardamento
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "situacao",
            fetch = FetchType.LAZY)
    protected List<Fardamento> fardamento;
    
    
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

    public List<Fardamento> getFardamento() {
        return fardamento;
    }

    public void setFardamento(List<Fardamento> fardamento) {
        this.fardamento = fardamento;
    }
    
}
