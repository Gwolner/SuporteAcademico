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
@Table(name="tb_volume")
@Access(AccessType.FIELD)
public class Volume implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_volume")
    private long idVolume;
    
    @Column(name="descricao_volume", nullable = false, length = 12)
    private String descricaoVolume;
    
    
    //Cardinalidade Volume 1 : N Livro
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "volume", 
            fetch = FetchType.LAZY)
    protected List<Livro> livro;
    
    
    public long getIdVolume() {
        return idVolume;
    }

    public void setIdVolume(long idVolume) {
        this.idVolume = idVolume;
    }

    public String getDescricaoVolume() {
        return descricaoVolume;
    }

    public void setDescricaoVolume(String descricaoVolume) {
        this.descricaoVolume = descricaoVolume;
    }

    public List<Livro> getLivro() {
        return livro;
    }

    public void setLivro(List<Livro> livro) {
        this.livro = livro;
    }
    
}
