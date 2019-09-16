package br.com.ifpe.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="tb_professor")
@DiscriminatorValue(value="P") //Valor usado no campo descriminador
@PrimaryKeyJoinColumn( //Definindo a PK de Professor
        name="id_professor", //Nome da coluna PK de Professor
        referencedColumnName="id_usuario" //Referencia a PK de Usuario
)
public class Professor extends Usuario implements Serializable {
    
    @Column(name="siape", nullable = false, length = 8)
    private int siape;
    
    @Column(name="setor", nullable = false, length = 100)
    private String setor;
    
    @Column(name="departamento", nullable = false, length = 100)
    private String departamento;
    
    @Column(name="ramal", nullable = false, length = 15)
    private long ramal;

    
    public int getSiape() {
        return siape;
    }

    public void setSiape(int siape) {
        this.siape = siape;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public long getRamal() {
        return ramal;
    }

    public void setRamal(long ramal) {
        this.ramal = ramal;
    }
    
    
}
