package br.com.ifpe.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_bolsa")
@Access(AccessType.FIELD)
public class Bolsa implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_bolsa")
    private long idBolsa;
    private String nomeBolsa;
    private String tipo;
    private double valor;
    
    
    //Cardinalidade Aluno N : N Bolsa
    @ManyToMany(mappedBy = "bolsas") //Subordinado ao relacionamento ManyToMany
    protected List<Aluno> alunos;

    
    public long getIdBolsa() {
        return idBolsa;
    }

    public void setIdBolsa(long idBolsa) {
        this.idBolsa = idBolsa;
    }

    public String getNomeBolsa() {
        return nomeBolsa;
    }

    public void setNomeBolsa(String nomeBolsa) {
        this.nomeBolsa = nomeBolsa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
    
    
}
