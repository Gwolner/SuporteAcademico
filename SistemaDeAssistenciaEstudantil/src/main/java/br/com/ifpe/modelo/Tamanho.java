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
@Table(name="tb_tamanho")
@Access(AccessType.FIELD)
public class Tamanho implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tamanho")
    private long idTamanho;
    
    @Column(name="descricao_tamanho", nullable = false, length = 2)
    private String descricaoTamanho;
    
    
    //Cardinalidade Tamanho 1 : N Fardamento
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "tamanho", 
            fetch = FetchType.LAZY)
    protected List<Fardamento> fardamento;
    
    
    public long getIdTamanho() {
        return idTamanho;
    }

    public void setIdTamanho(long idTamanho) {
        this.idTamanho = idTamanho;
    }

    public String getDescricaoTamanho() {
        return descricaoTamanho;
    }

    public void setDescricaoTamanho(String descricaoTamanho) {
        this.descricaoTamanho = descricaoTamanho;
    }

    public List<Fardamento> getFardamento() {
        return fardamento;
    }

    public void setFardamento(List<Fardamento> fardamento) {
        this.fardamento = fardamento;
    }
    
}
