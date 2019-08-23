package br.com.ifpe.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Volume implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_volume")
    private long idVolume;
    
    @Column(name="descricao_volume", nullable = false, length = 12)
    private String descricaoVolume;

    
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
}
